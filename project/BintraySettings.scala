import sbt._
import sbt.Keys._
import bintray.BintrayPlugin
import bintray.BintrayKeys._

object BintraySettings extends AutoPlugin {

  override def trigger = noTrigger
  override def requires = plugins.JvmPlugin && BintrayPlugin

  override def projectSettings = Seq(
    resolvers += Resolver.jcenterRepo,
    bintrayPackageLabels := Seq("avro4s"),
    bintrayOrganization := None,
    bintrayRepository := "maven",
    bintrayReleaseOnPublish := false,
    bintrayOmitLicense := false,
  )
}