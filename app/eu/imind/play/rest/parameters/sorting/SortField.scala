package eu.imind.play.rest.parameters.sorting

import scala.language.implicitConversions

case class SortField(field: String, direction: SortDirection)

class SortFieldBuilder(field: String) {
  def asc:SortField = SortField(field, Asc)
  def desc:SortField = SortField(field, Desc)
}

object SortFieldBuilder {

  implicit def stringIsSortFieldBuilder(field: String):SortFieldBuilder = new SortFieldBuilder(field)

}
