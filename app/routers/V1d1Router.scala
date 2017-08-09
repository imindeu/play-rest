package routers

import javax.inject._

import controllers.BasicExampleController
import eu.imind.play.rest.controllers.ResourceControllerComponents
import eu.imind.play.rest.routing.VersionedRouter
import play.api.routing.Router._
import play.api.routing.sird._

@Singleton
class V1d1Router @Inject() (
                             exampleController: BasicExampleController,
                             pv: V1Router
                         )(implicit val rcc: ResourceControllerComponents) extends VersionedRouter {

  override val versionTag = "v1.1"
  override val previousVersion = Some(pv)

  override def implements:Routes = NOTHING

  override def removes = {
    case GET(p"/example/nst") => REMOVED
  }

}

