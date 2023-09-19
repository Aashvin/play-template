package controllers

import models.{APIError, DataModel}
import services.{LibraryService, RepositoryService}
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ApplicationController @Inject()(val controllerComponents: ControllerComponents, val repositoryService: RepositoryService, val libraryService: LibraryService)(implicit val ec: ExecutionContext) extends BaseController {

    def index(): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.index().map {
            case Right(value: JsValue) => Ok(value)
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
        request.body.validate[DataModel] match {
            case JsSuccess(dataModel, _) =>
                repositoryService.create(dataModel).map {
                    case Right(value: JsValue) => Created(value)
                    case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
                }
            case JsError(_) => Future(BadRequest)
        }
    }

    def read(id: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.read(id).map {
            case Right(value: JsValue) => Ok(value)
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def findBySearch(field: String, value: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.findBySearch(field, value).map {
            case Right(value: JsValue) => Ok(value)
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def update(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
        request.body.validate[DataModel] match {
            case JsSuccess(dataModel, _) =>
                repositoryService.update(id, dataModel).map {
                    case Right(value: JsValue) => Accepted(value)
                    case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
                }
            case JsError(_) => Future(BadRequest)
        }
    }

    def updateByID(id: String, field: String, value: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.updateByID(id, field, value).map {
            case Right(value: JsValue) => Accepted(value)
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.delete(id).map {
            case Right(_: Boolean) => Accepted
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def getGoogleBook(search: String, term: String): Action[AnyContent] = Action.async { implicit request =>
        libraryService.getGoogleBook(search = search, term = term).value.map {
            case Right(book) => Ok {Json.toJson(book)} //Hint: This should be the same as before
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }
}
