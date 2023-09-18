package controllers

import models.{APIError, DataModel}
import services.LibraryService
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc._
import repositories.DataRepository

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ApplicationController @Inject()(val controllerComponents: ControllerComponents, val dataRepository: DataRepository, val service: LibraryService)(implicit val ec: ExecutionContext) extends BaseController {
    def index(): Action[AnyContent] = Action.async { implicit request =>
        dataRepository.index().map {
            case Right(item: Seq[DataModel]) => Ok {
                Json.toJson(item)
            }
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
        request.body.validate[DataModel] match {
            case JsSuccess(dataModel, _) =>
                dataRepository.create(dataModel).map(_ => Created)
            case JsError(_) => Future(BadRequest)
        }
    }

    def read(id: String): Action[AnyContent] = Action.async { implicit request =>
        dataRepository.read(id).map {
            case Some(item) => Ok {
                Json.toJson(item)
            }
            case None => BadRequest
        }
    }

    def update(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
        request.body.validate[DataModel] match {
            case JsSuccess(dataModel, _) =>
                dataRepository.update(id, dataModel).map(_ => Accepted(Json.toJson(dataModel)))
            case JsError(_) => Future(BadRequest)
        }
    }

    def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
        dataRepository.delete(id).map {
            _ => Accepted
        }
    }

//    def getGoogleBook(search: String, term: String): Action[AnyContent] = Action.async { implicit request =>
//        service.getGoogleBook(search = search, term = term).map {
//            item => Ok {
//                Json.toJson(item)
//            }
//        }
//    }

    def getGoogleBook(search: String, term: String): Action[AnyContent] = Action.async { implicit request =>
        service.getGoogleBook(search = search, term = term).value.map {
            case Right(book) => Ok {Json.toJson(book)} //Hint: This should be the same as before
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }
}
