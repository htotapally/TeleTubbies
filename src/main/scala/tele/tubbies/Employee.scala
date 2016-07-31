package tele.tubbies

// import org.apache.spark.implicits._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object Employee {
  def main1(args: Array[String]) {
    val conf = new SparkConf().setAppName("Employee Merge").setMaster("spark://Haras-MBP:7077")
    val sc = new SparkContext(conf)
    val recs = sc.textFile("hdfs://Haras-MBP:9000/employees.json")
    
    
  }
}

// /Users/haratotapally/workspace/HelloScala/