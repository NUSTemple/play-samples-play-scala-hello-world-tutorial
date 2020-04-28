package services

import java.sql.Date
import java.text.SimpleDateFormat

import com.google.inject.Inject
import models.{DeptManager, DeptManagerJoin, DeptManagers}

import scala.concurrent.Future

class DeptManagerService @Inject() (deptManagers: DeptManagers) {

  def getDeptManager(id: Int): Future[Option[DeptManagerJoin]] = {
    deptManagers.get(id)
  }
}
