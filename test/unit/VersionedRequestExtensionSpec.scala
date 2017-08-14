package unit

import eu.imind.play.rest.versioning.ApiVersion
import helpers.FakeVersionedRequest
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers._

class VersionedRequestExtensionSpec extends PlaySpec {

  "The version request extension" must {

    "enable apiVersion to extract from a non-versioned path" in {
      val request = FakeVersionedRequest(GET, "/api/resource")

      request.apiVersion mustEqual ApiVersion.default
    }

    "enable apiVersion to extract from a versioned path" in {
      val request = FakeVersionedRequest(GET, "/api/v4.2/resource")

      request.apiVersion mustEqual ApiVersion("v4.2")
    }

  }

}
