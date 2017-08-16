package helpers

import com.google.inject.AbstractModule
import com.mohiva.play.silhouette.api.Environment
import com.mohiva.play.silhouette.test.FakeEnvironment
import models.User
import net.codingwell.scalaguice.ScalaModule
import org.scalatest.TestSuite
import org.scalatestplus.play.{BaseOneAppPerSuite, FakeApplicationFactory}
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import silhouette.DummyEnv

import scala.concurrent.ExecutionContext.Implicits.global

trait SilhouetteApplicationFactory extends FakeApplicationFactory {

  val testUsers = Seq(
    User("test-user")
  )

  implicit val fakeEnv = new FakeEnvironment[DummyEnv](testUsers.map { tu =>
    tu.loginInfo -> tu
  })

  override def fakeApplication(): Application = new GuiceApplicationBuilder()
    .overrides(new AbstractModule with ScalaModule {
      override def configure(): Unit = {
        bind[Environment[DummyEnv]].toInstance(fakeEnv)
      }
    })
    .build()

}

trait SilhouetteOneAppPerSuite extends BaseOneAppPerSuite with SilhouetteApplicationFactory { this: TestSuite =>

}