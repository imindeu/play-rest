package eu.imind.play.rest.controllers

import javax.inject.Inject

import eu.imind.play.rest.headers.ApiResponseHeaders
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

trait ResourceActionBuilder extends ActionBuilder[Request, AnyContent]

class ResourceActionBuilderImpl (
    parser: BodyParser[AnyContent]
  )(implicit executionContext: ExecutionContext) extends ActionBuilderImpl(parser) with ResourceActionBuilder {

  @Inject
  def this(parser: BodyParsers.Default)(implicit ec: ExecutionContext) = this(parser: BodyParser[AnyContent])

  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]):Future[Result] =
    block(request).map(_.withHeaders(ApiResponseHeaders.CLACKS_OVERHEAD))

}
