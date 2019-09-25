package mf.models 

import scala.util.Try

// Don't touch these for purposes of the exercise. They are deliberately simple.
// Use the parser on the value inside the RawResponse you get back from http client


sealed trait Request 

case class ValidRequest(value: String) extends Request
case object BatchFail extends Request

/* The request we want to build */
// final case class Request(value: String)

/* Raw, unparsed response from the client */
final case class RawResponse(value: String)

/* Successful parsed case */
final case class ParsedResponse(value: Int) 

case object ParsedResponse {
  val parser: String => Try[ParsedResponse] = s => 
  Try(ParsedResponse(s.toInt))
}

final case class BatchRequest(requests: List[Request])
final case class BatchResponse(responses: List[RawResponse])
