package eu.imind.play.rest.versioning

import play.api.mvc.Request

trait VersionedRequest[A] { this: Request[A] =>

  lazy val apiVersion:ApiVersion = ApiVersion.fromPath(path).getOrElse(ApiVersion.default)

}