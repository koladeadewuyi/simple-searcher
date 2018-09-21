package com.kolade.searcher

import java.io.File

class ValidatorSpec extends TestFixture {

  describe("Validator") {

    val argumentErrorMessage = "A single file path is required"
    val invalidPathErrorMessage = "does not exists or is not a directory"

    it("should return a file handle to a given valid directory") {
      val validDirectoryPath = getClass.getResource("/non-empty-directory").getPath
      Validator.validate(Array(validDirectoryPath)) shouldBe new File(validDirectoryPath)
    }

    it("should throw an [IllegalArgumentException] when given empty args") {
      val emptyArgs = Array.empty[String]
      val ex = the[IllegalArgumentException] thrownBy Validator.validate(emptyArgs)
      ex.getMessage should include(argumentErrorMessage)
    }

    it("should throw an [IllegalArgumentException] when given more than one args") {
      val multiElementArgs = Array("path1", "path2")
      val ex = the[IllegalArgumentException] thrownBy Validator.validate(multiElementArgs)
      ex.getMessage should include(argumentErrorMessage)
    }

    it("should throw an [IllegalArgumentException] when given a non-existent file path") {
      val invalidPathArgs = Array("path1")
      val ex = the[IllegalArgumentException] thrownBy Validator.validate(invalidPathArgs)
      ex.getMessage should include(invalidPathErrorMessage)
    }

    it("should throw an [IllegalArgumentException] when given a file path that is not a directory") {
      val nonDirectoryPath = getClass.getResource("/non-directory-file").getPath
      val nonDirectoryArgs = Array(nonDirectoryPath)
      val ex = the[IllegalArgumentException] thrownBy Validator.validate(nonDirectoryArgs)
      ex.getMessage should include(invalidPathErrorMessage)
    }
  }

}
