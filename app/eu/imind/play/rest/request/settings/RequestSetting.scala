package eu.imind.play.rest.request.settings

import eu.imind.play.rest.parameters.pagination.Pagination
import eu.imind.play.rest.parameters.pagination.Pagination._
import eu.imind.play.rest.parameters.sorting.Sorting

import scala.language.implicitConversions
import scala.reflect.runtime.universe._

trait SettingApplicable

sealed abstract class RequestSetting[+A : TypeTag] {

  def isApplicableTo[T : TypeTag]:Boolean = typeOf[T] match {
    case t if t =:= typeOf[A] => true
    case _ => false
  }

}

sealed trait ApplicableSetting[A] {
  def apply(in: A):A
}

trait PaginationSetting extends ApplicableSetting[Pagination]
trait SortingSetting extends ApplicableSetting[Sorting]

case class DefaultPageSize(limit: PaginationLimit) extends RequestSetting[Pagination] with PaginationSetting {
  override def apply(in: Pagination): Pagination = in match {
    case Pagination(offset, _: DEFAULT) => Pagination(offset, limit)
    case pagination => pagination
  }

}

case class DefaultSorting(sorting: Sorting) extends RequestSetting[Sorting] with SortingSetting {
  override def apply(in: Sorting): Sorting = in match {
    case Sorting(fields) if fields.isEmpty => sorting
    case original => original
  }
}

//@todo make this setting available as a global configuration
case object CaseInsensitiveSorting extends RequestSetting[Sorting] with SortingSetting {
  override def apply(in: Sorting): Sorting =
    in.copy(sortFields = in.sortFields.map(sf => sf.copy(field = sf.field.toLowerCase)))
}

