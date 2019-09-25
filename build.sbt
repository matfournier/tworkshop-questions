name := "traverse-workshop"

version := "0.1"

scalaVersion := "2.12.8"

val circeVersion = "0.11.1"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0-RC1"
libraryDependencies += "org.typelevel" %% "cats-effect" % "1.3.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "io.monix" %% "monix" % "3.0.0"

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")

fork in run := true
outputStrategy := Some(StdoutOutput)

scalacOptions ++= List(
  "-feature",
  "-language:higherKinds",
  "-Xlint",
  "-Yrangepos",
  "-Ywarn-unused",
  "-Ypartial-unification"
)

