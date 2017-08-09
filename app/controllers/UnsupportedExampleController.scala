package controllers

import javax.inject._

import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO
import play.api.mvc.AbstractController

import scala.concurrent.ExecutionContext

@Singleton
class UnsupportedExampleController @Inject()(
  override val rcc: ResourceControllerComponents
)(implicit ec: ExecutionContext) extends AbstractController(rcc) with ResourceController[ExampleDTO, Long] {

  override def get(id: Long) = rcc.UNSUPPORTED
  override def get = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED

}
