name := "CadiroBro"

version := "0.1"

scalaVersion := "2.13.2"

//description =

//compile/mainClass = ""

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.0",
  "com.typesafe.play" %% "play-json" % "2.7.4",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "ch.qos.logback"  % "logback-classic" % "1.2.3",
  "org.apache.httpcomponents" % "httpclient" % "4.5.12",
)

crossPaths := false

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "org.mockito" % "mockito-core" % "3.3.0" % Test,
  "org.scalatest" % "scalatest_2.13" % "3.1.1" % Test
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")