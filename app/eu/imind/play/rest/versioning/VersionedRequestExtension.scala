package eu.imind.play.rest.versioning

import play.api.mvc.Request

trait VersionedRequestExtension {

  implicit class VersionedRequest[A](r: Request[A]) {
    lazy val apiVersion:ApiVersion = ApiVersion.fromPath(r.path).getOrElse(ApiVersion.default)
  }

}

object VersionedRequestExtension extends VersionedRequestExtension
