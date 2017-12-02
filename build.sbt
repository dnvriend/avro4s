
lazy val avro4s = project.in(file("."))
    .settings(
      publishArtifact := false,
      publish := {},
    ).aggregate(
      `avro4s-macros`,
      `avro4s-core`,
      `avro4s-json`,
    )

val json4s = "org.json4s" %% "json4s-native" % "3.5.3"
val shapeless = "com.chuusai" %% "shapeless" % "2.3.2"

lazy val `avro4s-macros` = project.in(file("avro4s-macros"))
  .settings(
    libraryDependencies += shapeless,
    libraryDependencies += json4s
  ).enablePlugins(BintraySettings)

lazy val `avro4s-core` = project.in(file("avro4s-core"))
  .dependsOn(`avro4s-macros`)
  .enablePlugins(BintraySettings)

lazy val `avro4s-json` = project.in(file("avro4s-json"))
  .dependsOn(`avro4s-core`)
  .settings(libraryDependencies += json4s)
  .enablePlugins(BintraySettings)