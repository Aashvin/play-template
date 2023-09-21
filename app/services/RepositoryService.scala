package services

import models.{APIError, DataModel}
import play.api.libs.json.{JsValue, Json}
import repositories.RepositoryTrait

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RepositoryService @Inject()(val dataRepository: RepositoryTrait)(implicit val ec: ExecutionContext) {

    def index(): Future[Either[APIError, Seq[DataModel]]] = {
        dataRepository.index().map {
            case Right(item: Seq[DataModel]) => Right(item)
            case Left(error: APIError) => Left(error)
        }
    }

    def create(dataModel: DataModel): Future[Either[APIError, DataModel]] = {
        dataRepository.create(dataModel).map{
            case Right(value: DataModel) => Right(value)
            case Left(error: APIError) => Left(error)
        }
    }

    def read(id: String): Future[Either[APIError, DataModel]] = {
        dataRepository.read(id).map {
            case Right(value: DataModel) => Right(value)
            case Left(error: APIError) => Left(error)
        }
    }

    def findBySearch(field: String, value: String): Future[Either[APIError, DataModel]] = {
        dataRepository.findBySearch(field, value).map {
            case Right(value: DataModel) => Right(value)
            case Left(error: APIError) => Left(error)
        }
    }

    def update(id: String, dataModel: DataModel): Future[Either[APIError, DataModel]] = {
        dataRepository.update(id, dataModel).map {
            case Right(_: DataModel) => Right(dataModel)
            case Left(error: APIError) => Left(error)
        }
    }

    def updateByID(id: String, field: String, value: String): Future[Either[APIError, DataModel]] = {
        dataRepository.updateByID(id, field, value).map {
            case Right(dataModel: DataModel) => Right(dataModel)
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
