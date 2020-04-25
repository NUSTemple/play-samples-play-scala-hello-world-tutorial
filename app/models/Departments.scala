package models
import play.api.libs.json._

case class Departments( dept_no: String,dept_name: String)

object Departments {
  implicit val departmentFormat = Json.format[Departments]
}

