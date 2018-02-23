spark-submit \
  --class org.nasahttp.demo.Application \
  --master local[*] \
  --executor-memory 4G \
  --driver-memory 3g \
  --total-executor-cores 1 \
  ./target/scala-2.11/nasahttp-spark_2.11-1.0.jar  \
  $1 $2

  
