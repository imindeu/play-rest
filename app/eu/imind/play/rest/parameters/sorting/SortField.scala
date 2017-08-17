package eu.imind.play.rest.parameters.sorting

import scala.language.implicitConversions

case class SortField(field: String, direction: SortDirection)

object SortField {

  implicit class AddableSortField(sortField: SortField) {
    def :+(that: SortField):Sorting = Sorting(Seq(sortField, that))
  }
}

class SortFieldBuilder(field: String) {
  def asc:SortField = SortField(field, Asc)
  def desc:SortField = SortField(field, Desc)
}

trait SortFieldBuilderImplicits {
  implicit def stringIsSortFieldBuilder(field: String):SortFieldBuilder = new SortFieldBuilder(field)
}

object SortFieldBuilder extends SortFieldBuilderImplicits