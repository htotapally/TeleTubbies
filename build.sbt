lazy val commonSettings = Seq(
  organization := "tele.tubbies",
  version := "0.1.0",
  scalaVersion := "2.11.8",
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "TeleTubbies"
    , mainClass := Some("tele.tubbies.BabyNames")
    
  )



// resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"

resolvers ++= Seq(
  "JBoss Repository" at "http://repository.jboss.org/nexus/content/repositories/releases/",
  "Spray Repository" at "http://repo.spray.cc/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Twitter4J Repository" at "http://twitter4j.org/maven2/",
  "Apache HBase" at "https://repository.apache.org/content/repositories/releases",
  "Twitter Maven Repo" at "http://maven.twttr.com/",
  "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven",
  Resolver.sonatypeRepo("public")
)


libraryDependencies ++= Seq(
	("org.apache.spark" % "spark-core_2.11" % "2.0.0").
	exclude("org.mortbay.jetty", "servlet-api").
    exclude("commons-beanutils", "commons-beanutils-core").
    exclude("commons-collections", "commons-collections").
    exclude("commons-logging", "commons-logging").
    exclude("com.esotericsoftware.minlog", "minlog")
	, "com.typesafe" % "config" % "1.3.0"
	, "org.apache.spark" % "spark-sql_2.11" % "2.0.0"
	, "org.apache.spark" % "spark-streaming_2.11" % "2.0.0"
	, "junit" % "junit" % "4.8.1" % "test"
	, "org.scalatest" %% "scalatest" % "2.2.6" % "test"
	, "org.specs2" %% "specs2" % "2.4.15" % "test" 
)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
    case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("org", "aopalliance", xs @ _*) => MergeStrategy.last
    case PathList("com", "google", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
    case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
    case "META-INF/mailcap" => MergeStrategy.last
    case "META-INF/mimetypes.default" => MergeStrategy.last
    case "plugin.properties" => MergeStrategy.last
    case "log4j.properties" => MergeStrategy.last
    case x => old(x)
  }
}

excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp filter {
                i => i.data.getName == "slf4j-api-1.7.12.jar,spark-unsafe_2.11-2.0.0.jar,hadoop-yarn-api-2.2.0.jar,hadoop-yarn-client-2.2.0.jar,hadoop-mapreduce-client-core-2.2.0.jar"
            }
}
	// "org.specs2"    %% "specs2"    % "1.5" % "test"

	// , "org.apache.spark" %% "spark-sql" % "1.3.1"


// libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

// libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.0"


