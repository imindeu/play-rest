package functional.examples

import helpers.IDGenerator
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.test.Helpers.{DELETE, GET, PATCH, POST, PUT, route, status}

//@todo make sure that the + - urlencoding relation is proper here
class SortedExampleControllerSpec extends PlaySpec with GuiceOneAppPerSuite {
  
  "the sorted example endpoint over API v1" must {

    "handle missing sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: empty sorting"
    }

    "handle empty sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted?sort=")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: empty sorting"
    }

    "handle one nonprefixed sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted?sort=a")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: +a"
    }

    "handle one + prefixed sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted?sort=%2Ba")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: +a"
    }

    "handle one - prefixed sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted?sort=-a")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: -a"
    }

    "handle multiple nonprefixed sort params" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted?sort=a,b,c")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: +a, +b, +c"
    }

    "handle multiple prefixed sort params" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted?sort=-a,%2Bb,%2Bc")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: -a, +b, +c"
    }

    "handle multiple variously prefixed sort params" in {
      val result = route(app, FakeRequest(GET, "/api/v1/sorted?sort=a,-b,%2Bc")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: +a, -b, +c"
    }

    "handle default with missing sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/sorted")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: -b, +a"
    }

    "handle default with empty sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/sorted?sort=")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: -b, +a"
    }

    "handle default with set sort param" in {
      val result = route(app, FakeRequest(GET, "/api/v1.1/sorted?sort=c")).get

      status(result) mustEqual OK
      contentAsString(result) mustEqual "sorting: +c"
    }

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
