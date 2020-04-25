package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

    val json:JsValue = Json.parse(
      """{
        |"status" : "Okay",
        |"type": "index"
        |}""".stripMargin)

    Ok(json)
  }

  def hello(name: String): Action[AnyContent] = Action {
    val json:JsValue = Json.parse(
      s"""{
        |"status" : "Okay",
        |"type": "hello",
        |"name": "$name"
        |}""".stripMargin)

    Ok(json)
  }

  def jsonReturnTest(): Action[AnyContent] = Action {

    val json: JsValue = Json.parse(
      """
  {
    "name" : "Watership Down",
    "location" : {
      "lat" : 51.235685,
      "long" : -1.309197
    },
    "residents" : [ {
      "name" : "Fiver",
      "age" : 4,
      "role" : null
    }, {
      "name" : "Bigwig",
      "age" : 6,
      "role" : "Owsla"
    } ]
  }
  """)
    val lat = (json \ "residents").get
    Ok(lat)
  }

  def departments():Action[AnyContent] = Action {


    Ok("")
  }

}
