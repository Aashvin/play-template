package repositories

import com.google.inject.ImplementedBy
import models.{APIError, DataModel}
import org.mongodb.scala.{MongoWriteException, WriteError}
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.model.Filters.{empty, equal}
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.model._
import play.api.PlayException
import uk.gov.hmrc.mongo.MongoComponent
import uk.gov.hmrc.mongo.play.json.PlayMongoRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@ImplementedBy(classOf[DataRepository])
trait RepositoryTrait {
    def index(): Future[Either[APIError.BadAPIResponse, Seq[DataModel]]]
    def create(book: DataModel): Future[Either[APIError.CRUDAPIError, DataModel]]
    def read(id: String): Future[Either[APIError.CRUDAPIError, DataModel]]
    def findBySearch(field: String, value: String): Future[Either[APIError.CRUDAPIError, DataModel]]
    def update(id: String, book: DataModel): Future[Either[APIError.CRUDAPIError, Boolean]]
    def updateByID(id: String, field: String, value: String): Future[Either[APIError.CRUDAPIError, DataModel]]
    def delete(id: String): Future[Either[APIError.CRUDAPIError, Boolean]]
    def deleteAll(): Future[Unit]
}

@Singleton
class DataRepository @Inject()(mongoComponent: MongoComponent)(implicit ec: ExecutionContext) extends PlayMongoRepository[DataModel] (
    collectionName = "dataModels",
    mongoComponent = mongoComponent,
    domainFormat = DataModel.formats,
    indexes = Seq(IndexModel(
        Indexes.ascending("_id")
    )),
    replaceIndexes = false
) {

    def index(): Future[Either[APIError.BadAPIResponse, Seq[DataModel]]] =
        collection.find().toFuture().map {
            case books: Seq[DataModel] => Right(books)
            case _ => Left(APIError.BadAPIResponse(404, "Books cannot be found"))
        }

    def create(book: DataModel): Future[Either[APIError.CRUDAPIError, DataModel]] = {
        try {
            collection.insertOne(book)
        } catch {
            case _ => Left(APIError.CRUDAPIError(400, "Could not create book."))
        }

        collection
            .insertOne(book)
            .toFutureOption()
            .map {
                case Some(value) if value.wasAcknowledged() => Right(book)
                case _ => Left(APIError.CRUDAPIError(400, "Could not create book."))
            }
    }

    private def byID(id: String): Bson =
        Filters.and(
            Filters.equal("_id", id)
        )

    def read(id: String): Future[Either[APIError.CRUDAPIError, DataModel]] =
        collection.find(byID(id)).headOption flatMap {
            case Some(data) => Future(Right(data))
            case None => Future(Left(APIError.CRUDAPIError(404, "Book not found.")))
        }

    def findBySearch(field: String, value: String): Future[Either[APIError.CRUDAPIError, DataModel]] = {
//        collection.find(Json.obj(field -> Json.obj("$regex" -> (".*" + value + ".*"))), None).headOption flatmap {
        val searchValue: Any = if (field == "numSales") value.toInt else value
        collection.find(Filters.equal(field, searchValue)).headOption flatMap {
            case Some(data) => Future(Right(data))
            case _ => Future(Left(APIError.CRUDAPIError(404, "Book not found.")))
        }
    }

    def update(id: String, book: DataModel): Future[Either[APIError.CRUDAPIError, Boolean]] =
        collection.replaceOne(
            filter = byID(id),
            replacement = book,
            options = new ReplaceOptions().upsert(true) //What happens when we set this to false?
        ).toFutureOption().map {
            case Some(value) => if (value.getMatchedCount == 1 && value.getModifiedCount == 1) {
                Right(true)
            } else if (value.getMatchedCount == 0 && value.getModifiedCount == 0) {
                Right(false)
            } else {
                Left(APIError.CRUDAPIError(404, "Book not found."))
            }
            case _ => Left(APIError.CRUDAPIError(400, "Could not update book."))
        }

    def updateByID(id: String, field: String, value: String): Future[Either[APIError.CRUDAPIError, DataModel]] = {
        val searchValue: Any = if (field == "numSales") value.toInt else value
        collection.updateOne(equal("_id", id), set(field, searchValue)).toFutureOption().flatMap {
            case Some(result) => if (result.getMatchedCount == 1 && result.getModifiedCount == 1) {
                read(id)
            } else if (result.getMatchedCount == 1 && result.getModifiedCount == 0) {
                Future(Left(APIError.CRUDAPIError(400, s"Book $field is already $value.")))
            } else {
                Future(Left(APIError.CRUDAPIError(404, "Book not found.")))
            }
            case None => Future(Left(APIError.CRUDAPIError(400, "Could not update book.")))
        }
    }

    def delete(id: String): Future[Either[APIError.CRUDAPIError, Boolean]] = {
        collection.deleteOne(
            filter = byID(id)
        ).toFutureOption().map {
            case Some(value) => if (value.getDeletedCount == 1) Right(true) else {
                Left(APIError.CRUDAPIError(404, "Book not found."))
            }
            case _ => Left(APIError.CRUDAPIError(400, "Could not delete book."))
        }
    }

    def deleteAll(): Future[Unit] = collection.deleteMany(empty()).toFuture().map(_ => ()) //Hint: needed for tests

}
