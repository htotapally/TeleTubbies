package tele.tubbies

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object HelloObject {
  def main(args: Array[String]) {
    // val sc = new SparkContext(new SparkConf())
    // val sc = new SparkContext( "spark://192.168.56.102:7077", "Word Count", "/Users/haratotapally/Downloads/spark-1.6.2", Nil, Map(), Map()) 

    val conf = new SparkConf().setAppName("Animal Count").setMaster("spark://Haras-MBP:7077")
    // val conf = new SparkConf().setAppName("Bird Count").setMaster("yarn-cluster")
    val sc = new SparkContext(conf)
    print(sc)
    val data = Array(1, 2, 3, 4, 5)
    val distData = sc.parallelize(data)

    //val input = sc.textFile("/Users/haratotapally/workspace/HelloScala/src/in.txt")
    // val input = sc.textFile("hdfs://tiger.hopeless.com:8020/Hortonworks")
 
    val myLines = sc.textFile("hdfs://Haras-MBP:9000/in.txt")

    // val myLinesFiltered = myLines.filter( lambda x: len(x) > 0 )
    val count = myLines.count()
    print(count)

    /* Transform the inputRDD into countRDD */

    val valcount = myLines.flatMap(line ⇒ line.split(" "))
                          .map(word ⇒ (word, 1))
                          .reduceByKey(_ + _)

    /* saveAsTextFile method is an action that effects on the RDD */
    valcount.saveAsTextFile("hdfs://Haras-MBP:9000/outfile")
  }
}

