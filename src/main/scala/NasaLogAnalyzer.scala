import base.SparkBootstrap._
import org.apache.spark.rdd.RDD

case class NasaLogAnalyzer(filesInputPath: String) {
  lazy val hostsRDD = sc.wholeTextFiles(filesInputPath).cache
                        .flatMap( _._2 split("\n") )
                        .filter(_.contains(" - - "))
                        .map(NasaRequestLogItem.extractData(_))

  def numberOfUniqueHosts = hostsRDD.map(_.host).distinct().count()

  private def filterBy404Errors = hostsRDD.filter(_.error.contains("404"))

  def numberOf404Errors = filterBy404Errors.count()

  def topURLWithMost404Errors(nTops: Int): RDD[(String,Int)] = sc.parallelize(filterBy404Errors.map( _.host -> 1).reduceByKey(_+_).sortBy(_._2, false).take(nTops))

  def numberOf404ErrorsByDay: RDD[(String,Int)] = filterBy404Errors.map(_.datetime.split(":")(0) -> 1).reduceByKey(_+_).sortBy(_._2)

  def totalBytes = hostsRDD.map(_.bytes.toLong).sum()
}
