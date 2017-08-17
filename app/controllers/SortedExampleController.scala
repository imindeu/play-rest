package controllers

import javax.inject.Inject

import eu.imind.play.rest.api.{HasRestApiConfig, RestApiConfig}
import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import eu.imind.play.rest.request.settings.DefaultSorting
import models.ExampleDTO
import play.api.mvc.{AbstractController, Request, Result}

import scala.concurrent.{ExecutionContext, Future}

class SortedExampleController @Inject()(
                                         override val rcc: ResourceControllerComponents,
                                         override val restApiConfig:RestApiConfig
                                       )(implicit ec: ExecutionContext)
  extends AbstractController(rcc)
    with ResourceController[ExampleDTO, Long]
    with HasRestApiConfig {

  import restApiConfig.api._

  private def getV1[A](request: Request[A]):Future[Result] = Future.successful(Ok {
    "sorting: " + request.sorting
  })

  private def getV1d1[A](request: Request[A]):Future[Result] = Future.successful(Ok {
    "sorting: " + request.sorting
  })

  override def get = Action.async { request =>
    request.apiVersion match {
      case v if v >= "v1.1" => getV1d1(request.withSettings(DefaultSorting("b".desc :+ "a".asc)))
      case _ => getV1(request)
    }
  }

  override def get(id: Long) = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED


}
