package models

import com.mohiva.play.silhouette.api.{Identity, LoginInfo}
import com.mohiva.play.silhouette.api.services.IdentityService
import com.google.inject.Singleton

import scala.concurrent.Future

case class User(id: String) extends Identity

@Singleton
class UserIdentityService extends IdentityService[User] {

  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = Future.successful {
    Some(User(loginInfo.providerKey))
  }

}
