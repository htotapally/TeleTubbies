lazy val commonSettings = Seq(
  organization := "tele.tubbies",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "TeleTubbies"
  )

mainClass := Some("Bootstrap")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.0"

resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
   "junit" % "junit" % "4.8.1" % "test",
   "org.scalatest" %% "scalatest" % "2.2.6" % "test",
   // "org.specs2"    %% "specs2"    % "1.5" % "test"
   "org.specs2" %% "specs2" % "2.4.15" % "test" 
)
