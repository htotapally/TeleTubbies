# TeleTubbies

# Adding sbt eclipse plugin instructions
http://www.scala-sbt.org/release/docs/Using-Plugins.html


run sbt assembly to create an uber jar file to submit the job


val baby_names = sqlContext.read.format("com.databricks.spark.csv").option("header", "true").option("inferSchema", "true").load("hdfs://Haras-MBP:9000/Baby_Names__Beginning_2007.csv")
baby_names: org.apache.spark.sql.DataFrame = [Year: int, First Name: string, County: string, Sex: string, Count: int]



val popular_names = spark.sql("select distinct(`First Name`), count(County) as cnt from names group by `First Name` order by cnt desc LIMIT 10")

popular_names.collect.foreach(println)

val popular_names = spark.sql("select distinct(`First Name`), sum(Count) as cnt from names group by `First Name` order by cnt desc LIMIT 10")