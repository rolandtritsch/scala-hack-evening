import org.apache.http.client.ResponseHandler
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.impl.client.DefaultHttpClient


object Crawler {

  def main(args : Array[String]) : Unit = {

    val sourceUrl = "http://www.gilt.com/"

    val httpclient: HttpClient = new DefaultHttpClient
    val httpget: HttpGet = new HttpGet(sourceUrl)

    println("executing request..." + httpget.getURI)
    val responseHandler: ResponseHandler[String] = new BasicResponseHandler
    val responseBody = httpclient.execute(httpget, responseHandler)
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    println(responseBody)

    httpclient.getConnectionManager.shutdown

  }

}

