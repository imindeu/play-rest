package eu.imind.play.rest.routing

import eu.imind.play.rest.controllers.ResourceControllerComponents
import eu.imind.play.rest.headers.ApiResponseHeaders
import play.api.mvc.{Action, AnyContent, Results}
import play.api.routing.Router._
import play.api.routing.SimpleRouter

import scala.language.implicitConversions

//@todo BIG add an appropriate DSL for ease of coding here
trait VersionedRouter extends SimpleRouter {

  val rcc: ResourceControllerComponents
  val versionTag:String
  def previousVersion:Option[VersionedRouter]

  protected val NOTHING:Routes = PartialFunction.empty

  def implements:Routes
  def removes:Routes

  private def notFound:Routes = {
    case _ => RESOURCE_NOT_FOUND
  }

  override def routes =
    implements orElse removes orElse previousVersion.map(_.routes).getOrElse(NOTHING) orElse notFound

  val REMOVED: Action[AnyContent] = rcc.actionBuilder {
    Results.Gone.withHeaders(ApiResponseHeaders.REMOVED_SINCE(versionTag))
  }

  val RESOURCE_NOT_FOUND: Action[AnyContent] = rcc.actionBuilder {
    Results.NotFound.withHeaders(ApiResponseHeaders.RESOURCE_NOT_FOUND)
  }

}
