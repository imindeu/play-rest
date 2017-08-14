package helpers

import eu.imind.play.rest.api.{DefaultRestApiConfig, RestApiConfig}
import eu.imind.play.rest.parameters.pagination.PaginatedRequest
import play.api.mvc.{AnyContentAsEmpty, Request, WrappedRequest}
import play.api.test.FakeRequest

case class FakePaginatedRequest[A](request: Request[A])(implicit val config: RestApiConfig)
  extends WrappedRequest[A](request) with PaginatedRequest[A]

object FakePaginatedRequest {

  implicit val restApiConfig = new DefaultRestApiConfig()

  def apply(method: String, path: String): FakePaginatedRequest[AnyContentAsEmpty.type] =
    FakePaginatedRequest(FakeRequest(method, path))
}

