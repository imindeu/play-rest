package unit

import eu.imind.play.rest.parameters.sorting.{Asc, Desc, SortField, Sorting}
import org.scalatestplus.play.PlaySpec

class SortingSpec extends PlaySpec {

  "the sorting model" must {

    "convert to string properly when empty" in {
      val sorting = Sorting(Seq())

      sorting.toString mustEqual "empty sorting"
    }

    "convert to string properly when has one field" in {
      val sortingA = Sorting(Seq(SortField("a", Asc)))
      val sortingB = Sorting(Seq(SortField("b", Desc)))

      sortingA.toString mustEqual "+a"
      sortingB.toString mustEqual "-b"
    }

    "convert to string properly when has multiple fields" in {
      val sorting = Sorting(Seq(
        SortField("a", Desc),
        SortField("b", Asc),
        SortField("c", Desc)
      ))

      sorting.toString mustEqual "-a, +b, -c"
    }
  }

}
