package controllers

import javax.inject._

import eu.imind.play.rest.api._
import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class VersionedExampleController @Inject()(
   override val rcc: ResourceControllerComponents,
   override val restApiConfig:RestApiConfig
)(implicit ec: ExecutionContext) extends AbstractController(rcc)
  with ResourceController[ExampleDTO, Long]
  with HasRestApiConfig {

  import restApiConfig.api._

  override def post = Action.async { request =>
    request.apiVersion match {
      case v if v in "v2.0" -> "v3.0" =>
        Future.successful(Ok("between 2.0 and 3.0"))
      case v if v >= "v1.2" =>
        Future.successful(Ok("1.2 or higher"))
      case v if v <= "v1.1" =>
        Future.successful(Ok("1.1 or lower"))
      case _ =>
        Future.successful(Ok("rest"))
    }
  }

  override def get = Action.async { request =>
    Future.successful(Ok(request.apiVersion.toString))
  }

  override def get(id: Long) = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED

}
