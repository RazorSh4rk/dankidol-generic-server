
import play.api.libs.json._
import scala.io.Source
import scala.util.Random
import scala.util.Properties
import scala.collection.JavaConverters._
import java.net.URI

import DB._
import Game._

object Main extends App {

	DB.insert( Game("test", "mobile", List(1,2,3)) )
	DB.update( "test", 4 )
	println( DB.get("test") )
	
}

/*object Main extends cask.MainRoutes {
  override val port: Int = 
    Properties.envOrElse("PORT", "9001").toInt

  override val host: String = 
    Properties.envOrElse("HOST", "0.0.0.0")
 
  val words = Json.parse(Source.fromFile("words.json").getLines().mkString)
  val swearWords = Json.parse(Source.fromFile("swear.json").getLines().mkString)
  val headers = Seq(
    ("content-type", "application/json"),
    ("Access-Control-Allow-Origin", "*")
  )

  @cask.get("/")
  def redir() = {
    cask.Redirect("/home")
  }

  @cask.staticFiles("/home")
  def index() = {
    "/templates/index.html"
  }

  @cask.get("/all")
  def getAll(swear: Int = 0) = {
    if(swear == 0) 
      cask.Response(Json.stringify(words), headers = headers)
    else 
      cask.Response(Json.stringify( Json.toJson( words.as[List[String]]:::(swearWords.as[List[String]]) ) ) , headers = headers)
  }

  @cask.get("/word")
  def getWord(number: Int = 1, swear: Int = 0) = {
    val r = new Random
    var w = words.as[List[String]]
    if(swear == 1)
      w = w:::swearWords.as[List[String]]
    val ret = Random.shuffle(w).take(number)

    cask.Response(Json.stringify(Json.toJson(ret)), headers = headers)
  }

  initialize()
}*/


