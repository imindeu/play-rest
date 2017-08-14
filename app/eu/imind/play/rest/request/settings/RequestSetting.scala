package eu.imind.play.rest.request.settings

import eu.imind.play.rest.parameters.pagination
import eu.imind.play.rest.parameters.pagination.{PaginatedRequest, Pagination}
import eu.imind.play.rest.parameters.pagination.Pagination._

import scala.reflect.ClassTag
import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._
import scala.language.implicitConversions

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

case class DefaultPageSize(limit: PaginationLimit) extends RequestSetting[Pagination] with PaginationSetting {
  override def apply(in: Pagination): Pagination = in match {
    case Pagination(offset, originalLimit) if originalLimit == DEFAULT => Pagination(offset, limit)
    case pagination => pagination
  }

}
