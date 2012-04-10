Scala dub project / problems sets i'm working on.

Crawler:
========
 A very basic web crawler that crawls a website
 Steps:	
  1. http get root a url e.g www.gilt.com 
  2. find all sub urls <href> links
  3. recurse upto a max url num OR regex of url domain name  
  4. For each url visit add to list of http status codes. 
      e.g struct
           Map<HttpStatusCode, Map<Url, RepoonseTime>>
  5. Print results

To run I use sbt and scala test at the moment http://www.scalatest.org/
  e.g start sbt
  > clean
  > compile
  > test-only SimpleCrawlerTest -- -n online
 or
  > test-only SimpleCrawlerTest -- -n offline


