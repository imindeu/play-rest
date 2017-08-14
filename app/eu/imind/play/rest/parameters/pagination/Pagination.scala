package eu.imind.play.rest.parameters.pagination

import eu.imind.play.rest.parameters.pagination.Pagination.PaginationLimit
import eu.imind.play.rest.request.settings.SettingApplicable

case class Pagination(offset: Int, limit: PaginationLimit) extends SettingApplicable

object Pagination {

  //@todo related toString?
  sealed trait PaginationLimit {
    val value:Option[Int]
  }

  //@todo default should be configurabele
  case object DEFAULT extends PaginationLimit {
    override val value: Option[Int] = None
  }

  case object UNLIMITED extends PaginationLimit {
    override val value: Option[Int] = None
  }

  case class Limit(l: Int) extends PaginationLimit {
    override val value: Option[Int] = Some(l)
  }

}
