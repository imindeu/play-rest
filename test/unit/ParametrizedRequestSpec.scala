package unit

import eu.imind.play.rest.api.RestApiConfig
import eu.imind.play.rest.parameters.pagination.Pagination.UNLIMITED
import eu.imind.play.rest.request.settings.DefaultPageSize
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Request
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ParametrizedRequestSpec extends PlaySpec {

  val restApiConfig = RestApiConfig.forConfig("play-rest")

  import restApiConfig.api._

  def prIdentity[A](pRequest:ParametrizedRequest[A]):ParametrizedRequest[A] = pRequest
  def converterIdentity[A](pRequest:Request[A]):ParametrizedRequest[A] = pRequest

  "the parametrized request model" must {

    "be creatable from a request" in {
      val request = FakeRequest(GET, "/api/resource")

      prIdentity(request) mustEqual ParametrizedRequest(Set(), request)
    }

    "take settings data" in {
      val request = FakeRequest(GET, "/api/resource").withSettings(DefaultPageSize(UNLIMITED))

      request.settings must contain only DefaultPageSize(UNLIMITED)
    }

    "keep settings data while reconverting" in {
      val request = FakeRequest(GET, "/api/resource").withSettings(DefaultPageSize(UNLIMITED))

      prIdentity(request).settings must contain only DefaultPageSize(UNLIMITED)
      converterIdentity(request).settings must contain only DefaultPageSize(UNLIMITED)
    }
  }

}
