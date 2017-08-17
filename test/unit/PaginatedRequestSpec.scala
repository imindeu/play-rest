package unit

import eu.imind.play.rest.parameters.pagination.Pagination
import helpers.FakePaginatedRequest
import org.scalatest.Inside
import org.scalatestplus.play.PlaySpec
import play.api.test.Helpers._

class PaginatedRequestSpec extends PlaySpec with Inside {

  "the pagination request extension" must {

    "extract pagination info when nothing is present" in {
      val request = FakePaginatedRequest(GET, "/api/resource")

      inside(request.pagination) {
        case Pagination(offset, limit) =>
          offset mustEqual 0
          limit.value mustBe empty
      }
    }

    "extract pagination info when only limit is present" in {
      val request = FakePaginatedRequest(GET, "/api/resource?limit=5")

      inside(request.pagination) {
        case Pagination(offset, limit) =>
          offset mustEqual 0
          limit.value.value mustEqual 5
      }
    }

    "extract pagination info when limit is NaN" in {
      val request = FakePaginatedRequest(GET, "/api/resource?limit=apple")

      inside(request.pagination) {
        case Pagination(offset, limit) =>
          offset mustEqual 0
          limit.value mustBe empty
      }
    }

    "extract pagination info when only offset is present" in {
      val request = FakePaginatedRequest(GET, "/api/resource?offset=30")

      inside(request.pagination) {
        case Pagination(offset, limit) =>
          offset mustEqual 30
          limit.value mustBe empty
      }
    }

    "extract pagination info when offset is NaN" in {
      val request = FakePaginatedRequest(GET, "/api/resource?offset=apple")

      inside(request.pagination) {
        case Pagination(offset, limit) =>
          offset mustEqual 0
          limit.value mustBe empty
      }
    }

    "extract pagination info for full setup" in {
      val request = FakePaginatedRequest(GET, "/api/resource?offset=30&limit=10")

      inside(request.pagination) {
        case Pagination(offset, limit) =>
          offset mustEqual 30
          limit.value.value mustEqual 10
      }
    }

  }

}
