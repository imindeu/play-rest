package eu.imind.play.rest.headers

object ApiResponseHeaders {

  private val X_ERROR_DETAILS:String = "X-Error-Details"

  val RESOURCE_NOT_FOUND:(String, String) = X_ERROR_DETAILS -> "Resource not found"
  val ENTITY_NOT_FOUND:(String, String) = X_ERROR_DETAILS -> "Entity not found"

  def REMOVED_SINCE(tag: String):(String, String) = "X-Removed-Since" -> tag
  def DEPRECATION_WARNING(tag: String):(String, String) = "X-Deprecation-Warning" -> tag

  val CLACKS_OVERHEAD:(String, String) = "X-Clacks-Overhead" -> "GNU Terry Pratchett"

}
