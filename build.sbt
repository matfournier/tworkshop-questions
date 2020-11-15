name := "traverse-workshop"

version := "0.2"

scalaVersion := "2.13.3"

val circeVersion = "0.11.1"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.2.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
libraryDependencies += "io.monix" %% "monix" % "3.3.0"
libraryDependencies += "com.ironcorelabs" %% "cats-scalatest" % "3.0.8"

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin(
  "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full
)

fork in run := true
outputStrategy := Some(StdoutOutput)

scalacOptions ++= List(
  "-feature",
  "-language:higherKinds",
  "-Xlint",
  "-Yrangepos",
  "-Ywarn-unused"
)
