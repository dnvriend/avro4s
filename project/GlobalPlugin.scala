import sbt._
import sbt.Keys._

/** Adds common settings automatically to all subprojects */
object GlobalPlugin extends AutoPlugin {

  val githubName = "dnvriend"
  val org = s"com.github.$githubName"

  val AvroVersion = "1.8.2"
  val Log4jVersion = "1.2.17"
  val ScalatestVersion = "3.0.4"
  val ScalaVersion = "2.12.4"
  val Slf4jVersion = "1.7.12"

  override def trigger = allRequirements
  override def requires = plugins.JvmPlugin
  override def projectSettings = Seq(
    organization := org,
    scalaVersion := ScalaVersion,
    crossScalaVersions := Seq("2.11.11", "2.12.4"),
    resolvers += Resolver.mavenLocal,
    parallelExecution in Test := false,
    scalacOptions := Seq(
      "-unchecked",
      "-deprecation",
      "-encoding",
      "utf8",
      "-Ywarn-unused-import",
      "-Xfatal-warnings", "-feature", "-language:existentials"
    ),
    javacOptions := Seq("-source", "1.8", "-target", "1.8"),
    libraryDependencies ++= Seq(
      "org.scala-lang"    % "scala-reflect"     % scalaVersion.value,
      "org.apache.avro"   % "avro"              % AvroVersion,
      "org.slf4j"         % "slf4j-api"         % Slf4jVersion,
      "log4j"             % "log4j"             % Log4jVersion          % "test",
      "org.slf4j"         % "log4j-over-slf4j"  % Slf4jVersion          % "test",
      "org.scalatest"     %% "scalatest"        % ScalatestVersion      % "test"
    ),
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    pomExtra := {
      <url>https://github.com/{githubName}/avro4s</url>
        <licenses>
          <license>
            <name>MIT</name>
            <url>https://opensource.org/licenses/MIT</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:{githubName}/avro4s.git</url>
          <connection>scm:git@github.com:{githubName}/avro4s.git</connection>
        </scm>
        <developers>
          <developer>
            <id>{githubName}</id>
            <name>{githubName}</name>
            <url>http://github.com/{githubName}</url>
          </developer>
        </developers>
    },
  )
}