package modules

import com.google.inject.{AbstractModule, Provides}
import com.mohiva.play.silhouette.api.{Environment, EventBus, Silhouette, SilhouetteProvider}
import com.mohiva.play.silhouette.impl.authenticators.DummyAuthenticatorService
import models.UserIdentityService
import net.codingwell.scalaguice.ScalaModule
import silhouette.DummyEnv

import scala.concurrent.ExecutionContext

class SilhouetteModule extends AbstractModule with ScalaModule {

  override def configure() = {
    bind[Silhouette[DummyEnv]].to[SilhouetteProvider[DummyEnv]]
  }

  @Provides
  def provideAuthenticatorService(implicit ec: ExecutionContext):DummyAuthenticatorService = {
    new DummyAuthenticatorService()
  }

  @Provides
  def provideEnvironment (
     identityService: UserIdentityService,
     authenticatorService: DummyAuthenticatorService,
     eventBus: EventBus
  )(implicit ec: ExecutionContext):Environment[DummyEnv] = Environment[DummyEnv](
    identityService,
    authenticatorService,
    Seq(),
    eventBus
  )

}
