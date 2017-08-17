package eu.imind.play.rest.parameters.sorting

import eu.imind.play.rest.request.settings.SettingApplicable
import scala.language.implicitConversions

case class Sorting(sortFields: Seq[SortField]) extends SettingApplicable {

  def :+(sortField: SortField):Sorting = this.copy(sortFields = sortFields :+ sortField)
}

object Sorting {

  implicit def sortFieldIsSingleSorting(field: SortField):Sorting = Sorting(Seq(field))

}
