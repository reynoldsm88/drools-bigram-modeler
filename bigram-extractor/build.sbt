organization := "org.reynoldsm88"
name := "bigram-extractor"
version := "0.0.1-SNAPSHOT"

test in assembly := {}

assemblyMergeStrategy in assembly := {
    case PathList( "META-INF", "MANIFEST.MF" ) => MergeStrategy.discard
    case PathList( "reference.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "kie.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "sisu", "javax.inject.Named" ) => MergeStrategy.concat
    case PathList( "META-INF", "plexus", "components.xml" ) => MergeStrategy.first
    case x => MergeStrategy.last
}