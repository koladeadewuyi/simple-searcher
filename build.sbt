import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.kolade.searcher",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "simple-searcher",
    libraryDependencies ++= Seq(
      scalaTest % Test
    ),
    mainClass in (Compile, assembly) := Some("com.kolade.searcher.Searcher")
  )
