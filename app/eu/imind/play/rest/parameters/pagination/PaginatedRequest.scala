package eu.imind.play.rest.parameters.pagination

import eu.imind.play.rest.api.RestApiConfig
import eu.imind.play.rest.parameters.pagination.Pagination.{DEFAULT, Limit}
import play.api.mvc.Request

trait PaginatedRequest[A] { this: Request[A] =>

  val config:RestApiConfig

  def pagination:Pagination = Pagination(

    offset = getQueryString(config.parameterNames.offset)
      .filter(_.forall(_.isDigit))
      .map(_.toInt)
      .getOrElse(0),

    limit = getQueryString(config.parameterNames.limit)
      .filter(_.forall(_.isDigit))
      .map(l => Limit(l.toInt))
      .getOrElse(DEFAULT(config.parameterValues.defaultLimit))
  )
}