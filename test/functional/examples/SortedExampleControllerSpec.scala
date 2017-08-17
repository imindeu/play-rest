package functional.examples

import helpers.IDGenerator
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.test.Helpers.{DELETE, GET, PATCH, POST, PUT, route, status}

class SortedExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {
  
  "the sorted example endpoint over API v1" must {
    
    "not support single-item GET" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support PATCH" in {
      val result = route(app, FakeRequest(PATCH, "/api/v1/sorted/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support DELETE" in {
      val result = route(app, FakeRequest(DELETE, "/api/v1/sorted/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support PUT" in {
      val result = route(app, FakeRequest(PUT, "/api/v1/sorted/" + IDGenerator.randomLong())).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }

    "not support POST" in {
      val result = route(app, FakeRequest(POST, "/api/v1/sorted")).get

      status(result) mustEqual METHOD_NOT_ALLOWED
    }
  }

}
