package models

import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape
import java.sql.Date
import util.DateMapper.sqlDate2StringMapper

import scala.concurrent.{ExecutionContext, Future}

case class Employee(empNo: Int, birthDate: String, firstName: String, lastName: String,
                    gender: String, hireDate: String)
class EmployeesTable(tag: Tag) extends Table[Employee](tag, "employees") {
  def empNo = column[Int]("emp_no")
  def birthDate = column[Date]("birth_date") (sqlDate2StringMapper)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def gender = column[String]("gender")
  def hireDate = column[Date]("hire_date") (sqlDate2StringMapper)
  override def * : ProvenShape[Employee] =
    (empNo, birthDate.asColumnOf[String], firstName, lastName, gender, hireDate.asColumnOf[String]) <> (Employee.tupled, Employee.unapply)

}

class Employees @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                            (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  // the HasDatabaseConfigProvider trait gives access to the
  // dbConfig object that we need to run the slick queries

  val employees = TableQuery[EmployeesTable]

  def get(id: Int): Future[Option[Employee]] = {
    dbConfig.db.run(employees.filter(_.empNo === id).result.headOption)
  }

}

