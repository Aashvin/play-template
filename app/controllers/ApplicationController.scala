package controllers

import models.DataModel.dataModelForm
import models.{APIError, DataModel}
import services.{LibraryService, RepositoryService}
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc._
import play.filters.csrf.CSRF

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ApplicationController @Inject()(val controllerComponents: ControllerComponents, val repositoryService: RepositoryService, val libraryService: LibraryService)(implicit val ec: ExecutionContext) extends BaseController with play.api.i18n.I18nSupport {

    def getGoogleBook(search: String, term: String): Action[AnyContent] = Action.async { implicit request =>
        libraryService.getGoogleBook(search = search, term = term).value.map {
            case Right(book) => Ok {
                Json.toJson(book)
            } //Hint: This should be the same as before
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def getGoogleBookAsDataModel(search: String, term: String): Action[AnyContent] = Action.async { implicit request =>
        libraryService.getGoogleBookAsDataModel(search = search, term = term).value.map {
            case Right(book) => Ok {
                Json.toJson(book)
            } //Hint: This should be the same as before
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def index(): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.index().map {
            case Right(value: Seq[DataModel]) => Ok(Json.toJson(value))
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    private def accessToken(implicit request: Request[_]): Option[CSRF.Token] = {
        CSRF.getToken
    }

    def addBook(): Action[AnyContent] = Action { implicit request =>
        Ok(views.html.addBook(DataModel.dataModelForm))
    }

    def addBookForm(): Action[AnyContent] = Action.async { implicit request =>
        accessToken //call the accessToken method
        dataModelForm.bindFromRequest().fold( //from the implicit request we want to bind this to the form in our companion object
            formWithErrors => {
                Future(BadRequest(formWithErrors.errors.toString))
            },
            formData => {
                repositoryService.create(formData).map {
                    case Right(value: DataModel) => Redirect(routes.ApplicationController.viewBook(value._id))
                    case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
                }
            }
        )
    }

    def viewBook(id: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.read(id).map {
            case Right(value: DataModel) => Ok(views.html.viewBook(value))
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
        request.body.validate[DataModel] match {
            case JsSuccess(dataModel, _) =>
                repositoryService.create(dataModel).map {
                    case Right(value: DataModel) => Created(views.html.viewBook(value))
                    case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
                }
            case JsError(_) => Future(BadRequest)
        }
    }

    def createFromGoogle(search: String, term: String) : Action[AnyContent] = Action.async { implicit request =>
        libraryService.getGoogleBookAsDataModel(search = search, term = term).value.flatMap {
            case Right(dataModel: DataModel) => repositoryService.create(dataModel).flatMap {
                case Right(value: DataModel) => Future(Created(views.html.viewBook(value)))
                case Left(error: APIError) => Future(Status(error.httpResponseStatus)(Json.toJson(error.reason)))
            }
            case Left(error: APIError) => Future(Status(error.httpResponseStatus)(Json.toJson(error.reason)))
        }
    }

    def read(id: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.read(id).map {
            case Right(value: DataModel) => Ok(Json.toJson(value))
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def findBySearch(field: String, value: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.findBySearch(field, value).map {
            case Right(value: DataModel) => Ok(views.html.viewBook(value))
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def update(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
        request.body.validate[DataModel] match {
            case JsSuccess(dataModel, _) =>
                repositoryService.update(id, dataModel).map {
                    case Right(value: DataModel) => Accepted(Json.toJson(value))
                    case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
                }
            case JsError(_) => Future(BadRequest)
        }
    }

    def updateByID(id: String, field: String, value: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.updateByID(id, field, value).map {
            case Right(value: DataModel) => Accepted(Json.toJson(value))
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }

    def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
        repositoryService.delete(id).map {
            case Right(_: Boolean) => Accepted
            case Left(error: APIError) => Status(error.httpResponseStatus)(Json.toJson(error.reason))
        }
    }
}
