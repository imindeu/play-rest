package unit

import eu.imind.play.rest.parameters.sorting.{Asc, Desc, SortField}
import org.scalatestplus.play.PlaySpec

class SortFieldSpec extends PlaySpec {

  "the sort field model" must {

    "convert to string properly when ascending" in {
      SortField("aa", Asc).toString mustEqual "+aa"
    }

    "convert to string properly when descending" in {
      SortField("xCx", Desc).toString mustEqual "-xCx"
    }

  }

}
