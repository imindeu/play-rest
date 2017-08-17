package unit

import eu.imind.play.rest.parameters.pagination.Pagination
import eu.imind.play.rest.parameters.sorting.{Asc, Desc, SortField, Sorting}
import eu.imind.play.rest.request.settings.{DefaultPageSize, DefaultSorting}
import org.scalatestplus.play.PlaySpec

class DefaultSortingSpec extends PlaySpec {

  "the default sorting setting" must {

    "not be applicable to Pagination" in {
      val setting = DefaultSorting(Sorting(Seq(SortField("a", Asc))))

      setting.isApplicableTo[Pagination] mustBe false
    }

    "be applicable to Sorting" in {
      val setting = DefaultSorting(Sorting(Seq(SortField("a", Asc))))

      setting.isApplicableTo[Sorting] mustBe true
    }

    "not be applicable to Filters" ignore {
      //@todo implement
      fail("Implement after filters are implemented")
    }

    "override empty sorting" in {
      val original = Sorting(Seq())
      val setting = DefaultSorting(Sorting(Seq(SortField("a", Asc))))

      setting(original) mustEqual Sorting(Seq(SortField("a", Asc)))
    }

    "not override non-empty sorting" in {
      val original = Sorting(Seq(SortField("b", Desc)))
      val setting = DefaultSorting(Sorting(Seq(SortField("a", Asc))))

      setting(original) mustEqual Sorting(Seq(SortField("b", Desc)))
    }

  }

}
