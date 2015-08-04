name := "vector"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.7"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

incOptions := incOptions.value.withNameHashing(false)

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "com.chuusai" %% "shapeless" % "2.2.5",
  "org.typelevel" %% "shapeless-scalacheck" % "0.4",
  "org.typelevel" %% "shapeless-spire" % "0.4",
  "org.typelevel" %% "shapeless-scalaz" % "0.4",
  "org.spire-math" %% "spire" % "0.10.1"
)
