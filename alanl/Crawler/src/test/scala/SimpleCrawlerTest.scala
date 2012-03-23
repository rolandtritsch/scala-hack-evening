
import org.scalatest.FunSuite

class SimpleCrawlerTest extends FunSuite {
 
  test("test calc gilt.com") {
    //TODO: what should I exppect ?
    expect(634) { new SimpleCrawler().calc("http://www.gilt.com") }
  }
 
}
