package unit

import eu.imind.play.rest.parameters.pagination.Pagination
import eu.imind.play.rest.parameters.sorting.{Asc, Desc, SortField, Sorting}
import eu.imind.play.rest.request.settings.CaseInsensitiveSorting
import org.scalatestplus.play.PlaySpec

class CaseInsensitiveSortingSpec extends PlaySpec {

  "not be applicable to Pagination" in {
    val setting = CaseInsensitiveSorting

    setting.isApplicableTo[Pagination] mustBe false
  }

  "be applicable to Sorting" in {
    val setting = CaseInsensitiveSorting

    setting.isApplicableTo[Sorting] mustBe true
  }

  "not be applicable to Filters" ignore {
    //@todo implement
    fail("Implement after filters are implemented")
  }

  "handle lowercase sorting" in {
    val original = Sorting(Seq(SortField("bbb", Desc)))
    val setting = CaseInsensitiveSorting

    setting(original) mustEqual Sorting(Seq(SortField("bbb", Desc)))
  }

  "handle uppercase sorting" in {
    val original = Sorting(Seq(SortField("BBB", Desc)))
    val setting = CaseInsensitiveSorting

    setting(original) mustEqual Sorting(Seq(SortField("bbb", Desc)))
  }

  "handle mixed case sorting" in {
    val original = Sorting(Seq(SortField("bBb", Desc)))
    val setting = CaseInsensitiveSorting

    setting(original) mustEqual Sorting(Seq(SortField("bbb", Desc)))
  }
}
