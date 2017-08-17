package helpers

import eu.imind.play.rest.api.RestApiConfig
import eu.imind.play.rest.parameters.sorting.SortedRequest
import play.api.mvc.{AnyContentAsEmpty, Request, WrappedRequest}
import play.api.test.FakeRequest

case class FakeSortedRequest[A](request: Request[A])(implicit val config: RestApiConfig)
  extends WrappedRequest[A](request) with SortedRequest[A]

object FakeSortedRequest {

  implicit val restApiConfig = RestApiConfig.forConfig("play-rest")

  def apply(method: String, path: String): FakeSortedRequest[AnyContentAsEmpty.type] =
    FakeSortedRequest(FakeRequest(method, path))
}
