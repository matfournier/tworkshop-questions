package mf.exercises

import cats.data.ValidatedNel

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
import mf.models._
import mf.http.Http

/**
  * Should fail if any network call to Http.getbatch fails
  * Should fail if any BatchResponse.response element fails to parse
  * Should collect all parsing failures
  */
class Exercise3(implicit ec: ExecutionContext) {
  def requestBatch(requests: List[Request], batchSize: Int): Future[ValidatedNel[ServiceError, List[ParsedResponse]]] = {
    val batches = requests.grouped(batchSize).toList.map(BatchRequest.apply)
    ???
  }
}
