package controllers

import javax.inject._

import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO
import play.api.mvc.{AbstractController, Action, AnyContent}

import scala.concurrent.ExecutionContext

@Singleton
class BasicExampleController @Inject()(
    override val rcc: ResourceControllerComponents
  )(implicit ec: ExecutionContext) extends AbstractController(rcc) with ResourceController[ExampleDTO, Long] {

  val nonstandard = Action {
    Ok("nst")
  }

  def nonstandardparam(p: String): Action[AnyContent] =
    Action { Ok("nstp: " + p) }

  override def get(id: Long) = Action { Ok("single-id: " + id) }
  override def get = Action { Ok("multi-get") }
  override def patch(id: Long) = Action { Ok("patch: " + id) }
  override def delete(id: Long) = Action { Ok("delete: " + id) }
  override def post = Action { Ok("post") }
  override def put(id: Long) = Action { Ok("put: " + id) }

}
