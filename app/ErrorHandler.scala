import com.mongodb.MongoWriteException
import play.api.http.HttpErrorHandler
import play.api.mvc.{RequestHeader, Result}
import play.api.libs.json.Json
import play.api.mvc.Results._

import scala.concurrent._
import javax.inject.Singleton

@Singleton
class ErrorHandler extends HttpErrorHandler {
    def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
        Future.successful(Status(statusCode)("A client error occurred: " + message))
    }

    def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
        exception match {
            case _: MongoWriteException => Future.successful(Status(400)(Json.toJson("A book with this ID already exists.")))
            case _ => Future.successful(InternalServerError("A server error occurred: " + exception.getMessage))
        }
    }
}
