package eu.imind.play.rest.parameters.pagination

import eu.imind.play.rest.parameters.pagination.Pagination.PaginationLimit
import eu.imind.play.rest.request.settings.SettingApplicable
import scala.language.implicitConversions

case class Pagination(offset: Int, limit: PaginationLimit) extends SettingApplicable

object Pagination {

  sealed trait PaginationLimit {
    val value:Option[Long]
  }

  object PaginationLimit {
    implicit def intIsPagintaionLimit(l: Int):PaginationLimit = Limit(l)
    implicit def longIsPagintaionLimit(l: Long):PaginationLimit = Limit(l)
  }

  case class DEFAULT(limit: PaginationLimit) extends PaginationLimit {
    override val value: Option[Long] = limit.value

    override def toString: String = limit.toString
  }

  case object UNLIMITED extends PaginationLimit {
    override val value: Option[Long] = None
  }

  case class Limit(l: Long) extends PaginationLimit {
    override val value: Option[Long] = Some(l)
  }

}
