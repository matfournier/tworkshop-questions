package mf.exercises

import java.util.concurrent.Executors

import cats.scalatest.EitherValues
import mf.models._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

class Exercise3Test
    extends AnyFunSpec
    with Matchers
    with ScalaFutures
    with EitherValues {

  implicit val ec: ExecutionContextExecutor =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  implicit val defaultPatience: PatienceConfig =
    PatienceConfig(timeout = Span(5, Seconds), interval = Span(1, Millis))

  val br = new Exercise3
  val n = 3

  describe("successful cases") {

    val xs = List(1, 2, 3, 4, 5, 6, 7, 8)
    val requests: List[Request] = xs.map(i => ValidRequest.apply(i.toString))
    val expected = xs.map(ParsedResponse.apply)

    it("Should return the input list, parsed, in order") {
      val result = br.requestBatch(requests, n)
      result.futureValue.toEither.value should contain theSameElementsInOrderAs expected
    }
  }

  describe("unsuccessful cases") {

    val xs1 = List(1, 2, 3, 4).map(i => ValidRequest.apply(i.toString))

    it("should fail the future if any future fails") {
      val xs2 = List(BatchFail, BatchFail, BatchFail)
      val xs3 = List(8, 9, 10).map(i => ValidRequest.apply(i.toString))
      val requests: List[Request] = xs1 ++ xs2 ++ xs3 ++ List(BatchFail)
      val result = br.requestBatch(requests, n)
      result.failed.futureValue.isInstanceOf[Exception]
    }

    it("should accumulate all the failures") {
      val xs2 = List("jdsf", "dsfjsfs", "7", "8").map(ValidRequest.apply)
      val requests: List[Request] = xs1 ++ xs2
      val result = br.requestBatch(requests, n)
      result.futureValue.toEither.leftValue.length shouldBe 2
    }

  }
}
