package routers

import javax.inject.{Inject, Singleton}

import controllers.BasicExampleController
import eu.imind.play.rest.controllers.ResourceControllerComponents
import eu.imind.play.rest.routing.VersionedRouter
import play.api.routing.Router.Routes

@Singleton
class V1d2Router @Inject() (
                             exampleController: BasicExampleController,
                             pv: V1d1Router
                           )(implicit val rcc: ResourceControllerComponents) extends VersionedRouter {

  override val versionTag = "v1.2"
  override val previousVersion = Some(pv)

  override def implements:Routes = NOTHING
  override def removes:Routes = NOTHING

}
