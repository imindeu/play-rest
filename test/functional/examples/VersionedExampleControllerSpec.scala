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
      contentAsString(result) mustEqual "1.1 or lower"
    }

    "respond according to version over API v1.1 to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1.1/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "1.1 or lower"
    }

    "respond according to version over API v1.2 to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1.2/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "1.2 or higher"
    }

    "respond according to version over API v2.0 to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v2.0/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "between 2.0 and 3.0"
    }

    "respond according to version over API v2.5 to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v2.5/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "between 2.0 and 3.0"
    }

    "respond according to version over API v3.0 to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v3.0/versioned")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "between 2.0 and 3.0"
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