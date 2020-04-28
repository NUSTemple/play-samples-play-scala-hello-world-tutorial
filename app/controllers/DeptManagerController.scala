package controllers

import javax.inject.Inject
import models.{DeptManager, DeptManagerJoin}
import play.api.libs.json.{Json, OWrites}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.DeptManagerService

import scala.concurrent.ExecutionContext.Implicits.global

class DeptManagerController @Inject()(cc: ControllerComponents, deptManagerService: DeptManagerService)
  extends AbstractController(cc) {

  def getDeptManager(id: Int) = Action.async { implicit request: Request[AnyContent] =>
    deptManagerService.getDeptManager(id) map { res =>
      implicit val resFormat: OWrites[DeptManagerJoin] = Json.writes[DeptManagerJoin]
      Ok(Json.toJson(res))
    }
  }
}

