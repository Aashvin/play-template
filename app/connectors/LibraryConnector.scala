package connectors

import cats.data.EitherT
import models.{APIError, Book}
import play.api.libs.json.OFormat

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.ws._

class LibraryConnector @Inject()(ws: WSClient) {
    def get[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext): EitherT[Future, APIError, Book] = {
        val request = ws.url(url)
        val response = request.get()
        EitherT {
            response
                .map {
                    result =>
                        Right(result.json.as[Book])
                }
                .recover { case _: WSResponse =>
                    Left(APIError.BadAPIResponse(500, "Could not connect"))
                }
        }
    }
}
