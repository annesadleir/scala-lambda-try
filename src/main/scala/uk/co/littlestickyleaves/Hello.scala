package uk.co.littlestickyleaves

import java.io.{InputStream, OutputStream}
import java.nio.charset.StandardCharsets.UTF_8
import cats.data.Xor._
import io.circe.generic.auto._, io.circe.parse.decode, io.circe.syntax._

case class FullName(
                   title: Option[String],
                   firstName: Option[String],
                   lastName: Option[String]
                   )

class Hello {

  def handle(in: InputStream, out: OutputStream): Unit = {

    val payload = scala.io.Source.fromInputStream(in).mkString("")

    val reply = decode[FullName](payload) match {
      case Left(err) => s"Sorry I have error: $err"
      case Right(name)  =>
        name match {
          case FullName(Some(title), _, Some(lastName)) => s"Hello $title $lastName"
          case FullName(_, Some(firstName), _) => s"Hello $firstName"
          case FullName(_,_,_) => "Hello world"
        }
    }

    out.write(reply.getBytes(UTF_8))
  }

}