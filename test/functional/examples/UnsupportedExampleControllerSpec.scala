package functional.examples

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class UnsupportedExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "the unsupported controller" must {

    "not respond to single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/unsupported/5")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
      contentAsString(result) mustBe empty
    }

    "not respond to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/unsupported")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
      contentAsString(result) mustBe empty
    }

    "not respond to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1/unsupported")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
      contentAsString(result) mustBe empty

    }

    "not respond to PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1/unsupported/5")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
      contentAsString(result) mustBe empty

    }

    "not respond to PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1/unsupported/5")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
      contentAsString(result) mustBe empty

    }

    "not respond to DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1/unsupported/5")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
      contentAsString(result) mustBe empty

    }

  }

}
