package eu.imind.play.rest.parameters.pagination

import eu.imind.play.rest.parameters.pagination.Pagination.PaginationLimit
import eu.imind.play.rest.request.settings.SettingApplicable
import scala.language.implicitConversions

case class Pagination(offset: Int, limit: PaginationLimit) extends SettingApplicable

object Pagination {

  sealed trait PaginationLimit {
    val value:Option[Int]
  }

  object PaginationLimit {
    implicit def intIsPagintaionLimit(l: Int):PaginationLimit = Limit(l)
    implicit def longIsPagintaionLimit(l: Long):PaginationLimit = Limit(l.toInt)
  }

  case class DEFAULT(limit: PaginationLimit) extends PaginationLimit {
    override val value: Option[Int] = limit.value

    override def toString: String = limit.toString
  }

  case object UNLIMITED extends PaginationLimit {
    override val value: Option[Int] = None
  }

  case class Limit(l: Int) extends PaginationLimit {
    override val value: Option[Int] = Some(l)
  }

}
