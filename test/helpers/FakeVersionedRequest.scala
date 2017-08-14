package helpers

import eu.imind.play.rest.versioning.VersionedRequest
import play.api.mvc.{AnyContentAsEmpty, Request, WrappedRequest}
import play.api.test.FakeRequest

case class FakeVersionedRequest[A](request: Request[A]) extends WrappedRequest[A](request) with VersionedRequest[A]

object FakeVersionedRequest {

  def apply(method: String, path: String): FakeVersionedRequest[AnyContentAsEmpty.type] =
    FakeVersionedRequest(FakeRequest(method, path))

}