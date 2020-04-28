package services

import com.google.inject.Inject
import models.{Employee, Employees}

import scala.concurrent.Future

class EmployeeService @Inject() (employees: Employees) {
  def getEmployee(id: Int): Future[Option[Employee]] = {
    employees.get(id)
  }
}
