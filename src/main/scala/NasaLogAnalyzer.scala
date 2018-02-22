import base.SparkBootstrap
import org.apache.spark.SparkFiles

import scala.io.Source

case class NasaLogAnalyzer(filesPath: String) extends SparkBootstrap {
    lazy val hostsRDD = sc.wholeTextFiles(filesPath).cache
                          .flatMap( _._2 split("\n") )
                          .filter(_.contains(" - - "))
                          .map(NasaRequestLogItem.extractData(_))


}
