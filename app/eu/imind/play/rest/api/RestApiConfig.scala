package eu.imind.play.rest.api

import javax.inject.Inject

import com.typesafe.config.{Config, ConfigException, ConfigFactory}
import eu.imind.play.rest.parameters.ApiRequestSupport
import eu.imind.play.rest.parameters.pagination.Pagination
import eu.imind.play.rest.parameters.pagination.Pagination.{PaginationLimit, UNLIMITED}
import eu.imind.play.rest.parameters.sorting.SortFieldBuilderImplicits

import scala.util.Try

case class ParameterNames(
    offset:String,
    limit:String,
    sort:String
  )

case class ParameterValues(
    defaultLimit: PaginationLimit
  )

case class ParameterSettings(
    sortDelimitier: String
  )

class RestApiConfig (
  val parameterNames: ParameterNames,
  val parameterValues: ParameterValues,
  val parameterSettings: ParameterSettings
) { restApiConfig =>

  trait API
    extends ApiRequestSupport
    with SortFieldBuilderImplicits {

    override implicit val config: RestApiConfig = restApiConfig

  }

  val api: API = new API {}

}

object RestApiConfig {

  def conf(config: Config):RestApiConfig = new RestApiConfig(
    parameterNames = ParameterNames(
      offset = config.getString("parameters.offset"),
      limit = config.getString("parameters.limit"),
      sort = config.getString("parameters.sort")
    ),
    parameterValues = ParameterValues(
      defaultLimit = Try {
        Pagination.DEFAULT(Pagination.Limit(config.getInt("defaults.limit")))
      }
      .recover {
        case ex: ConfigException.WrongType if config.getString("defaults.limit").toLowerCase() == "unlimited" =>
            Pagination.DEFAULT(Pagination.UNLIMITED)
      }
      .get
    ),
    parameterSettings = ParameterSettings(
      sortDelimitier = config.getString("settings.sort-delimiter")
    )
  )

  def forConfig(path: String, config: Config = ConfigFactory.load()):RestApiConfig =
    conf(config.getConfig(path))
}

//@todo this could be simplified maybe? - omitted even
class RestApiConfigImpl @Inject() (config: Config) extends RestApiConfig(
  RestApiConfig.conf(config.getConfig("play-rest")).parameterNames,
  RestApiConfig.conf(config.getConfig("play-rest")).parameterValues,
  RestApiConfig.conf(config.getConfig("play-rest")).parameterSettings
)