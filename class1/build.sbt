name := "class0"

scalaVersion := "2.11.8"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0-M15"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0-M15" % "test"
libraryDependencies += "com.github.okomok" %% "sing-core" % "0.2.0"