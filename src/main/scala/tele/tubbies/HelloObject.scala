package tele.tubbies

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

import com.typesafe.config._

object HelloObject {
  def main(args: Array[String]) {
 
    // Load our own config values from the default location, application.conf
    val config = ConfigFactory.load()
    val appname = config.getString("spark-conf.appname")
    val master = config.getString("spark-conf.master")
    
    val conf = new SparkConf().setAppName(appname).setMaster(master)
    // val conf = new SparkConf().setAppName("Bird Count").setMaster("yarn-cluster")
    val sc = new SparkContext(conf)
 
    val inputfile = config.getString("spark-conf.inputfile")
    val myLines = sc.textFile(inputfile)

    val count = myLines.count()
 
    /* Transform the inputRDD into countRDD */
    val valcount = myLines.flatMap(line ⇒ line.split(" "))
                          .map(word ⇒ (word, 1))
                          .reduceByKey(_ + _)

    /* saveAsTextFile method is an action that effects on the RDD */
    val outfile = config.getString("spark-conf.outfile")
    valcount.saveAsTextFile(outfile)
  }
}

