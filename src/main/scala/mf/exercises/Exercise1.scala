package mf.exercises

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
import mf.models._
import mf.http.Http

/**
  * Should fail if any call to Http.getBatch fails
  * Should fail if any BatchResponse.response element fails to parse into a ParsedResponse using the
  * ParsedResponse.parser
  */
class Exercise1(implicit ec: ExecutionContext) {
  def requestBatch(requests: List[Request], batchSize: Int): Future[Either[ServiceError, List[ParsedResponse]]] = {
    val batches = requests.grouped(batchSize).toList.map(BatchRequest.apply)
    ???
  }
}