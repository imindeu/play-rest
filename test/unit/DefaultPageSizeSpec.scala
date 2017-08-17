package unit

import eu.imind.play.rest.parameters.pagination.Pagination
import eu.imind.play.rest.parameters.pagination.Pagination.{DEFAULT, Limit, UNLIMITED}
import eu.imind.play.rest.parameters.sorting.Sorting
import eu.imind.play.rest.request.settings.DefaultPageSize
import org.scalatestplus.play.PlaySpec

class DefaultPageSizeSpec extends PlaySpec {

  "the default page size setting" must {

    "be applicable to Pagination" in {
      val size = 30
      val setting = DefaultPageSize(size)

      setting.isApplicableTo[Pagination] mustBe true
    }

    "not be applicable to Sorting" in {
      val size = 30
      val setting = DefaultPageSize(size)

      setting.isApplicableTo[Sorting] mustBe false
    }

    "not be applicable to Filters" ignore {
      //@todo implement
      fail("Implement after filters are implemented")
    }

    "override original pagination if it is the default" in {
      val original = Pagination(0, DEFAULT(UNLIMITED))
      val size = 30
      val setting = DefaultPageSize(size)

      setting(original).limit mustEqual Limit(size)
    }

    "override limited page size with unlimited" in {
      val size = 30
      val original = Pagination(0, DEFAULT(size))
      val setting = DefaultPageSize(UNLIMITED)

      setting(original).limit mustEqual UNLIMITED
    }

    "not override original pagination if it was explicitly set" in {
      val originalSize = 40
      val original = Pagination(0, Limit(originalSize))
      val size = 30
      val setting = DefaultPageSize(size)

      setting(original).limit mustEqual Limit(originalSize)
    }

  }

}
