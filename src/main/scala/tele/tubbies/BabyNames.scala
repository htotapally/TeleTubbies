package tele.tubbies

import org.apache.spark._
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import com.typesafe.config._


object BabyNames {
  
  def main(args: Array[String]) {
    
    // Load config values from the babynames.conf
    val config = ConfigFactory.load("babynames")
    val appname = config.getString("spark-conf.appname")
    val master = config.getString("spark-conf.master")
    
    val conf = new SparkConf().setAppName(appname).setMaster(master)
    val sc = new SparkContext(conf)
    
    val inputfile = config.getString("spark-conf.inputfile")
  
    // create SparkSession
    val spark = SparkSession.builder()
                          .appName(appname)
                          .config("spark.some.config.option", "some-value")
                          .getOrCreate()
                          
    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._
    
    // Read baby names                      
    val baby_names = spark.read
                          .format("com.databricks.spark.csv")
                          .option("header", "true")
                          .option("inferSchema", "true")
                          .load(inputfile)
   
    // Register TempTable                      
    baby_names.registerTempTable("names")
    
    // Write to an output file
    val outfile = config.getString("spark-conf.outfile")

    val distinctYears = spark.sql("select distinct Year from names")
    // distinctYears.rdd.saveAsTextFile(outfile)
    
    val popular_names = spark.sql("select distinct(`First Name`), count(County) as cnt from names group by `First Name` order by cnt desc LIMIT 10")
    // popular_names.rdd.saveAsTextFile(outfile)

    val popular_names1 = spark.sql("select distinct(`First Name`), sum(Count) as cnt from names group by `First Name` order by cnt desc LIMIT 10")
    popular_names1.rdd.saveAsTextFile(outfile)
    
    
  }
}