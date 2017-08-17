package unit

import eu.imind.play.rest.parameters.pagination.Pagination.{DEFAULT, Limit, PaginationLimit, UNLIMITED}
import org.scalatestplus.play.PlaySpec

class PaginationLimitSpec extends PlaySpec {

  def pLimitIdentity(pl: PaginationLimit):PaginationLimit = pl

  "the pagination limit model" must {

    "be implicitly creatable from int values" in {
      val a:Int = 10
      val b:Int = 30

      pLimitIdentity(a) mustEqual Limit(a)
      pLimitIdentity(b) mustEqual Limit(b)
    }
  }

  "be implicitly creatable from long values" in {
    val a:Long = 10
    val b:Long = 30

    pLimitIdentity(a) mustEqual Limit(a)
    pLimitIdentity(b) mustEqual Limit(b)
  }

  "the DEFAULT pagination limit must be transparent for the toString method" in {
    val l = 40

    DEFAULT(l).toString mustEqual Limit(l).toString
    DEFAULT(UNLIMITED).toString mustEqual UNLIMITED.toString
  }

}
