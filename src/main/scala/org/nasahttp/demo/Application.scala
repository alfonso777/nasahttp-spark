package org.nasahttp.demo

import org.nasahttp.demo.base.SparkBootstrap._
import scala.util.{Success, Try}

object Application {

  def main(args: Array[String]) = {
    Try((args(0), args(1))) match {
      case Success((input, output)) => processLog(input, output)
      case _ => println("Error at getting the input and output path.")
    }
  }

  def processLog(inputPath: String, outputPath: String) = {
    val analyzerLog = new NasaLogAnalyzer(inputPath)

    val numberOfDistinctHosts = s"Number of distinct hosts: " + analyzerLog.numberOfUniqueHosts
    val numberOf404Errors = s"Number of 404 Errors: " + analyzerLog.numberOf404Errors
    val totalOfBytes = s"Total of bytes: " + analyzerLog.totalBytes

    val topURLsWithMost404Errors = analyzerLog.topURLWithMost404Errors(13)
    val daily404Errors = analyzerLog.numberOf404ErrorsByDay

    val simpleContentOutput = List(numberOfDistinctHosts, numberOf404Errors, totalOfBytes)
    sc.parallelize(simpleContentOutput).saveAsTextFile(s"${outputPath}/question125")
    topURLsWithMost404Errors.saveAsTextFile(s"${outputPath}/question3")
    daily404Errors.saveAsTextFile(s"${outputPath}/question4")

    spark.close()
  }
}
