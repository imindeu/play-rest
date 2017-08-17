package eu.imind.play.rest.request.settings

import eu.imind.play.rest.parameters.pagination.{PaginatedRequest, Pagination}
import eu.imind.play.rest.parameters.sorting.{SortedRequest, Sorting}
import play.api.mvc.Request

trait RequestWithSettings[A] extends PaginatedRequest[A] with SortedRequest[A] { this: Request[A] =>
  val settings: Set[RequestSetting[SettingApplicable]]

  override lazy val pagination:Pagination =
    settings
      .filter(_.isApplicableTo[Pagination])
      .foldLeft(super.pagination) { (pagination, setting) => setting match {
        case s:PaginationSetting => s.apply(pagination)
        case _ => pagination
      }
    }

  override lazy val sorting:Sorting =
    settings
      .filter(_.isApplicableTo[Sorting])
      .foldLeft(super.sorting) { (sorting, setting) => setting match {
        case s:SortingSetting => s.apply(sorting)
        case _ => sorting
      }
    }
}
