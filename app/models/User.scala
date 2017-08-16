package models

import com.google.inject.Singleton
import com.mohiva.play.silhouette.api.services.IdentityService
import com.mohiva.play.silhouette.api.{Identity, LoginInfo}
import com.mohiva.play.silhouette.impl.authenticators.DummyAuthenticatorService
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider

import scala.concurrent.Future

case class User(id: String) extends Identity {

  lazy val loginInfo = LoginInfo(CredentialsProvider.ID, id)

}

@Singleton
class UserIdentityService extends IdentityService[User] {

  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = Future.successful {
    Some(User(loginInfo.providerKey))
  }

}
