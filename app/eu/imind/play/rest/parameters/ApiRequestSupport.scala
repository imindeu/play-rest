package eu.imind.play.rest.parameters

import eu.imind.play.rest.api.RestApiConfig
import eu.imind.play.rest.parameters.pagination.PaginatedRequest
import eu.imind.play.rest.parameters.pagination.Pagination.{Limit, PaginationLimit}
import eu.imind.play.rest.request.settings.{RequestSetting, RequestWithSettings, SettingApplicable}
import eu.imind.play.rest.versioning.VersionedRequest
import play.api.mvc.{Request, WrappedRequest}

import scala.language.implicitConversions

trait ApiRequestSupport {

  val config:RestApiConfig

  case class ParametrizedRequest[A](
     settings: Set[RequestSetting[SettingApplicable]],
     request: Request[A]
  )(implicit val config: RestApiConfig) extends WrappedRequest[A](request)
    with RequestWithSettings[A]
    with PaginatedRequest[A]
    with VersionedRequest[A] {

    def withSettings(settings: RequestSetting[SettingApplicable]*):ParametrizedRequest[A] = ParametrizedRequest[A](
      settings = this.settings ++ settings,
      request = this.request
    )

  }

  object ParametrizedRequest {
    //@todo make this type parameter fancier to retain the request type (e.g. SecuredRequest of silhouette)
    def fromRequest[A](request: Request[A]):ParametrizedRequest[A] = request match {
      case r:ParametrizedRequest[_] =>
        r.asInstanceOf[ParametrizedRequest[A]]
      case _ =>
        ParametrizedRequest[A] (
          settings = Set(),
          request = request
        )(config)
    }
  }

  implicit def request2parametrized[A](request: Request[A]):ParametrizedRequest[A] = ParametrizedRequest.fromRequest(request)

  implicit def int2pagintaionLimit(l: Int):PaginationLimit = Limit(l)

}