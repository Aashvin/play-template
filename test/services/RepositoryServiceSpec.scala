package services

import baseSpec.BaseSpecWithApplication
import models.{APIError, DataModel}
import org.scalamock.scalatest.MockFactory
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Results.Status
import play.api.test.FakeRequest
import repositories.RepositoryTrait

import scala.concurrent.Future

class RepositoryServiceSpec extends BaseSpecWithApplication with MockFactory {
    val mockRepository: RepositoryTrait = mock[RepositoryTrait]
    val TestRepositoryService = new RepositoryService(mockRepository)

    private val dataModel: DataModel = DataModel(
        "abcd",
        "test name",
        "test description",
        100
    )

    private val singleUpdatedDataModel: DataModel = DataModel(
        "abcd",
        "new name",
        "test description",
        100
    )

    private val updatedDataModel: DataModel = DataModel(
        "abcd",
        "new name",
        "new description",
        500
    )

    private val dataModel2: DataModel = DataModel(
        "efgh",
        "test name 2",
        "test description 2",
        50
    )


    "RepositoryService .index()" should {

        val sequenceForIndex = Seq(dataModel, dataModel2)

        "get a sequence of DataModels" in {
            (() => mockRepository.index()).expects()
                .returning(Future(Right(sequenceForIndex))).once()

            whenReady(TestRepositoryService.index()) { result =>
                result shouldBe Right(sequenceForIndex)
            }
        }

        "get an API error" in {
            (() => mockRepository.index()).expects()
                .returning(Future(Left(APIError.BadAPIResponse(404, "Books cannot be found")))).once()

            whenReady(TestRepositoryService.index()) { result =>
                result shouldBe Left(APIError.BadAPIResponse(404, "Books cannot be found"))
            }
        }
    }

