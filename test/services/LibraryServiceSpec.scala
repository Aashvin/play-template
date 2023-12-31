package services

import baseSpec.BaseSpec
import cats.data.EitherT
import connectors.LibraryConnector
import models.{APIError, DataModel}
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.{JsValue, Json, OFormat}

import scala.concurrent.{ExecutionContext, Future}

class LibraryServiceSpec extends BaseSpec with MockFactory with ScalaFutures with GuiceOneAppPerSuite {
    val mockConnector: LibraryConnector = mock[LibraryConnector]
    implicit val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
    val testService = new LibraryService(mockConnector)

    val gameOfThrones: JsValue = Json.obj(
        "_id" -> "someId",
        "name" -> "A Game of Thrones",
        "description" -> "The best book!!!",
        "numSales" -> 100
    )

    "getGoogleBook" should {
        val url: String = "testUrl"

        "return a book" in {
            (mockConnector.getAsDataModel[DataModel](_: String)(_: OFormat[DataModel], _: ExecutionContext))
                .expects(url, *, *)
                .returning(EitherT.rightT[Future, APIError](gameOfThrones.as[DataModel]))
                .once()

            whenReady(testService.getGoogleBookAsDataModel(urlOverride = Some(url), search = "", term = "").value) { result =>
                result shouldBe Right(DataModel("someId", "A Game of Thrones", "The best book!!!", 100))
            }
        }

        "return an error" in {
            val url: String = "testUrl"

            (mockConnector.getAsDataModel[DataModel](_: String)(_: OFormat[DataModel], _: ExecutionContext))
                .expects(url, *, *)
                .returning(EitherT.leftT[Future, DataModel](APIError.BadAPIResponse(400, "Could not connect"))) // How do we return an error?
                .once()

            whenReady(testService.getGoogleBookAsDataModel(urlOverride = Some(url), search = "", term = "").value) { result =>
                result shouldBe Left(APIError.BadAPIResponse(400, "Could not connect"))
            }
        }
    }
}
