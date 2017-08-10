package eu.imind.play.rest.versioning

case class ApiVersion(v: String) {

  lazy val major:Int = v.split("""\.""").headOption
      .collect {
        case m if m.startsWith("v") && m.substring(1).forall(_.isDigit) => m.substring(1).toInt
      }
      .getOrElse(0)

  lazy val minor:Int = v.split("""\.""")
    .lift(1)
    .filter(_.forall(_.isDigit))
    .map(_.toInt)
    .getOrElse(0)

  override def toString = "v" + major + "." + minor

}

object ApiVersion {
  val default = ApiVersion("v1.0")

  implicit class ApiVersionComparisons(apiVersion: ApiVersion) {

    def >=(range: ApiVersionRange): Boolean = range match {
      case ApiVersionRange(lowest, _) if apiVersion.major > lowest.major => true
      case ApiVersionRange(lowest, _) if apiVersion.major == lowest.major =>
        apiVersion.minor >= lowest.minor
      case _ => false
    }

    def <=(range: ApiVersionRange): Boolean = range match {
      case ApiVersionRange(highest, None) if apiVersion.major < highest.major => true
      case ApiVersionRange(highest, None) if apiVersion.major == highest.major =>
        apiVersion.minor <= highest.minor
      case ApiVersionRange(_, Some(highest)) if apiVersion.major < highest.major => true
      case ApiVersionRange(_, Some(highest)) if apiVersion.major == highest.major =>
        apiVersion.minor <= highest.minor
      case _ => false
    }

    def in(range: ApiVersionRange): Boolean =
      this >= range && this <= range

  }

  def fromPath(path: String): Option[ApiVersion] = path
    .split("""/""")
    .dropWhile(part => !(part startsWith "v"))
    .headOption
    .map(ApiVersion.apply)

}

