import sbt._
import Dependencies._

organization := "org.reynoldsm88"
name := "drools-bigram-modeler"
version := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.11.11"

// reccommended from: https://github.com/holdenk/spark-testing-base/blob/master/README.md
fork in Test := true
javaOptions ++= Seq( "-Xms512M", "-Xmx2048M", "-XX:MaxMetaspace=1024M", "-XX:+CMSClassUnloadingEnabled" )

publishMavenStyle := true

resolvers += Resolver.sbtPluginRepo( "releases" )
resolvers += Classpaths.typesafeReleases
resolvers in ThisBuild ++= Seq( "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
                                "Spray IO Repository" at "http://repo.spray.io/",
                                "Maven Central" at "https://repo1.maven.org/maven2/",
                                "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/" )

//@formatter:off
lazy val root = ( project in file( "." ) ).aggregate( model, bigramExtractor, bigramRules, bigramEndpoint )

lazy val model = ( project in file( "model" ) ).settings( libraryDependencies ++= scalaTest )


lazy val bigramRules = ( project in file( "bigram-rules" ) )
                                    .dependsOn( model )
                                    .settings( libraryDependencies ++= kieTest ++ droolsTest ++ scalaTest  )

lazy val bigramExtractor = ( project in file( "bigram-extractor" ) )
                                    .dependsOn( model, bigramRules )
                                    .settings( libraryDependencies ++= spark ++ drools ++ kie ++ sparkTestBase ++ scalaTest ++ gensonJSON )

val bigramEndpoint = ( project in file( "bigram-modeler-endpoint" ) )
                                    .dependsOn( model, bigramRules, bigramExtractor )
                                    .settings( libraryDependencies ++= spark ++ scalaTest ++ scalatra ++ sparkTestBase )

//@formatter:off