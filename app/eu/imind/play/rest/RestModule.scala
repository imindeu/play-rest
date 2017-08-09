package eu.imind.play.rest

import eu.imind.play.rest.controllers.{ResourceActionBuilder, ResourceActionBuilderImpl}
import play.api.inject._
import play.api.{Configuration, Environment}

class RestModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = Seq(
    bind[ResourceActionBuilder].to[ResourceActionBuilderImpl]
  )

}
