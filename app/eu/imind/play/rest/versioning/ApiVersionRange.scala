package eu.imind.play.rest.versioning

import scala.language.implicitConversions

case class ApiVersionRange(lowest: ApiVersion, highest: Option[ApiVersion] = None)

object ApiVersionRange {

  // @todo check if use case exists
  implicit def StringTuple2ApiVersionRange(t: (String, String)):ApiVersionRange =
    ApiVersionRange(ApiVersion(t._1), Some(ApiVersion(t._2)))

  implicit def StringIsApiVersionRange(s: String):ApiVersionRange = {
    ApiVersionRange(ApiVersion(s))
  }

}
