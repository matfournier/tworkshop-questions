package mf.exercises

import cats.implicits._
import mf.http.Http
import mf.models._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Should fail if any call to Http.getBatch fails
  * Should fail if any BatchResponse.response element fails to parse into a ParsedResponse using the
  * ParsedResponse.parser
  */
class Exercise1(implicit ec: ExecutionContext) {
  def requestBatch(
      requests: List[Request],
      batchSize: Int
  ): Future[Either[ServiceError, List[ParsedResponse]]] = {
    val batches = requests.grouped(batchSize).toList.map(BatchRequest.apply)
    batches
      .traverse(Http.getBatch)
      .map(_.flatMap(r => r.responses).map(_.value).map(ParsedResponse.parser))
      .map(_.traverse(_.toEither))
      .map(_.leftMap(ex => ServiceError(ex.getMessage)))
  }
}
