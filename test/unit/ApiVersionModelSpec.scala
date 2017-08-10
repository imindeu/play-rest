package unit

import eu.imind.play.rest.versioning.ApiVersion
import org.scalatestplus.play.PlaySpec

class ApiVersionModelSpec extends PlaySpec {

  "The API Version model" must {
    "parse own version number to major/minor parts" in {
      val v1 = ApiVersion("v1.0")
      val v211 = ApiVersion("v2.11")
      val v0 = ApiVersion("whatever")

      v1.major mustEqual 1
      v1.minor mustEqual 0

      v211.major mustEqual 2
      v211.minor mustEqual 11

      v0.major mustEqual 0
      v0.minor mustEqual 0
    }

    "produce empty version" in {
      ApiVersion.default.major mustEqual 1
      ApiVersion.default.minor mustEqual 0
    }

    "compare properly to compatibility ranges" in {
      val v12 = ApiVersion("v1.2")

      (v12 <= "v1.13") mustEqual true
      (v12 >= "v1.13") mustEqual false

      (v12 <= "v2.0") mustEqual true
      (v12 >= "v2.0") mustEqual false

      (v12 <= "v1.1") mustEqual false
      (v12 >= "v1.1") mustEqual true

      (v12 <= "v1.2") mustEqual true
      (v12 >= "v1.2") mustEqual true

      v12 in ("v1" -> "v2") mustEqual true
      v12 in ("v1.2" -> "v3") mustEqual true
      v12 in ("v1.1" -> "v1.2") mustEqual true
      v12 in ("v1.0" -> "v1.1") mustEqual false
      v12 in ("v1.3" -> "v3.3") mustEqual false
    }

    "be parsable from a request path" in {
      ApiVersion.fromPath("/api/v1/resource").value.major mustEqual 1
      ApiVersion.fromPath("/api/v1/resource").value.minor mustEqual 0

      ApiVersion.fromPath("/api/v1.2/resource").value.major mustEqual 1
      ApiVersion.fromPath("/api/v1.2/resource").value.minor mustEqual 2

      ApiVersion.fromPath("/api/resource") mustBe empty
      ApiVersion.fromPath("/resource") mustBe empty

      ApiVersion.fromPath("/api/v5/resource").value.major mustEqual 5
      ApiVersion.fromPath("/api/v5/resource").value.minor mustEqual 0

      ApiVersion.fromPath("/api/v5").value.major mustEqual 5
      ApiVersion.fromPath("/api/v5").value.minor mustEqual 0

      ApiVersion.fromPath("/api/master/v1/resource").value.major mustEqual 1
      ApiVersion.fromPath("/api/master/v1/resource").value.minor mustEqual 0

      ApiVersion.fromPath("/api/master/v2.0/resource").value.major mustEqual 2
      ApiVersion.fromPath("/api/master/v2.0/resource").value.minor mustEqual 0
    }
  }

}

