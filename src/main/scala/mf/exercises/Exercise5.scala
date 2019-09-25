package mf.exercises

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
import mf.models._
import mf.http.Http

/**
  * Should not stop for any Http.getBatch failures
  * Should fail if any BatchResponse.response element fails to parse
  * Should collect all parsing failures and all network failures
  */
class Exercise5(implicit ec: ExecutionContext) {
  def requestBatch(requests: List[Request], batchSize: Int): Future[(List[ServiceError], List[ParsedResponse])] = {
    val batches = requests.grouped(batchSize).toList.map(BatchRequest.apply)
    ???
  }

}

