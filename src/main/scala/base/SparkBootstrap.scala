package base

import org.apache.spark.sql.SparkSession


object SparkBootstrap {

  val spark: SparkSession = SparkSession.builder()
    .appName("Spark NASA HTTP")
    .master("local[*]")
    .getOrCreate()

  val sc = spark.sparkContext

  def close = {
    spark.close()
  }
}
