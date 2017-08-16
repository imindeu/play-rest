package modules

import com.google.inject.{AbstractModule, Provides}
import com.mohiva.play.silhouette.api.repositories.AuthenticatorRepository
import com.mohiva.play.silhouette.api.services.AuthenticatorService
import com.mohiva.play.silhouette.api.util.{Clock, IDGenerator}
import com.mohiva.play.silhouette.api.{Environment, EventBus, Silhouette, SilhouetteProvider}
import com.mohiva.play.silhouette.impl.authenticators.{BearerTokenAuthenticator, BearerTokenAuthenticatorService, BearerTokenAuthenticatorSettings}
import com.mohiva.play.silhouette.impl.util.SecureRandomIDGenerator
import models.UserIdentityService
import net.codingwell.scalaguice.ScalaModule
import play.api.Configuration
import silhouette.{DummyEnv, DummyRepository}

import scala.concurrent.ExecutionContext

class SilhouetteModule(environment: play.api.Environment, configuration: Configuration) extends AbstractModule with ScalaModule {

  override def configure() = {
    bind[Silhouette[DummyEnv]].to[SilhouetteProvider[DummyEnv]]
    bind[AuthenticatorRepository[BearerTokenAuthenticator]].to[DummyRepository]
    bind[Clock].toInstance(Clock())
  }

  @Provides
  def provideIdGenerator(implicit ec: ExecutionContext):IDGenerator =
    new SecureRandomIDGenerator()

  @Provides
  def provideAuthenticatorService(
                                 repository: AuthenticatorRepository[BearerTokenAuthenticator],
                                 idGenerator: IDGenerator,
                                 clock: Clock
                                 )(implicit ec: ExecutionContext):AuthenticatorService[BearerTokenAuthenticator] = {
    new BearerTokenAuthenticatorService(
      BearerTokenAuthenticatorSettings(),
      repository,
      idGenerator,
      clock
    )
  }

  @Provides
  def provideEnvironment (
     identityService: UserIdentityService,
     authenticatorService: AuthenticatorService[BearerTokenAuthenticator],
     eventBus: EventBus
  )(implicit ec: ExecutionContext):Environment[DummyEnv] = Environment[DummyEnv](
    identityService,
    authenticatorService,
    Seq(),
    eventBus
  )

}
