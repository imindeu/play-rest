package controllers

import javax.inject.Inject

import eu.imind.play.rest.api.{HasRestApiConfig, RestApiConfig}
import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import models.ExampleDTO
import play.api.mvc.AbstractController

import scala.concurrent.{ExecutionContext, Future}

class SortedExampleController @Inject()(
                                         override val rcc: ResourceControllerComponents,
                                         override val restApiConfig:RestApiConfig
                                       )(implicit ec: ExecutionContext)
  extends AbstractController(rcc)
    with ResourceController[ExampleDTO, Long]
    with HasRestApiConfig {

  override def get = Action.async { request =>
    Future.successful(Ok)
  }

  override def get(id: Long) = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED


}
