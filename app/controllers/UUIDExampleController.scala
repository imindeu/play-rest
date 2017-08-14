package controllers

import java.util.UUID
import javax.inject._

import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO
import play.api.mvc.AbstractController

import scala.concurrent.ExecutionContext

@Singleton
class UUIDExampleController @Inject()(
  override val rcc: ResourceControllerComponents
)(implicit ec: ExecutionContext) extends AbstractController(rcc) with ResourceController[ExampleDTO, UUID] {

  override def get = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED

  override def get(id: UUID) = Action { Ok("single-id: " + id) }
  override def patch(id: UUID) = Action { Ok("patch: " + id) }
  override def delete(id: UUID) = Action { Ok("delete: " + id) }
  override def put(id: UUID) = Action { Ok("put: " + id) }

}