    "RepositoryService .create()" should {

        "get a DataModel" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }
        }

        "get an API error when creation is unsuccessful" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Left(APIError.CRUDAPIError(400, "Could not create book.")))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Left(APIError.CRUDAPIError(400, "Could not create book."))
            }
        }

        "get an API error when the id already exists" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Left(APIError.CRUDAPIError(400, "A book with this ID already exists.")))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Left(APIError.CRUDAPIError(400, "A book with this ID already exists."))
            }
        }
    }

    "RepositoryService .read()" should {

        "get a DataModel" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.read(_: String)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.read("abcd")) { result =>
                result shouldBe Right(dataModel)
            }
        }

        "get an API error" in {
            (mockRepository.read(_: String)).expects(*)
                .returning(Future(Left(APIError.CRUDAPIError(404, "Book not found.")))).once()

            whenReady(TestRepositoryService.read("abcd")) { result =>
                result shouldBe Left(APIError.CRUDAPIError(404, "Book not found."))
            }
        }
    }

    "RepositoryService .findBySearch()" should {

        "get a DataModel from string field search" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.findBySearch(_: String, _: String)).expects(*, *)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.findBySearch("name", "test name")) { result =>
                result shouldBe Right(dataModel)
            }
        }

        "get a DataModel from int field search" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.findBySearch(_: String, _: String)).expects(*, *)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.findBySearch("numSales", "100")) { result =>
                result shouldBe Right(dataModel)
            }
        }

        "get an API error when string field is not a match" in {
            (mockRepository.findBySearch(_: String, _: String)).expects(*, *)
                .returning(Future(Left(APIError.CRUDAPIError(404, "Book not found.")))).once()

            whenReady(TestRepositoryService.findBySearch("name", "test name")) { result =>
                result shouldBe Left(APIError.CRUDAPIError(404, "Book not found."))
            }
        }

        "get an API error when int field is not a match" in {
            (mockRepository.findBySearch(_: String, _: String)).expects(*, *)
                .returning(Future(Left(APIError.CRUDAPIError(404, "Book not found.")))).once()

            whenReady(TestRepositoryService.findBySearch("numSales", "99")) { result =>
                result shouldBe Left(APIError.CRUDAPIError(404, "Book not found."))
            }
        }

        "get an API error when the field does not exist" in {
            (mockRepository.findBySearch(_: String, _: String)).expects(*, *)
                .returning(Future(Left(APIError.CRUDAPIError(404, "Book not found.")))).once()

            whenReady(TestRepositoryService.findBySearch("notAField", "test")) { result =>
                result shouldBe Left(APIError.CRUDAPIError(404, "Book not found."))
            }
        }
    }

    "RepositoryService .update()" should {

        "get an updated DataModel book" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.update(_: String, _: DataModel)).expects(*, *)
                .returning(Future(Right(updatedDataModel))).once()

            whenReady(TestRepositoryService.update("abcd", updatedDataModel)) { result =>
                result shouldBe Right(updatedDataModel)
            }
        }

        "get a 400 APIError when trying to change the ID" in {
            val dataModelWithNewID: DataModel = DataModel(
                "xyz",
                "test name",
                "test description",
                100
            )

            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.update(_: String, _: DataModel)).expects(*, *)
                .returning(Future(Left(APIError.CRUDAPIError(400, "The updated ID needs to be the same as the current ID.")))).once()

            whenReady(TestRepositoryService.update("abcd", dataModelWithNewID)) { result =>
                result shouldBe Left(APIError.CRUDAPIError(400, "The updated ID needs to be the same as the current ID."))
            }
        }

        "get a 404 API error" in {
            (mockRepository.update(_: String, _: DataModel)).expects(*, *)
                .returning(Future(Left(APIError.CRUDAPIError(404, "Book not found.")))).once()

            whenReady(TestRepositoryService.update("xyz", updatedDataModel)) { result =>
                result shouldBe Left(APIError.CRUDAPIError(404, "Book not found."))
            }
        }

        "get a 400 API error when the updates contain the same field values" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.update(_: String, _: DataModel)).expects(*, *)
                .returning(Future(Left(APIError.CRUDAPIError(400, "No book fields were updated.")))).once()

            whenReady(TestRepositoryService.update("abcd", dataModel)) { result =>
                result shouldBe Left(APIError.CRUDAPIError(400, "No book fields were updated."))
            }
        }
    }

    "RepositoryService .updateByID()" should {

        "get an updated DataModel book" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.updateByID(_: String, _: String, _: String)).expects(*, *, *)
                .returning(Future(Right(singleUpdatedDataModel))).once()

            whenReady(TestRepositoryService.updateByID("abcd", "name", "new name")) { result =>
                result shouldBe Right(singleUpdatedDataModel)
            }
        }

        "get a 400 APIError when trying to change the ID" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.updateByID(_: String, _: String, _: String)).expects(*, *, *)
                .returning(Future(Left(APIError.CRUDAPIError(400, "Cannot update the ID field.")))).once()

            whenReady(TestRepositoryService.updateByID("abcd", "_id", "new_id")) { result =>
                result shouldBe Left(APIError.CRUDAPIError(400, "Cannot update the ID field."))
            }
        }

        "get a 404 API error" in {
            (mockRepository.updateByID(_: String, _: String, _: String)).expects(*, *, *)
                .returning(Future(Left(APIError.CRUDAPIError(404, "Book not found.")))).once()

            whenReady(TestRepositoryService.updateByID("abcd", "name", "new name")) { result =>
                result shouldBe Left(APIError.CRUDAPIError(404, "Book not found."))
            }
        }

        "get a 400 API error when the update contains the same field value" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.updateByID(_: String, _: String, _: String)).expects(*, *, *)
                .returning(Future(Left(APIError.CRUDAPIError(400, s"Book name is already new name.")))).once()

            whenReady(TestRepositoryService.updateByID("abcd", "name", "new name")) { result =>
                result shouldBe Left(APIError.CRUDAPIError(400, s"Book name is already new name."))
            }
        }
    }

    "RepositoryService .updateByID()" should {

        "get a boolean true if upon successful deletion" in {
            (mockRepository.create(_: DataModel)).expects(*)
                .returning(Future(Right(dataModel))).once()

            whenReady(TestRepositoryService.create(dataModel)) { result =>
                result shouldBe Right(dataModel)
            }

            (mockRepository.delete(_: String)).expects(*)
                .returning(Future(Right(true))).once()

            whenReady(TestRepositoryService.delete(dataModel._id)) { result =>
                result shouldBe Right(true)
            }
        }

        "get a 404 APIError" in {
            (mockRepository.delete(_: String)).expects(*)
                .returning(Future(Left(APIError.CRUDAPIError(404, "Book not found.")))).once()

            whenReady(TestRepositoryService.delete(dataModel._id)) { result =>
                result shouldBe Left(APIError.CRUDAPIError(404, "Book not found."))
            }
        }
    }
}
