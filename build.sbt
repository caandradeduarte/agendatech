name := "agendatech"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "commons-io" % "commons-io" % "2.3",
  "org.projectlombok" % "lombok" % "1.14.0"
)     

play.Project.playJavaSettings
