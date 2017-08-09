package functional.examples

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by silex on 2017.08.08..
  */
class BasicExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "the example controller over API v1" must {

    "respond to nonstandard without param" in {
      val result = route(app, FakeRequest(GET, "/api/v1/example/nst")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "nst"
    }

    "respond to nonstandard with param" in {
      val result = route(app, FakeRequest(GET, "/api/v1/example/nst/p44")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "nstp: p44"
    }

    "respond to single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/example/123")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "single-id: 123"
    }

    "respond to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/example")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "multi-get"
    }

    "respond to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1/example")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "post"
    }

    "respond to PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1/example/45")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "patch: 45"
    }

    "respond to PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1/example/11")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "put: 11"
    }

    "respond to DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1/example/44")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "delete: 44"
    }
  }

  "the example controller over API v1.0" must {

    "respond to nonstandard without param" in {
      val result = route(app, FakeRequest(GET, "/api/v1.0/example/nst")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "nst"
    }

    "respond to nonstandard with param" in {
      val result = route(app, FakeRequest(GET, "/api/v1.0/example/nst/p44")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "nstp: p44"
    }

    "respond to single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1.0/example/123")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "single-id: 123"
    }

    "respond to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1.0/example")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "multi-get"
    }

    "respond to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1.0/example")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "post"
    }

    "respond to PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1.0/example/45")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "patch: 45"
    }

    "respond to PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1.0/example/11")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "put: 11"
    }

    "respond to DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1.0/example/44")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "delete: 44"
    }
  }

  "the example controller over API v1.1" must {

    "not respond to nonstandard without param" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/example/nst")).get

      status(result) mustEqual GONE
      header("X-Removed-Since", result).value mustEqual "v1.1"
      contentAsString(result) mustBe empty
    }

    "respond to nonstandard with param" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/example/nst/p44")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "nstp: p44"
    }

    "respond to single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/example/123")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "single-id: 123"
    }

    "respond to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/example")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "multi-get"
    }

    "respond to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1.1/example")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "post"
    }

    "respond to PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1.1/example/45")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "patch: 45"
    }

    "respond to PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1.1/example/11")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "put: 11"
    }

    "respond to DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1.1/example/44")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "delete: 44"
    }
  }

}
