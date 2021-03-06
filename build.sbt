name := "scaloid-layout-converter"

description := "An Android layout converter from XML to Scaloid DSL"

version := "0.4"

scalaVersion := "2.10.2"

organization := "org.scaloid"

organizationHomepage := Some(new URL("http://blog.scaloid.org"))

scalacOptions ++= Seq("-feature", "-deprecation")


// Dependencies

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io",
  "spray nightlies" at "http://nightlies.spray.io"
)

val scaloidVersion = "2.3-8"
val sprayVersion = "1.2-20130912"
val akkaVersion = "2.2.1"

libraryDependencies ++= Seq(
  "org.scaloid" %% "scaloid" % scaloidVersion,
  "com.google.android" % "android" % "2.2.1" withSources,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.apache.commons" % "commons-lang3" % "3.1",
  "io.spray" % "spray-http" % sprayVersion,
  "io.spray" % "spray-httpx" % sprayVersion,
  "io.spray" % "spray-routing" % sprayVersion,
  "io.spray" % "spray-servlet" % sprayVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.12.v20130726" % "container",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" artifacts Artifact("javax.servlet", "jar", "jar"),
  "org.scalatest" %% "scalatest" % "2.0.M8" % "test",
  "junit" % "junit" % "4.11" % "test"
)

seq(Revolver.settings: _*)

seq(webSettings: _*)

artifactPath in (Compile, packageWar) := {
  (target in Compile).value / "dist" / (artifactPath in (Compile, packageWar)).value.getName
}

fork in Test := true

initialCommands := Seq(
  "import org.scaloid.layout.converter._",
  "import ReflectionUtils._",
  "import StringUtils._"
).mkString("\n")
