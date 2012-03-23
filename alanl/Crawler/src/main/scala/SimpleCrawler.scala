import java.net.URL

import scala.collection.mutable.Map
import scala.io.BufferedSource
import scala.io.Source

class SimpleCrawler {

  def get(url: String): BufferedSource = { 
    Source.fromURL(new URL(url))
  }

  def calc(url: String): Long = {
    val start = System.currentTimeMillis()    
    val source = get(url)
    val finish = System.currentTimeMillis()
    // TODO: parse source for response code
    //         parse source for hrefs   
    finish - start

  }

}

object SimpleCrawler {

  private val responses = Map[String, Long]()

  def crawl(url: String): Long =
    if (responses.contains(url))
      responses(url)
    else {
      val crawler = new SimpleCrawler
      val time = crawler.calc(url)
      responses += (url -> time)
      time
    } 

}

