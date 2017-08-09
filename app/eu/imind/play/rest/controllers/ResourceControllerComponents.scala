package eu.imind.play.rest.controllers

import javax.inject.Inject

import eu.imind.play.rest.headers.ApiResponseHeaders
import play.api.http.FileMimeTypes
import play.api.i18n.{Langs, MessagesApi}
import play.api.mvc._

import scala.concurrent.ExecutionContext

case class ResourceControllerComponents @Inject() (
  actionBuilder: ResourceActionBuilder,
  parsers: PlayBodyParsers,
  messagesApi: MessagesApi,
  langs: Langs,
  fileMimeTypes: FileMimeTypes,
  executionContext: ExecutionContext) extends ControllerComponents {

  val UNSUPPORTED: Action[AnyContent] = actionBuilder {
    Results.MethodNotAllowed
  }

  val ENTITY_NOT_FOUND:Action[AnyContent] = actionBuilder {
    Results.NotFound.withHeaders(ApiResponseHeaders.ENTITY_NOT_FOUND)
  }

}
