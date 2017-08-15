package routers

import javax.inject.{Inject, Singleton}

import eu.imind.play.rest.controllers.ResourceControllerComponents
import eu.imind.play.rest.routing.VersionedRouter
import play.api.routing.Router.Routes

@Singleton
class V2d5Router @Inject() (
                             pv: V2Router
                           )(implicit val rcc: ResourceControllerComponents) extends VersionedRouter {

  override val versionTag = "v2.5"
  override val previousVersion = Some(pv)

  override def implements:Routes = NOTHING
  override def removes:Routes = NOTHING

}