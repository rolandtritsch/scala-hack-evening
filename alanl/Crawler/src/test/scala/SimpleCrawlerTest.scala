import org.scalatest.FunSuite
import org.scalatest.Tag

import scala.collection.mutable.Map

class SimpleCrawlerTest extends FunSuite {
 
  test("test crawl of gilt.com", Tag("online")) {
    val url = "http://www.gilt.com"
    val responses = SimpleCrawler.crawl(url)  
    printResults(responses)
  
    //TODO: what should I expect ?
   // expect (url) {url2}
   // expect (672) {time}
  }

  test("test getting hrefs", Tag("offline")) {

    //TODO: how do i access the resocue dir in scala-test ?
    val url = "file://Users/alan/Code/scala/scala-dub/Scala-Hack-Evening/alanl/Crawler/src/test/resources/gilt-home.html"
    val responses = SimpleCrawler.crawl(url) 
    printResults(responses)
  }

  private def printResults(responses : Map[Int, Map[String, Long]]) {
   
    println("====================== RESULTS ==================================")
    println()

    println(" HTTP_CODE \t TIME      \t URL")
    println(" --------- \t --------- \t -------------")

    //TODO: print out in readable format
    println(responses)

    println()
    println("=================================================================")
    
  }

}
