package functional.examples

import java.util.UUID

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class UUIDExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "the uuid example controller" must {

    "handle single-item GET" in {
      val uuid = UUID.randomUUID()

      val result = route(app, FakeRequest(GET, "/api/v1/uuid/" + uuid)).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "single-id: " + uuid
    }

    "handle PATCH" in {
      val uuid = UUID.randomUUID()

      val result = route(app, FakeRequest(PATCH, "/api/v1/uuid/" + uuid)).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "patch: " + uuid
    }

    "handle DELETE" in {
      val uuid = UUID.randomUUID()

      val result = route(app, FakeRequest(DELETE, "/api/v1/uuid/" + uuid)).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "delete: " + uuid
    }

    "handle PUT" in {
      val uuid = UUID.randomUUID()

      val result = route(app, FakeRequest(PUT, "/api/v1/uuid/" + uuid)).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "put: " + uuid
    }
  }

}
