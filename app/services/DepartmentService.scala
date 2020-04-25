package services

import com.google.inject.Inject
import models.{Department, Departments}

import scala.concurrent.Future

class DepartmentService @Inject() (departments: Departments) {

  def getDepartment(id: String): Future[Option[Department]] = {
    departments.get(id)
  }

  def listAllDepartments: Future[Seq[Department]] = {
    departments.listAll
  }
}