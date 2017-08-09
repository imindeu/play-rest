package eu.imind.play.rest.routing

import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import play.api.mvc.{PathBindable, RequestHeader}
import play.api.routing.Router._
import play.api.routing.sird._
import play.api.routing.{Router, SimpleRouter}

class ResourceRouter[DTO, ID](
    controller: ResourceController[DTO, ID]
  )(implicit pb:PathBindable[ID], rcc: ResourceControllerComponents) extends SimpleRouter {

  val extractor = new PathBindableExtractor[ID]

  override def withPrefix(prefix: String) = {
    val prefixMatcher = Function.unlift({rh: RequestHeader =>
      if (rh.path.startsWith(prefix)) {
        val p = if (prefix.endsWith("/")) prefix else prefix + "/"
        val cleaned = rh.path.drop(p.length - 1)
        val path = if (cleaned.isEmpty) "/" else cleaned
        Some(rh.withTarget(rh.target.withPath(path)))
      }
      else {
        None
      }
    })
    Router.from(prefixMatcher.andThen(routes))
  }

  override def routes: Routes = {
    case POST(p"/") => controller.post
    case PUT(p"/${extractor(id)}") => controller.put(id)
    case PATCH(p"/${extractor(id)}") => controller.patch(id)
    case GET(p"/") => controller.get
    case GET(p"/${extractor(id)}") => controller.get(id)
    case DELETE(p"/${extractor(id)}") => controller.delete(id)
    //@todo handle OPTIONS verb
    case _ => rcc.UNSUPPORTED
  }

}
