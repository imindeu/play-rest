package routers

import com.google.inject.{Inject, Singleton}
import controllers.{BasicExampleController, UUIDExampleController, UnsupportedExampleController}
import eu.imind.play.rest.controllers.ResourceControllerComponents
import eu.imind.play.rest.routing.{ResourceRouter, VersionedRouter}
import play.api.routing.Router._
import play.api.routing.sird._

@Singleton
class V1Router @Inject() (
   exampleController: BasicExampleController,
   unsupportedExampleController: UnsupportedExampleController,
   uuidExampleController: UUIDExampleController
 )(implicit val rcc: ResourceControllerComponents) extends VersionedRouter {

  override val versionTag = "v1.0"
  override val previousVersion = None

  def nonStandardActions:Routes = {
    case GET(p"/example/nst") => exampleController.nonstandard
    case GET(p"/example/nst/${p}") => exampleController.nonstandardparam(p)
  }

  override def implements:Routes = nonStandardActions orElse
    new ResourceRouter(exampleController).withPrefix("/example").routes orElse
    new ResourceRouter(unsupportedExampleController).withPrefix("/unsupported").routes orElse
    new ResourceRouter(uuidExampleController).withPrefix("/uuid").routes

  override def removes = NOTHING

}
