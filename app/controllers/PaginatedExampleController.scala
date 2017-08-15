package controllers

import javax.inject._

import eu.imind.play.rest.api.{HasRestApiConfig, RestApiConfig}
import eu.imind.play.rest.controllers.{ResourceController, ResourceControllerComponents}
import eu.imind.play.rest.parameters.pagination.Pagination.UNLIMITED
import eu.imind.play.rest.request.settings.DefaultPageSize
import models.ExampleDTO
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class PaginatedExampleController @Inject()(
                                            override val rcc: ResourceControllerComponents,
                                            override val restApiConfig:RestApiConfig
                                          )(implicit ec: ExecutionContext)
  extends AbstractController(rcc)
  with ResourceController[ExampleDTO, Long]
  with HasRestApiConfig {

  import restApiConfig.api._

  private def getV1[A](request: Request[A]):Future[Result] = Future.successful(Ok {
    "offset: " + request.pagination.offset + " limit: " + request.pagination.limit
  })

  private def getV1d1[A](request: Request[A]):Future[Result] = {
    Future.successful(Ok {
      "offset: " + request.pagination.offset + " limit: " + request.pagination.limit
    })
  }

  private def getV1d2[A](request: Request[A]):Future[Result] = Future.successful(Ok {
    "offset: " + request.pagination.offset + " limit: " + request.pagination.limit
  })

  override def get = Action.async { request =>
    request.apiVersion match {
      case v if v >= "v1.2" => getV1d2(request)
      //scalastyle:off magic.number
      case v if v >= "v1.1" => getV1d1(request.withSettings(DefaultPageSize(30)))
      //scalastyle:on magic.number
      case _ =>
        getV1(request.withSettings(DefaultPageSize(UNLIMITED)))
    }
  }

  override def get(id: Long) = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED

}
