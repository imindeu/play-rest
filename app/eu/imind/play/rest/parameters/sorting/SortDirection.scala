package eu.imind.play.rest.parameters.sorting

sealed trait SortDirection

object Asc extends SortDirection {
  override def toString = "+"
}

object Desc extends SortDirection {
  override def toString = "-"
}
