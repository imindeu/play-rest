package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.Silhouette
import eu.imind.play.rest.api.{HasRestApiConfig, RestApiConfig}
import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO
import play.api.mvc.AbstractController
import silhouette.DummyEnv

import scala.concurrent.ExecutionContext

class SilhouetteExampleController @Inject()(
                                             override val rcc: ResourceControllerComponents,
                                             override val restApiConfig:RestApiConfig,
                                             silhouette: Silhouette[DummyEnv]
                                           )(implicit ec: ExecutionContext)
  extends AbstractController(rcc)
    with ResourceController[ExampleDTO, Long]
    with HasRestApiConfig {
  
  override def get = rcc.UNSUPPORTED
  override def get(id: Long) = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
}
