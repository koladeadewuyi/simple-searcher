package com.kolade.searcher

import com.kolade.searcher.model.SearchResult

class RankerSpec extends TestFixture {

  describe("Ranker") {

    it("should rank search results in descending score order when given a descending ranking function") {
      import fixture._
      def descendingRank(searchResults: Set[SearchResult]) = searchResults.toVector.sortBy(-_.score)

      val ranker = Ranker(descendingRank)

      ranker.rank(searchResults) should contain theSameElementsInOrderAs Vector(result2, result3, result1)
    }

    it("should rank search results in ascending score order when given an ascending ranking function") {
      import fixture._
      def ascendingRank(searchResults: Set[SearchResult]) = searchResults.toVector.sortBy(_.score)

      val ranker = Ranker(ascendingRank)

      ranker.rank(searchResults) should contain theSameElementsInOrderAs Vector(result1, result3, result2)
    }

  }

  private val fixture = new {
    val result1 = SearchResult("file1.txt", 1.1F)
    val result2 = SearchResult("file2.txt", 2.2F)
    val result3 = SearchResult("file3.txt", 1.3F)
    val searchResults = Set(result1, result2, result3)
  }
}
