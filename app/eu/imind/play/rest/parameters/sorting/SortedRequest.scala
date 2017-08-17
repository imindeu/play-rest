package eu.imind.play.rest.parameters.sorting

import eu.imind.play.rest.api.RestApiConfig
import play.api.mvc.Request

trait SortedRequest[A] { this: Request[A] =>

  implicit class StringIsSortParam(str: String) {
    def parseSort:SortField = str.charAt(0) match {
      case '+' => SortField(field = str.substring(1), Asc)
      case '-' => SortField(field = str.substring(1), Desc)
      case _ => SortField(field = str, Asc)
    }
  }

  val config:RestApiConfig

  def sorting:Sorting =
    getQueryString(config.parameterNames.sort).map(
      _.split(config.parameterSettings.sortDelimitier)
      .filter(_.nonEmpty)
      .map(_.parseSort)
    )
    .map(Sorting(_))
    .getOrElse(Sorting(Seq()))
}
