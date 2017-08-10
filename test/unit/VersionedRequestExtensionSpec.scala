package unit

import eu.imind.play.rest.versioning.ApiVersion
import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers._

import eu.imind.play.rest.versioning.VersionedRequestExtension._

class VersionedRequestExtensionSpec extends PlaySpec {

  "The version request extension" must {

    "enable apiVersion to extract from a non-versioned path" in {
      val request = FakeRequest(GET, "/api/resource")

      request.apiVersion mustEqual ApiVersion.default
    }

    "enable apiVersion to extract from a versioned path" in {
      val request = FakeRequest(GET, "/api/v4.2/resource")

      request.apiVersion mustEqual ApiVersion("v4.2")
    }

  }

}
