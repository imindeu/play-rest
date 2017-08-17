package eu.imind.play.rest.parameters.sorting

sealed trait SortDirection

object Asc extends SortDirection
object Desc extends SortDirection
