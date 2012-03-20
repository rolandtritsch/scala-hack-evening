organization := "com.simundi"

name := "scala-tour"

version := "1.0"

scalaVersion := "2.9.1"

retrieveManaged := true

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test"
)

resolvers ++= Seq(ScalaToolsReleases, ScalaToolsSnapshots, DefaultMavenRepository)
