package helpers

import java.util.UUID

object IDGenerator {

  def randomLong():Long = (Math.random() * 10000).toLong
  def randomUUID():UUID = UUID.randomUUID()

}
