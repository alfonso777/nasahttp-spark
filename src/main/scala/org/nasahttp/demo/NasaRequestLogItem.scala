package org.nasahttp.demo


case class NasaRequestLogItem(host: String, datetime: String, resource: String, error: String, bytes: String)

object NasaRequestLogItem {
  def extractData(line: String) = new NasaRequestLogItem(
    host = line.split(" - - ")(0),
    datetime = "\\[([^]]+)\\]".r.findFirstMatchIn(line).getOrElse("").toString
      .toString.replace("[","").replace("]",""),
    resource = """(["']).*?\1""".r.findFirstMatchIn(line).getOrElse("").toString,
    error = "\" (.*)".r.findFirstMatchIn(line).toList(0).toString.split(" ")(1),
    bytes = "\" (.*)".r.findFirstMatchIn(line).toList(0).toString.split(" ")(2)
  )
}