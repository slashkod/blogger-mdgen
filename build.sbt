name := "blogger-mdgen"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/",
  "sonatype-snapshot" at "https://oss.sonatype.org/content/repositories/snapshots/",
   Resolver.bintrayRepo("giflw", "maven")
)


libraryDependencies ++= Seq (
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.6",
  "commons-io" % "commons-io" % "2.6-SNAPSHOT",
  "com.typesafe" % "config" % "1.3.1",
  "com.overzealous" % "remark" % "1.1.0"
)
        