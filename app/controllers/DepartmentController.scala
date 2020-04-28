package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services._
import models._

import scala.concurrent.ExecutionContext.Implicits.global

class DepartmentController @Inject()(cc: ControllerComponents, departmentService: DepartmentService)
  extends AbstractController(cc){
  def getDepartment(id:String) = Action.async { implicit request: Request[AnyContent] =>
    departmentService.getDepartment(id) map { res =>
      implicit val resFormat: OWrites[Department] = Json.writes[Department]
      Ok(Json.toJson(res))
    }
  }


  def listAll() = Action.async { implicit request: Request[AnyContent] =>
    departmentService.listAllDepartments map { res =>

      implicit val resFormat: OWrites[Department] = Json.writes[Department]
      Ok(Json.toJson(res))
    }
  }
}
