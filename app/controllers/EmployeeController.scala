package controllers

import javax.inject.Inject
import models.Employee
import play.api.libs.json.{Json, OWrites}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.EmployeeService
import scala.concurrent.ExecutionContext.Implicits.global

class EmployeeController @Inject()(cc: ControllerComponents, employeeService: EmployeeService)
  extends AbstractController(cc) {

  def getEmployee(id: Int) = Action.async { implicit request: Request[AnyContent] =>
    employeeService.getEmployee(id) map { res =>
      implicit val resFormat: OWrites[Employee] = Json.writes[Employee]
      Ok(Json.toJson(res))
    }
  }
}

