package silhouette

import com.mohiva.play.silhouette.api.Env
import com.mohiva.play.silhouette.impl.authenticators.DummyAuthenticator
import models.User

class DummyEnv extends Env {
  type I = User
  type A = DummyAuthenticator
}
