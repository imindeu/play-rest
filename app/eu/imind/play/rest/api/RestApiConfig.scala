package eu.imind.play.rest.api

import javax.inject.Inject

import com.typesafe.config.{Config, ConfigFactory}
import eu.imind.play.rest.parameters.ApiRequestSupport
import play.api.Configuration

case class ParameterNames(
    offset:String,
    limit:String,
    sort:String
  )

class RestApiConfig (
  val parameterNames: ParameterNames
) { restApiConfig =>

  trait API
    extends ApiRequestSupport {

    override implicit val config: RestApiConfig = restApiConfig

  }

  val api: API = new API {}

}

object RestApiConfig {

  def conf(config: Config):RestApiConfig = new RestApiConfig(
    parameterNames = ParameterNames(
      offset = config.getString("paramaters.offset"),
      limit = config.getString("parameters.limit"),
      sort = config.getString("parameters.sort")
    )
  )

  def forConfig(path: String, config: Config = ConfigFactory.load()):RestApiConfig =
    conf(config.getConfig(path))
}

//@todo this seems like a redundancy. Can we remove this?
class DefaultRestApiConfig extends RestApiConfig(
  parameterNames = ParameterNames(
    offset = "offset",
    limit = "limit",
    sort = "sort"
  )
)