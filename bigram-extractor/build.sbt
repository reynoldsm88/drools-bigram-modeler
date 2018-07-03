organization := "org.reynoldsm88"
name := "bigram-extractor"
version := "0.0.1-SNAPSHOT"

enablePlugins( JavaAppPackaging )

mainClass in(Compile, run) := Some( "org.reynoldsm88.bigram.modeler.Main" )

assemblyMergeStrategy in assembly := {
    case PathList( "META-INF", "MANIFEST.MF" ) => MergeStrategy.discard
    case PathList( "reference.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "kie.conf" ) => MergeStrategy.concat
    //    case PathList( "META-INF", xs@_* ) => MergeStrategy.first
    case x => MergeStrategy.last
}