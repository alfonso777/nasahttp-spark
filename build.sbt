name := "nasahttp-spark"
version := "1.0"

scalaVersion := "2.11.8"
val sparkVersion = "2.2.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
"org.apache.spark" %% "spark-sql" % sparkVersion % Provided
)
