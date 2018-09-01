import sbt._

object Dependencies {

    val slf4jVersion = "1.7.5"
    val logbackVersion = "1.1.7"
    val sparkVersion = "2.2.0"
    val sparkTestBaseVersion = "2.2.0_0.8.0"
    val kiegroupVersion = "7.4.1.Final"
    val betterFilesVersion = "2.16.0"
    val opennlpVersion = "1.8.3"
    val hadoopVersion = "2.7.4"
    val scalaTestVersion = "3.0.4"
    val gensonVersion = "1.4"
    val scalatraVersion = "2.5.4"
    val mockitoVersion = "0.4.3"


    val slf4j = Seq( "org.slf4j" % "slf4j-api" % slf4jVersion )

    val logback = Seq( "ch.qos.logback" % "logback-classic" % logbackVersion )

    val spark = Seq( "org.apache.spark" %% "spark-core" % sparkVersion % "provided" )

    val sparkTestBase = Seq( "com.holdenkarau" %% "spark-testing-base" % sparkTestBaseVersion % "test" )

    val drools = Seq( "org.drools" % "drools-core" % kiegroupVersion,
                      "org.drools" % "drools-compiler" % kiegroupVersion )

    val droolsTest = Seq( "org.drools" % "drools-core" % kiegroupVersion % "test",
                          "org.drools" % "drools-compiler" % kiegroupVersion % "test" )

    val kie = Seq( "org.kie" % "kie-api" % kiegroupVersion,
                   "org.kie" % "kie-internal" % kiegroupVersion,
                   "org.kie" % "kie-ci" % kiegroupVersion )

    val kieTest = Seq( "org.kie" % "kie-api" % kiegroupVersion % "test",
                       "org.kie" % "kie-internal" % kiegroupVersion % "test",
                       "org.kie" % "kie-ci" % kiegroupVersion % "test" )

//    val opennlp = Seq( "org.apache.opennlp" % "opennlp-tools" % opennlpVersion )

    val hadoop = Seq( "org.apache.hadoop" % "hadoop-common" % hadoopVersion,
                      "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion,
                      "org.apache.hadoop" % "hadoop-client" % hadoopVersion )

    val hadoopTest = Seq( "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion % "test" classifier "tests",
                          "org.apache.hadoop" % "hadoop-common" % hadoopVersion % "test" classifier "tests" )

    val scalaTest = Seq( "org.scalatest" %% "scalatest" % scalaTestVersion % "test" )

    val gensonJSON = Seq( "com.owlike" % "genson-scala_2.11" % gensonVersion )

    // TODO - fix versions later
    val scalatra = Seq( "org.scalatra" %% "scalatra" % scalatraVersion,
                        "org.scalatra" %% "scalatra-scalatest" % scalatraVersion % "test",
                        "ch.qos.logback" % "logback-classic" % "1.2.3",
                        "org.eclipse.jetty" % "jetty-webapp" % "9.2.19.v20160908",
                        "javax.servlet" % "javax.servlet-api" % "3.1.0" )

    val mockito = Seq( "org.mockito" %% "mockito-scala" % mockitoVersion % "test" )
}