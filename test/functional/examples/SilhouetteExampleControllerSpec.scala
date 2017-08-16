package functional.examples

import com.mohiva.play.silhouette.test._
import helpers.SilhouetteOneAppPerSuite
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

  }

}
