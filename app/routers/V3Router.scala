package routers

import javax.inject.{Inject, Singleton}

import eu.imind.play.rest.controllers.ResourceControllerComponents
import eu.imind.play.rest.routing.VersionedRouter
import play.api.routing.Router.Routes

@Singleton
class V3Router @Inject() (
                             pv: V2d5Router
                           )(implicit val rcc: ResourceControllerComponents) extends VersionedRouter {

  override val versionTag = "v3.0"
  override val previousVersion = Some(pv)

  override def implements:Routes = NOTHING
  override def removes:Routes = NOTHING

}
