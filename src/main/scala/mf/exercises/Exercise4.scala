package mf.exercises

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
import mf.models._
import mf.http.Http

/**
  * Should ignore network failures
  * Should ignore parsing failures
  * @param ec
  */
class Exercise4(implicit ec: ExecutionContext) {
  def requestBatch(requests: List[Request], batchSize: Int): Future[List[ParsedResponse]] = {
    val batches = requests.grouped(batchSize).toList.map(BatchRequest.apply)
    ???
  }
}
