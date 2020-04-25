package models

import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}

case class Department( dept_no: String,dept_name: String)

class DepartmentsTableDef(tag: Tag) extends Table[Department](tag, "departments") {

  def deptNo = column[String]("dept_no", O.PrimaryKey)
  def deptName= column[String]("dept_name")
  override def * : ProvenShape[Department]=
    (deptNo, deptName) <>(Department.tupled, Department.unapply)
}

class Departments @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                      (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  // the HasDatabaseConfigProvider trait gives access to the
  // dbConfig object that we need to run the slick queries

  val departments = TableQuery[DepartmentsTableDef]

  def get(id: String): Future[Option[Department]] = {
    dbConfig.db.run(departments.filter(_.deptNo === id).result.headOption)
  }

  def listAll: Future[Seq[Department]] = {
    dbConfig.db.run(departments.result)
  }

}
