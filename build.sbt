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

// mainClass := Some("Bootstrap")

resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % "2.0.0"
	, "org.apache.spark" % "spark-core_2.11" % "1.4.0"
	,  "com.typesafe" % "config" % "1.3.0"
	
	, "junit" % "junit" % "4.8.1" % "test"
	, "org.scalatest" %% "scalatest" % "2.2.6" % "test"
	, "org.specs2" %% "specs2" % "2.4.15" % "test" 
)

	// "org.specs2"    %% "specs2"    % "1.5" % "test"

	// , "org.apache.spark" %% "spark-sql" % "1.3.1"


// libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

// libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.0"


