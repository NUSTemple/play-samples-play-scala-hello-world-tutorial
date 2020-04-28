package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services._
import models._


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



}
