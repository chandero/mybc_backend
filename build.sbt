name := """mybc_server"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  //javaJpa,
  //"org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
  filters,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.dbunit" % "dbunit" % "2.4.9",
  cache,
  javaWs,
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.8.5",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.pauldijou" %% "jwt-play" % "0.9.0"
)

ivyConfiguration ~= { originalIvyConfiguration =>
  val config = originalIvyConfiguration.asInstanceOf[InlineIvyConfiguration]
  val ivyHome = file("./.ivy2")
  val ivyPaths = new IvyPaths(config.paths.baseDirectory, Some(ivyHome))
  new InlineIvyConfiguration(ivyPaths, config.resolvers, config.otherResolvers,
    config.moduleConfigurations, config.localOnly, config.lock,
    config.checksums, config.resolutionCacheDir, config.updateOptions, config.log)
}

resolvers += Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true