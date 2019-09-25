package mf.http

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import mf.models._
import monix.eval.Task

/* Don't look too closely and don't change this, it's just to make test cases easy */ 
object Http { 
  def getBatch(batch: BatchRequest)(implicit ec: ExecutionContext): Future[BatchResponse] =
    Future(BatchResponse(batch.requests.map(handleRequest)))

  private def handleRequest(request: Request): RawResponse =
    request match {
      case v: ValidRequest => RawResponse(v.value)
      case BatchFail       => throw new Exception("Http Error")
    }

  def getBatchTask(batch: BatchRequest): Task[BatchResponse] =
    Task(BatchResponse(batch.requests.map(handleRequest)))
}