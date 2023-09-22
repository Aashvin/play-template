package controllers

import baseSpec.BaseSpecWithApplication
import models.DataModel
import play.api.test.FakeRequest
import play.api.http.Status
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Result
import play.api.test.Helpers.{contentAsJson, defaultAwaitTimeout, redirectLocation, status}

import scala.concurrent.Future

class ApplicationControllerSpec extends BaseSpecWithApplication {
    val TestApplicationController: ApplicationController = new ApplicationController(component, repoService, service)

    private val dataModel: DataModel = DataModel(
        "abcd",
        "test name",
        "test description",
        100
    )

    private val dataModel2: DataModel = DataModel(
        "abcd",
        "test update name",
        "test update description",
        50
    )

    "ApplicationController .index()" should {
        val result = TestApplicationController.index()(FakeRequest())

        "return OK" in {
            status(result) shouldBe Status.OK
        }
    }

    "ApplicationController .create" should {

        "create a book in the database" in {
            beforeEach()

            val request: FakeRequest[JsValue] = buildPost("/api").withBody[JsValue](Json.toJson(dataModel))
            val createdResult: Future[Result] = TestApplicationController.create()(request)

            status(createdResult) shouldBe Status.CREATED

            afterEach()
        }

        "handle bad requests" in {
            beforeEach()

            val request: FakeRequest[JsValue] = buildPost("/api").withBody[JsValue](Json.toJson(None))
            val createdResult: Future[Result] = TestApplicationController.create()(request)

            status(createdResult) shouldBe Status.BAD_REQUEST

            afterEach()
        }
    }

    "ApplicationController .read" should {

        "find a book in the database by id" in {
            beforeEach()

            val request: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
            val createdResult: Future[Result] = TestApplicationController.create()(request)

            status(createdResult) shouldBe Status.CREATED

            //Hint: You could use status(createdResult) shouldBe Status.CREATED to check this has worked again

            val readResult: Future[Result] = TestApplicationController.read("abcd")(FakeRequest())

            status(readResult) shouldBe Status.SEE_OTHER
            redirectLocation(readResult) shouldBe Some(s"/viewBook/abcd")

            afterEach()
        }

        "handle bad requests" in {
            beforeEach()

            val request: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
            val createdResult: Future[Result] = TestApplicationController.create()(request)

            status(createdResult) shouldBe Status.CREATED

            val readResult: Future[Result] = TestApplicationController.read("xyz")(FakeRequest())

            status(readResult) shouldBe Status.NOT_FOUND

            afterEach()
        }
    }

    "ApplicationController .update()" should {

        "change the details of a book in the database by id" in {
            beforeEach()

            val createRequest: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
            val createdResult: Future[Result] = TestApplicationController.create()(createRequest)

            status(createdResult) shouldBe Status.CREATED

            val updateRequest: FakeRequest[JsValue] = buildPost("/api").withBody[JsValue](Json.toJson(dataModel2))
            val updateResult: Future[Result] = TestApplicationController.update("abcd")(updateRequest)

            status(updateResult) shouldBe Status.ACCEPTED
            contentAsJson(updateResult).as[DataModel] shouldBe dataModel2

            afterEach()
        }

        "handle bad requests" in {
            beforeEach()

            val createRequest: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
            val createdResult: Future[Result] = TestApplicationController.create()(createRequest)

            status(createdResult) shouldBe Status.CREATED

            val updateRequest: FakeRequest[JsValue] = buildPost("/api").withBody[JsValue](Json.toJson(None))
            val updateResult: Future[Result] = TestApplicationController.update("abcd")(updateRequest)

            status(updateResult) shouldBe Status.BAD_REQUEST

            afterEach()
        }

    }

    "ApplicationController .delete()" should {

        "remove a book in the database by id" in {
            beforeEach()

            val createRequest: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
            val createdResult: Future[Result] = TestApplicationController.create()(createRequest)

            status(createdResult) shouldBe Status.CREATED

            val readResult: Future[Result] = TestApplicationController.read("abcd")(FakeRequest())

            status(readResult) shouldBe Status.SEE_OTHER
            redirectLocation(readResult) shouldBe Some(s"/viewBook/abcd")

            val deleteResult: Future[Result] = TestApplicationController.delete("abcd")(FakeRequest())

            status(deleteResult) shouldBe Status.ACCEPTED

            afterEach()
        }

        "handle bad requests" in {
            beforeEach()

            val createRequest: FakeRequest[JsValue] = buildGet("/api/${dataModel._id}").withBody[JsValue](Json.toJson(dataModel))
            val createdResult: Future[Result] = TestApplicationController.create()(createRequest)

            status(createdResult) shouldBe Status.CREATED

            val deleteResult: Future[Result] = TestApplicationController.delete("xyz")(FakeRequest())

            status(deleteResult) shouldBe Status.NOT_FOUND

            afterEach()
        }

    }

    override def beforeEach(): Unit = repository.deleteAll()
    override def afterEach(): Unit = repository.deleteAll()
}
