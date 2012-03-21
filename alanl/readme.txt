Scala dub project / problems sets i'm working on.

Crawler:
========
 A very basic web crawler that crawls a website
 Steps:	
  1. http get root a url e.g www.gilt.com 
  2. find all sub urls <href> links
  3. recurse upto a max url num OR regex of url domain name  
  4. Build a Set<Int> of http status codes found. 
  5. Under each http staus code node store map of url details
      - <key> url
      - <value> http get repsonse time


