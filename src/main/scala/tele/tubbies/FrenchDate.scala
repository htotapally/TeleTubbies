package tele.tubbies

import java.util.{ Date, Locale }
import java.text.DateFormat._
object FrenchDate {
  def main1(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
  }
}