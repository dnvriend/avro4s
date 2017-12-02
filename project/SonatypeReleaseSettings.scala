import com.typesafe.sbt.pgp.PgpKeys
import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin

object SonatypeReleaseSettings extends AutoPlugin {
  override def trigger = noTrigger
  override def requires = plugins.JvmPlugin && sbtrelease.ReleasePlugin

  override def globalSettings = publishingSettings

  val publishingSettings = Seq(
    publishMavenStyle := true,
    publishArtifact in Test := false,
    ReleasePlugin.autoImport.releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    ReleasePlugin.autoImport.releaseCrossBuild := true,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value) {
        Option("snapshots" at s"${nexus}content/repositories/snapshots")
      } else {
        Option("releases" at s"${nexus}service/local/staging/deploy/maven2")
      }
    },
  )
}
