package functional.examples

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ClacksOverheadSpec extends PlaySpec with GuiceOneAppPerSuite {

  "The X-Clacks-Overhead header" must {

    "be present for valid requests" in {
      val result = route(app, FakeRequest(GET, "/api/v1/example")).get

      header("X-Clacks-Overhead", result).value mustEqual "GNU Terry Pratchett"
    }

    "be present for not found resources" in {
      val result = route(app, FakeRequest(GET, "/api/v1/nonexistent")).get

      header("X-Clacks-Overhead", result).value mustEqual "GNU Terry Pratchett"
    }

    "be present for invalid requests" in {
      val result = route(app, FakeRequest(PUT, "/api/v1/example")).get

      header("X-Clacks-Overhead", result).value mustEqual "GNU Terry Pratchett"
    }
  }

}
