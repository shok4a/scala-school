name := "scala-school"

scalaVersion := "2.11.8"

lazy val root = Project(id = "root", base = file(".")) aggregate sample
lazy val sample = Project(id = "sample", base = file("sample"))
