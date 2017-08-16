package functional.examples

import com.mohiva.play.silhouette.test._
import helpers.{IDGenerator, SilhouetteOneAppPerSuite}
import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers._

class SilhouetteExampleControllerSpec extends PlaySpec with SilhouetteOneAppPerSuite {

  "The silhouette example endpoint over API v1.0" must {

    "handle request without authentication" in {
      val result = route(app, FakeRequest(GET, "/api/v1.0/silhouette")).get

      status(result) mustEqual UNAUTHORIZED
      header("X-Clacks-Overhead", result).value mustEqual "GNU Terry Pratchett"
    }

    "handle request with authentication" in {
      val result = route(app, FakeRequest(GET, "/api/v1.0/silhouette").withAuthenticator(testUsers.head.loginInfo)).get

      status(result) mustEqual OK
      header("X-Clacks-Overhead", result).value mustEqual "GNU Terry Pratchett"
      contentAsString(result) mustEqual "user: test-user version: v1.0"
    }

    "not support single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/silhouette/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1/silhouette/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1/silhouette/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1/silhouette/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1/silhouette")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

  }

}
