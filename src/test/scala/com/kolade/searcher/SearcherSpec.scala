package com.kolade.searcher

import scala.collection.mutable.ArrayBuffer

class SearcherSpec extends TestFixture {

  describe("Searcher") {

    it("should output search results containing matching files and score") {
      val directory = getClass.getResource("/non-empty-directory/inner-directory-depth-1").getPath
      val args = Array(directory)
      val outputs = new ArrayBuffer[String]()
      val searcher = mockSearcher(args, Iterator("hello", ":quit"), outputs)

      searcher.run()

      outputs.toList should contain theSameElementsInOrderAs Seq(
        s"$directory/file3.txt : 100%",
        s"$directory/inner-directory-depth-2/file12.txt : 100%"
      )
    }

    it("should limit search results to only the top 10 matching files") {
      val MaxNumberOfResults = 10
      val directory = getClass.getResource("/non-empty-directory").getPath
      val args = Array(directory)
      val outputs = new ArrayBuffer[String]()
      val searcher = mockSearcher(args, Iterator("cat hello user 1 2 6", ":quit"), outputs)

      searcher.run()

      outputs.size shouldBe MaxNumberOfResults
      outputs.toList should contain theSameElementsInOrderAs Seq(
        s"$directory/inner-directory-depth-1/inner-directory-depth-2/file12.txt : 50%",
        s"$directory/inner-directory-depth-1/file3.txt : 50%",
        s"$directory/file6.txt : 33.33%",
        s"$directory/file2.txt : 33.33%",
        s"$directory/inner-directory-depth-1/file10.txt : 16.67%",
        s"$directory/inner-directory-depth-1/inner-directory-depth-2/file11.txt : 16.67%",
        s"$directory/file7.txt : 16.67%",
        s"$directory/inner-directory-depth-1/file9.txt : 16.67%",
        s"$directory/file4.txt : 16.67%",
        s"$directory/file5.txt : 16.67%"
      )
    }

    it("should output 'No matches found' when term(s) is not contained in any file") {
      val directory = getClass.getResource("/non-empty-directory").getPath
      val args = Array(directory)
      val outputs = new ArrayBuffer[String]()
      val searcher = mockSearcher(args, Iterator("unMatchedTerm", ":quit"), outputs)

      searcher.run()

      outputs.toList should contain theSameElementsInOrderAs Seq("No matches found")
    }

    it("should not output any search results when given a :quit command only") {
      val directory = getClass.getResource("/non-empty-directory").getPath
      val args = Array(directory)
      val outputs = new ArrayBuffer[String]()
      val searcher = mockSearcher(args, Iterator(":quit"), outputs)

      searcher.run()

      outputs.toList should contain theSameElementsInOrderAs Nil
    }

  }

  private def mockSearcher(args: Array[String], inputs: Iterator[String], outputs: ArrayBuffer[String]) = {
    new Searcher(args) {
      override def write(result: String): Unit = outputs += result

      override def read(): String = inputs.next()
    }
  }
}
