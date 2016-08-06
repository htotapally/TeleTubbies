package tele.tubbies

import org.apache.spark._
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import com.typesafe.config._

object Employee {
  
  def main(args: Array[String]) {
    // Load our own config values from the default location, application.conf
    val config = ConfigFactory.load("employee")
    val appname = config.getString("spark-conf.appname")
    val master = config.getString("spark-conf.master")
    
    val conf = new SparkConf().setAppName(appname).setMaster(master)
    val sc = new SparkContext(conf)
    val recs = sc.textFile("hdfs://Haras-MBP:9000/employees.json")
    
    val spark = SparkSession.builder()
                          .appName("Spark SQL Example")
                          .config("spark.some.config.option", "some-value")
                          .getOrCreate()
  
    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._
    
    val df = spark.read.json("hdfs://Haras-MBP:9000/employees.json")

    // Displays the content of the DataFrame to stdout
    df.show()
    
  }
}

// /Users/haratotapally/workspace/HelloScala/