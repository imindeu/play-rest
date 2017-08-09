
name := """play-rest"""
organization := "eu.imind"

version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-feature", "-deprecation")

libraryDependencies ++= Seq(
  guice,
  jdbc,
  "com.mohiva" %% "play-silhouette" % "5.0.0-RC2",
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.typesafe.play" % "play-json_2.12" % "2.6.2",

  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test,
  "com.h2database" % "h2" % "1.4.193" % Test,
  "com.mohiva" %% "play-silhouette-testkit" % "5.0.0-RC2" % Test
)

resolvers += Resolver.jcenterRepo //silhouette needs this

(scalastyleConfig in Test) := baseDirectory.value / "scalastyle-test-config.xml"

coverageExcludedPackages := "controllers.Reverse.*;controllers.javascript;router;"
coverageMinimum := 80
coverageFailOnMinimum := true
