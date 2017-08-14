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

  def getV1[A](request: Request[A]):Future[Result] = Future.successful(Ok {
    "offset: " + request.pagination.offset + " limit: " + request.pagination.limit.value
  })

  def getV1d1[A](request: ParametrizedRequest[A]):Future[Result] = {
    Future.successful(Ok {
      "offset: " + request.pagination.offset + " limit: " + request.pagination.limit.value
    })
  }

  override def get = Action.async { request =>
    request.apiVersion match {
      //scalastyle:off magic.number
      case v if v >= "v1.1" => getV1d1(request.withSettings(DefaultPageSize(30)))
      //scalastyle:on magic.number
      case _ =>
        getV1(request.withSettings(DefaultPageSize(UNLIMITED)))
        //@todo the following only works because of system default - should do a proper example
        //getV1(request)
    }
  }

  override def get(id: Long) = rcc.UNSUPPORTED
  override def patch(id: Long) = rcc.UNSUPPORTED
  override def delete(id: Long) = rcc.UNSUPPORTED
  override def put(id: Long) = rcc.UNSUPPORTED
  override def post = rcc.UNSUPPORTED

}
