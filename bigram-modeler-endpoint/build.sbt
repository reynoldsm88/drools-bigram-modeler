organization := "org.reynoldsm88"
name := "bigram-modeler-endpoint"
version := "0.0.1-SNAPSHOT"

mainClass in(Compile, run) := Some( "org.reynoldsm88.bigram.modeler.endpoint.Main" )

// docs for why this is necessary are here: https://github.com/scalatra/sbt-scalatra
enablePlugins( JavaAppPackaging )
enablePlugins( JettyPlugin )
enablePlugins( ScalatraPlugin )

test in assembly := {}

assemblyMergeStrategy in assembly := {
    case PathList( "META-INF", "MANIFEST.MF" ) => MergeStrategy.discard
    case PathList( "reference.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "kie.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "sisu", "javax.inject.Named" ) => MergeStrategy.concat
    case PathList( "META-INF", "plexus", "components.xml" ) => MergeStrategy.first
    case x => MergeStrategy.last
}