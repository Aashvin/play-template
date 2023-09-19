package services

import models.{APIError, DataModel}
import play.api.libs.json.{JsValue, Json}
import repositories.DataRepository

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RepositoryService @Inject()(val dataRepository: DataRepository)(implicit val ec: ExecutionContext) {

    def index(): Future[Either[APIError, JsValue]] = {
        dataRepository.index().map {
            case Right(item: Seq[DataModel]) => Right(Json.toJson(item))
            case Left(error: APIError) => Left(error)
        }
    }

    def create(dataModel: DataModel): Future[Either[APIError, JsValue]] = {
        dataRepository.create(dataModel).map{
            case Right(value) => Right(Json.toJson(value))
            case Left(error: APIError) => Left(error)
        }
    }

    def read(id: String): Future[Either[APIError, JsValue]] = {
        dataRepository.read(id).map {
            case Right(value) => Right(Json.toJson(value))
            case Left(error: APIError) => Left(error)
        }
    }

    def findBySearch(field: String, value: String): Future[Either[APIError, JsValue]] = {
        dataRepository.findBySearch(field, value).map {
            case Right(value) => Right(Json.toJson(value))
            case Left(error: APIError) => Left(error)
        }
    }

    def update(id: String, dataModel: DataModel): Future[Either[APIError, JsValue]] = {
        dataRepository.update(id, dataModel).map {
            case Right(_: Boolean) => Right(Json.toJson(dataModel))
            case Left(error: APIError) => Left(error)
        }
    }

    def updateByID(id: String, field: String, value: String): Future[Either[APIError, JsValue]] = {
        dataRepository.updateByID(id, field, value).map {
            case Right(dataModel: DataModel) => Right(Json.toJson(dataModel))
            case Left(error: APIError) => Left(error)
        }
    }

    def delete(id: String): Future[Either[APIError, Boolean]] = {
        dataRepository.delete(id).map {
            case Right(value: Boolean) => Right(value)
            case Left(error: APIError) => Left(error)
        }
    }
}
