package com.kolade.searcher

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class InputOutputSpec extends TestFixture with InputOutput {

  describe("InputOutput") {

    it("should read line from console when read() is called") {
      val inputString = "test input"
      val input = new ByteArrayInputStream(inputString.getBytes)

      Console.withIn(input) {
        read() shouldBe inputString
      }
    }

    it("should write to console when write() is called") {
      val outputString = "test output"
      val output = new ByteArrayOutputStream()

      Console.withOut(output) {
        write(outputString)
        output.toString shouldBe s"$outputString\n"
      }
    }

  }
}
