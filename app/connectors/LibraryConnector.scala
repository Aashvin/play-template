package connectors

import cats.data.EitherT
import models.{APIError, DataModel, GoogleBook}
import org.bson.json.JsonObject
import play.api.libs.json.{JsReadable, JsValue, OFormat}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.ws._

class LibraryConnector @Inject()(ws: WSClient) {
    def get[Response](url: String)(implicit rds: OFormat[Response], ec: ExecutionContext): EitherT[Future, APIError, DataModel] = {
        val request = ws.url(url)
        val response = request.get()
        EitherT {
            response.map {
                result => {
                    val googleBook = result.json("items")(0).as[GoogleBook]
                    Right(DataModel(googleBook.id, googleBook.volumeInfo.title, googleBook.searchInfo.textSnippet, googleBook.volumeInfo.pageCount))
//                    Right(result.json("items")(0).as[GoogleBook])
                }
            }.recover {
                case _: WSResponse => Left(APIError.BadAPIResponse(500, "Could not connect"))
            }
        }
    }
}
