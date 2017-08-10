package controllers

import javax.inject._

import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO

import scala.concurrent.{ExecutionContext, Future}
import play.api.mvc._
import eu.imind.play.rest.api._

class VersionedExampleController @Inject()(
   override val rcc: ResourceControllerComponents
)(implicit ec: ExecutionContext) extends AbstractController(rcc) with ResourceController[ExampleDTO, Long] {

  override def post = Action.async { request =>
    request.apiVersion match {
      //@todo make the following examples work and testable
      //case v if v <= "v0.0" =>
      //case v if v in "v2.0" -> "v3.0" =>
      case v if v >= "v1.1" =>
        Future.successful(Ok("above"))
      case _ =>
        Future.successful(Ok("below"))
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
