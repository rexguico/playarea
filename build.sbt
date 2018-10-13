name := """playarea"""
organization := "com.test"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += javaJdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
libraryDependencies ++= Seq(
  ehcache
)
libraryDependencies ++= Seq(
  ws
)
libraryDependencies += "org.mockito" % "mockito-core" % "2.23.0" % "test"