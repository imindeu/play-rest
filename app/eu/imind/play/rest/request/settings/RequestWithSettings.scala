package eu.imind.play.rest.request.settings

import eu.imind.play.rest.parameters.pagination.{PaginatedRequest, Pagination}
import play.api.mvc.Request
import DefaultPageSize._

trait RequestWithSettings[A] extends PaginatedRequest[A] { this: Request[A] =>
  val settings: Set[RequestSetting[SettingApplicable]]

  override lazy val pagination:Pagination = settings
    .filter(_.isApplicableTo[Pagination])
    .foldLeft(super.pagination) { (pagination, setting) => setting match {
        case s:PaginationSetting => s.apply(pagination)
        case _ => pagination
      }
    }
}
