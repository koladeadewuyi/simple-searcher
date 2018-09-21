package com.kolade.searcher

import com.kolade.searcher.model.{PrintFunction, SearchResult}

import scala.collection.mutable.ArrayBuffer

class ResultWriterSpec extends TestFixture with ResultWriter {

  describe("ResultWriter") {

    it("should format and write results") {
      val results = Vector(
        SearchResult("file2", 1.0F),
        SearchResult("file4", 0.3333F),
        SearchResult("file3", 0.5F),
        SearchResult("file1", 1.0F)
      )
      val output = new ArrayBuffer[String]()

      writeResults(results, stubbedPrintFunc(output))

      output.toList should contain theSameElementsInOrderAs Seq(
        "file2 : 100%",
        "file4 : 33.33%",
        "file3 : 50%",
        "file1 : 100%"
      )
    }

    it("should not write results when results is empty") {
      val results = Vector.empty
      val output = new ArrayBuffer[String]()

      writeResults(results, stubbedPrintFunc(output))

      output.toList should contain theSameElementsInOrderAs Nil
    }
  }

  private def stubbedPrintFunc(output: ArrayBuffer[String]): PrintFunction = {
    in: String => output += in
  }
}
