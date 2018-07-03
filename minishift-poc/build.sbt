organization := "org.reynoldsm88"
name := "minishift-poc"
version := "0.0.1-SNAPSHOT"

enablePlugins( JavaAppPackaging )

// docs for why this is necessary are here: https://github.com/scalatra/sbt-scalatra
enablePlugins( ScalatraPlugin )

mainClass in(Compile, run) := Some( "org.reynoldsm88.minishift.poc.Main" )

assemblyMergeStrategy in assembly := {
    case PathList( "META-INF", "MANIFEST.MF" ) => MergeStrategy.discard
    case PathList( "reference.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "kie.conf" ) => MergeStrategy.concat
    //    case PathList( "META-INF", xs@_* ) => MergeStrategy.first
    case x => MergeStrategy.last
}

