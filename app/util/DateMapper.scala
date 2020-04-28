package util

import java.text.SimpleDateFormat
import java.sql.Date
import slick.jdbc.MySQLProfile.api._

object DateMapper {
  val formatter = new SimpleDateFormat("yyyy-MM-dd")

  val sqlDate2StringMapper = MappedColumnType.base[Date, String](
    { sqlDate => sqlDate.formatted("yyyy-MM-dd HH:mm:ss") },
    { dateString => new java.sql.Date(formatter.parse(dateString).getTime) })
}
