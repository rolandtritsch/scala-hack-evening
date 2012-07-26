import java.net.URL
import scala.io.Source


/**
 *  Main entry point for simple crawler
 * */
object Main {

  val usage = """Usage: url"""

  def main(args : Array[String]) : Unit = {

    if (args.isEmpty) {
      println(usage)
    }
    else {
      //val sourceUrl = "http://www.gilt.com/"
      val sourceUrl = args(0)  
      val source = Source.fromURL(new URL(sourceUrl))

      // TODO: parse
      source.getLines.foreach(println)
    }
  
  }

}
