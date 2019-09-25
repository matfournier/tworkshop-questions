package mf.exercises

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
import mf.models._
import mf.http.Http

class Exercise2(implicit ec: ExecutionContext) {
  def requestBatch(requests: List[Request], batchSize: Int): Future[List[ParsedResponse]] = {
    val batches = requests.grouped(batchSize).toList.map(BatchRequest.apply)
    ???
  }
}

