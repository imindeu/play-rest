package eu.imind.play.rest.controllers

import play.api.mvc.{Action, AnyContent}

trait ResourceController[DTO, ID] {

  val rcc:ResourceControllerComponents

  def get:Action[AnyContent]
  def get(id: ID):Action[AnyContent]
  def post:Action[AnyContent]
  def put(id: ID):Action[AnyContent]
  def patch(id: ID):Action[AnyContent]
  def delete(id: ID):Action[AnyContent]



  /**
    * @todo actionBuilder features
    *       * withResource (aka json(/xml/soap/etc) body - file upload should be handled)
    *       * with queryParameters
    *       * response should be restricted to Writeable[DTO], not to specific format eg JSON value
    */

}
