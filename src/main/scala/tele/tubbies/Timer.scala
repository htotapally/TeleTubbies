package tele.tubbies

object Timer {
  def oncePerSecond(callback: () => Unit) {
    while (true) { callback(); Thread sleep 1000 }
  }
  def timeFlies() {
    println("time flies like an arrow...")
  }

  def main1(args: Array[String]) {
    oncePerSecond(timeFlies)
  }
}