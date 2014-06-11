name := "agendatech"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "commons-io" % "commons-io" % "2.3",
  "org.projectlombok" % "lombok" % "1.14.0",
  "javax.mail" % "mail" % "1.4.1",
  "com.typesafe" %% "play-plugins-mailer" % "2.1-RC2"
)     

play.Project.playJavaSettings
