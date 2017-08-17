package unit

import eu.imind.play.rest.parameters.sorting.{Asc, Desc, SortField, Sorting}
import helpers.FakeSortedRequest
import org.scalatest.Inside
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers._

//@todo make sure that the + - urlencoding relation is proper here
class SortedRequestSpec extends PlaySpec with Inside {

  "the sorted request extension" must {

    "extract sorting info whe no parameter is present" in {
      val request = FakeSortedRequest(GET, "/api/resource")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields mustBe empty
      }
    }

    "extract sorting info when empty parameter is present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields mustBe empty
      }
    }

    "extract sorting info when non-prefixed parameter is present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=a")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields must contain only SortField("a", Asc)
      }
    }

    "extract sorting info when + prefixed parameter is present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=%2Ba")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields must contain only SortField("a", Asc)
      }
    }

    "extract sorting info when - prefixed parameter is present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=-a")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields must contain only SortField("a", Desc)
      }
    }

    "extract sorting info when 2 non-prefixed parameters are present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=a,b")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields must contain inOrderOnly (SortField("a", Asc), SortField("b", Asc))
      }
    }

    "extract sorting info when 2 prefixed parameters are present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=-a,%2Bb")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields must contain inOrderOnly (SortField("a", Desc), SortField("b", Asc))
      }
    }

    "extract sorting info when mixed prefixed parameters are present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=a,%2Bb")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields must contain inOrderOnly (SortField("a", Asc), SortField("b", Asc))
      }
    }

    "extract sorting info when multiple parameters are present" in {
      val request = FakeSortedRequest(GET, "/api/resource?sort=a,%2Bb,-c")

      inside(request.sorting) {
        case Sorting(fields) =>
          fields must contain inOrderOnly (SortField("a", Asc), SortField("b", Asc), SortField("c", Desc))
      }
    }


  }

}
