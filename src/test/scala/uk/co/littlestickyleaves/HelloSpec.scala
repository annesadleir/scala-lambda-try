package uk.co.littlestickyleaves

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest._
import java.nio.charset.StandardCharsets.UTF_8

// *so* not idiomatic...
class HelloSpec extends FunSuite with BeforeAndAfter {

  var hello: Hello = _

  before {
    hello = new Hello
  }

  test("all three values defined results in formal greeting") {
    // arrange
    val input = s"""{"title":"Mx","firstName":"Jay","lastName":"Human"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) === "Hello Mx Human")
  }

  test("title and lastName but no firstName results in formal greeting") {
    // arrange
    val input = s"""{"title":"Mx","lastName":"Human"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) === "Hello Mx Human")
  }

  test("firstName and lastName but no title results in casual greeting") {
    // arrange
    val input = s"""{"firstName":"Jay","lastName":"Human"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) === "Hello Jay")
  }

  test("title and firstName but not lastName results in casual greeting") {
    // arrange
    val input = s"""{"title":"Mx","firstName":"Jay"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) === "Hello Jay")
  }

  test("title but no firstName or lastName results in Hello world") {
    // arrange
    val input = s"""{"title":"Mx"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) === "Hello world")
  }

  test("lastName but no title or firstName results in Hello world") {
    // arrange
    val input = s"""{"lastName":"Human"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) === "Hello world")
  }


  test("empty input results in Hello world") {
    // arrange
    val input = s"""{"lastName":"Human"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) === "Hello world")
  }

  test("bad input results in error message") {
    // arrange
    val input = s""""lastName":"Human"}"""
    val inputStream = new ByteArrayInputStream(input.getBytes(UTF_8))
    val outputStream = new ByteArrayOutputStream

    // act
    hello.handle(inputStream, outputStream)

    // assert
    assert(outputStream.toString(UTF_8.displayName()) ===
      "Sorry I have error: io.circe.ParsingFailure: expected whitespace or eof got : (line 1, column 11)")
  }
}
