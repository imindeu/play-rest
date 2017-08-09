package functional.examples

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by silex on 2017.08.09..
  */
class NonexistingControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "the nonexisting controller" must {

    "not respond to single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/nonexisting/5")).get

      status(result) mustEqual NOT_FOUND
      header("X-Error-Details", result).value mustEqual "Resource not found"
      contentAsString(result) mustBe empty
    }

    "not respond to multi-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/nonexisting")).get

      status(result) mustEqual NOT_FOUND
      header("X-Error-Details", result).value mustEqual "Resource not found"
      contentAsString(result) mustBe empty
    }

    "not respond to POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1/nonexisting")).get

      status(result) mustEqual NOT_FOUND
      header("X-Error-Details", result).value mustEqual "Resource not found"
      contentAsString(result) mustBe empty

    }

    "not respond to PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1/nonexisting/5")).get

      status(result) mustEqual NOT_FOUND
      header("X-Error-Details", result).value mustEqual "Resource not found"
      contentAsString(result) mustBe empty

    }

    "not respond to PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1/nonexisting/5")).get

      status(result) mustEqual NOT_FOUND
      header("X-Error-Details", result).value mustEqual "Resource not found"
      contentAsString(result) mustBe empty

    }

    "not respond to DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1/nonexisting/5")).get

      status(result) mustEqual NOT_FOUND
      header("X-Error-Details", result).value mustEqual "Resource not found"
      contentAsString(result) mustBe empty

    }

  }

}
