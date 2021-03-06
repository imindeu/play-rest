package functional.examples

import helpers.IDGenerator
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class PaginatedExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "the paginated example endpoint over API v1" must {

    "handle no pagination parameters" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: UNLIMITED"
    }

    "handle only offset parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated?offset=10")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: UNLIMITED"
    }

    "handle only limit parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated?limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: Limit(20)"
    }

    "handle offset and limit parameter simultanously" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated?offset=10&limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: Limit(20)"
    }
  }

  "the paginated example endpoint over API v1.1 (programmatic default limit)" must {

    "handle no pagination parameters" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: Limit(30)"
    }

    "handle only offset parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated?offset=10")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: Limit(30)"
    }

    "handle only limit parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated?limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: Limit(20)"
    }

    "handle offset and limit parameter simultanously" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated?offset=10&limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: Limit(20)"
    }
  }

  "the paginated example endpoint over API v1.2 (configured default limit)" must {

    "handle no pagination parameters" in {
      val result = route(app, FakeRequest(GET, "/api/v1.2/paginated")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: UNLIMITED"
    }

    "handle only offset parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1.2/paginated?offset=10")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: UNLIMITED"
    }

    "handle only limit parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1.2/paginated?limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: Limit(20)"
    }

    "handle offset and limit parameter simultanously" in {
      val result = route(app, FakeRequest(GET, "/api/v1.2/paginated?offset=10&limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: Limit(20)"
    }

    "not support single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1/paginated/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1/paginated/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1/paginated/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1/paginated")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

  }
}
