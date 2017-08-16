package silhouette

import com.mohiva.play.silhouette.api.repositories.AuthenticatorRepository
import com.mohiva.play.silhouette.api.{Env, LoginInfo}
import com.mohiva.play.silhouette.impl.authenticators.BearerTokenAuthenticator
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import models.User
import org.joda.time.DateTime

import scala.concurrent.Future

class DummyEnv extends Env {
  type I = User
  type A = BearerTokenAuthenticator
}

class DummyRepository extends AuthenticatorRepository[BearerTokenAuthenticator] {
  override def find(id: String): Future[Option[BearerTokenAuthenticator]] = Future.successful {
    Some(BearerTokenAuthenticator(
      id,
      LoginInfo(CredentialsProvider.ID, id),
      new DateTime(),
      new DateTime().plusYears(1),
      None
    ))
  }

  override def add(authenticator: BearerTokenAuthenticator): Future[BearerTokenAuthenticator] = Future.successful {
    authenticator
  }

  override def update(authenticator: BearerTokenAuthenticator): Future[BearerTokenAuthenticator] = Future.successful {
    authenticator
  }

  override def remove(id: String): Future[Unit] = Future.successful {
    ()
  }
}
