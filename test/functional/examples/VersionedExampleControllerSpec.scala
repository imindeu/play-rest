package functional.examples

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class VersionedExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "the versioned example endpoint" must {

    "respond according to version over API v1 to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "below"
    }

    "respond according to version over API v1.1 to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1.1/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "above"
    }

    "respond with version over API v1 to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "v1.0"
    }

    "respond with version over API v1.0 to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1.0/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "v1.0"
    }

    "respond with version over API v1.1 to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "v1.1"

    }
  }
}