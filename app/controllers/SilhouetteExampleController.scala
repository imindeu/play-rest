package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{HandlerResult, Silhouette}
import eu.imind.play.rest.api.{HasRestApiConfig, RestApiConfig}
import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO
import play.api.mvc.AbstractController
import silhouette.DummyEnv

import scala.concurrent.{ExecutionContext, Future}

class SilhouetteExampleController @Inject()(
                                             override val rcc: ResourceControllerComponents,
                                             override val restApiConfig:RestApiConfig,
                                             silhouette: Silhouette[DummyEnv]
                                           )(implicit ec: ExecutionContext)
  extends AbstractController(rcc)
    with ResourceController[ExampleDTO, Long]
    with HasRestApiConfig {

  import restApiConfig.api._

  //@todo this would be the best looks
  /*override def get = silhouette.SecuredAction.async { implicit request =>
    Future.successful {
      val user = request.authenticator.loginInfo.providerKey
      val v = request.apiVersion
      Ok("user: " + user + " version: " + v)
    }
  }*/

  override def get = Action.async { implicit request =>
    silhouette.SecuredRequestHandler { securedRequest =>
      val user = securedRequest.authenticator.loginInfo.providerKey
      val v = request.apiVersion
      Future.successful(HandlerResult(
        result = Ok("user: " + user + " version: " + v)
      ))
    }.map {
      case HandlerResult(result, _) => result
    }
  }

  override def get(id: Long) = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
}
