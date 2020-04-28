package models

import java.sql.Date
import java.text.SimpleDateFormat

import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape
import util.DateMapper._

import scala.concurrent.{ExecutionContext, Future}

case class DeptManager(empNo: Int, deptNo: String, fromDate: Date, toDate: Date)
case class DeptManagerJoin(empNo: Int, empName: String, dept: String, fromDate: String, toDate: String)

class DeptManagersTable(tag: Tag) extends Table[DeptManager](tag, "dept_manager") {
  def empNo = column[Int]("emp_no")
  def deptNo = column[String]("dept_no")
  def fromDate = column[Date]("from_date") (sqlDate2StringMapper)
  def toDate = column[Date]("to_date") (sqlDate2StringMapper)

  override def * : ProvenShape[DeptManager] =
    (empNo, deptNo, fromDate, toDate) <> (DeptManager.tupled, DeptManager.unapply)

  def pk = primaryKey("PRIMARY", (empNo, deptNo))

  val employees = TableQuery[EmployeesTable]
  val departments = TableQuery[DepartmentsTable]

  def empNoFK = foreignKey("dept_manager_ibfk_1", empNo, employees)(_.empNo)
  def deptNoFK = foreignKey("dept_manager_ibfk_2", deptNo, departments)(_.deptNo)

}

class DeptManagers @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  // the HasDatabaseConfigProvider trait gives access to the
  // dbConfig object that we need to run the slick queries

  val deptManagers = TableQuery[DeptManagersTable]
  val employees = TableQuery[EmployeesTable]
  val departments = TableQuery[DepartmentsTable]


  def get(id: Int): Future[Option[DeptManagerJoin]] = {
    dbConfig.db.run(deptManagers.join(employees)
      .on(_.empNo === _.empNo)
      .join(departments)
        .on(_._1.deptNo === _.deptNo)
      .filter(_._1._2.empNo === id).map{
      case ((t, s),a) =>
        ((s.empNo, s.firstName, a.deptName, t.fromDate.asColumnOf[String], t.toDate.asColumnOf[String])
          <> (DeptManagerJoin.tupled, DeptManagerJoin.unapply))
    }.result.headOption)
  }

}
