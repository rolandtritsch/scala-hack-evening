import org.jsoup.Jsoup
import org.jsoup.Connection
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import scala.collection.JavaConversions._
import scala.collection.mutable.Map

class SimpleCrawler {

  def get(url: String): (Int, Long, Elements) = {
  
    val start = System.currentTimeMillis()    
    val (status, links) = getStatusAndLinks(url)
    val time = System.currentTimeMillis() - start

    (status, time, links) 
  
  }

  private def getStatusAndLinks(url: String): (Int, Elements) = {

    val (status, doc) = getStatusAndDocument(url)  
    val links = doc.select("a[href]")
    //TODO: do I care about these?
    //val media: Elements = doc.select("[src]")
    //val imports: Elements = doc.select("link[href]")

    (status, links)
  
  }

  private def getStatusAndDocument(url: String): (Int, Document) = {

    if (url.matches("""file:\/\/.*""")) {
      fromFile(url)
    }
    else {
      fromHttp(url)
    }

  }

  private def fromHttp(url: String): (Int, Document) = {

      val conn = Jsoup.connect(url).ignoreHttpErrors(true)
      val doc = conn.timeout(0).get() //TODO: appropiate timeout?
      val status = conn.response.statusCode() 
    
      (status, doc)
  }

  private def fromFile(url: String): (Int, Document) = {

      val file = new java.io.File(url.replaceAll("file:/", ""))
      val doc = Jsoup.parse(file, "UTF-8")
      val status = 200
      
      (status, doc)
  }


  private def size(responsesByStatus: Map[Int, Map[String, Long]]) = {

    //TODO: FIXME: get rid of mutating var
    var sum = 0
    responsesByStatus.foreach(sum + _._2.size)
    sum

    /*
    responsesByStatus.reduceLeft((key:Int, value:Map[String, Long]) => sum + value.size)
    responsesByStatus.values.aggregate((size:Int) () )
    responsesByStatus.values.reduceLeft()
     pc.aggregate(Set[Int]())(_ += process(_), _ ++ _)
    //val sum = responsesByStatus.reduceLeft((key:Int, value:Map[String, Long]) => _:Int, + value.size)
    */
  }

}

object SimpleCrawler {

  private var count = 0 //TODO: change to functional method 
  private val MAX_LINKS = 100 
  private val responsesByStatus = Map[Int, Map[String, Long]]()

  def crawl(url: String): Map[Int, Map[String, Long]] = {
   
     if (continue(url)) {
      
      val crawler = new SimpleCrawler
      val (status, time, links) = crawler.get(url)

      val responses = responsesByStatus
        .getOrElse(status, Map[String, Long]())
      responses += (url -> time)
      responsesByStatus += (status -> responses)
      
      count += 1 
      displayProgress(url, status, time, links)

      for (link <- links) {
        val href = link.attr("abs:href")
        if (!responses.contains(href)) crawl(href)
      }

    }
   
    responsesByStatus
  }

  private def continue(url: String): Boolean = {
    (count <= MAX_LINKS) 
    //(count <= MAX_LINKS && url.matches("""http:\/\/www.gilt.com.*"""))
  }

  private def displayProgress(url: String, status: Int, time: Long
    , links: Elements) {

    if (count == 1) println("\nCrawling " + MAX_LINKS + " links...")
    
    println(" (" + count + ") " + url  
      + " {status_code=" + status 
      + " mili_secs=" + time 
      + " num_links=" + links.size + "}")
  
  }
 
}
