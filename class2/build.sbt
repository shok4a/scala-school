name := "class2"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.4"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

autoCompilerPlugins := true
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.8.0")
