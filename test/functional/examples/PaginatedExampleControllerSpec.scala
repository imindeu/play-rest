package functional.examples

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class PaginatedExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "the paginated example endpoint over API v1" must {

    "handle no pagination parameters" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: None"
    }

    "handle only offset parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated?offset=10")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: None"
    }

    "handle only limit parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated?limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: Some(20)"
    }

    "handle offset and limit parameter simultanously" in {
      val result = route(app, FakeRequest(GET, "/api/v1/paginated?offset=10&limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: Some(20)"
    }
  }

  "the paginated example endpoint over API v1.1 (programmatic default limit)" must {

    "handle no pagination parameters" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: Some(30)"
    }

    "handle only offset parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated?offset=10")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: Some(30)"
    }

    "handle only limit parameter" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated?limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 0 limit: Some(20)"
    }

    "handle offset and limit parameter simultanously" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/paginated?offset=10&limit=20")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "offset: 10 limit: Some(20)"
    }
  }

}
