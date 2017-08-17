package unit

import eu.imind.play.rest.api.RestApiConfig
import eu.imind.play.rest.parameters.sorting.{Asc, Desc, SortField, Sorting}
import org.scalatestplus.play.PlaySpec

class SortingDSLSpec extends PlaySpec {

  val restApiConfig = RestApiConfig.forConfig("play-rest")

  import restApiConfig.api._

  def sortingIdentity(sorting: Sorting):Sorting = sorting

  "sort fields" must {
    "be createable from a string and a directional function" in {
      "a".asc mustEqual SortField("a", Asc)
      "b".desc mustEqual SortField("b", Desc)
    }
  }

  "a single sort field" must {
    "be convertible to a full-blown sorting parameter" in {
      sortingIdentity("a".asc) mustEqual Sorting(Seq(SortField("a", Asc)))
      sortingIdentity("b".desc) mustEqual Sorting(Seq(SortField("b", Desc)))
    }
  }

  "multiple sort fields" must {
    "combine into a sorting parameter while keeping order" in {
      "a".asc :+ "b".desc mustEqual Sorting(Seq(
        SortField("a", Asc),
        SortField("b", Desc)
      ))

      "b".desc :+ "a".asc mustEqual Sorting(Seq(
        SortField("b", Desc),
        SortField("a", Asc)
      ))


      "a".asc :+ "b".desc :+ "c".desc mustEqual Sorting(Seq(
        SortField("a", Asc),
        SortField("b", Desc),
        SortField("c", Desc)
      ))
    }
  }

}
