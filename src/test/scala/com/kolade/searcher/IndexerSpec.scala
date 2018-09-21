package com.kolade.searcher

import java.io.File

import com.kolade.searcher.model.SearchResult

class IndexerSpec extends TestFixture {

  describe("Indexer") {

    it("should search the indices and compute the search results for the given search term(s)") {
      val directory = getClass.getResource("/non-empty-directory/inner-directory-depth-1").getPath
      val indexer = Indexer(new File(directory))

      indexer.search(Array("hello")).toVector should contain theSameElementsAs Vector(
        SearchResult(s"$directory/inner-directory-depth-2/file11.txt", 0.0F),
        SearchResult(s"$directory/file10.txt", 0.0F),
        SearchResult(s"$directory/file9.txt", 0.0F),
        SearchResult(s"$directory/file3.txt", 1.0F),
        SearchResult(s"$directory/inner-directory-depth-2/file12.txt", 1.0F)
      )
    }

    it("should assign a default score of 0.0 when file(s) do not contain search term(s)") {
      val directory = getClass.getResource("/non-empty-directory").getPath
      val indexer = Indexer(new File(directory))

      indexer.search(Array("unknown search terms")).toVector should contain theSameElementsAs Vector(
        SearchResult(s"$directory/file2.txt", 0.0F),
        SearchResult(s"$directory/inner-directory-depth-1/inner-directory-depth-2/file11.txt", 0.0F),
        SearchResult(s"$directory/inner-directory-depth-1/inner-directory-depth-2/file12.txt", 0.0F),
        SearchResult(s"$directory/file4.txt", 0.0F),
        SearchResult(s"$directory/inner-directory-depth-1/file3.txt", 0.0F),
        SearchResult(s"$directory/inner-directory-depth-1/file10.txt", 0.0F),
        SearchResult(s"$directory/file8.txt", 0.0F),
        SearchResult(s"$directory/empty-file.txt", 0.0F),
        SearchResult(s"$directory/file5.txt", 0.0F),
        SearchResult(s"$directory/file7.txt", 0.0F),
        SearchResult(s"$directory/file1.txt", 0.0F),
        SearchResult(s"$directory/inner-directory-depth-1/file9.txt", 0.0F),
        SearchResult(s"$directory/file6.txt", 0.0F)
      )
    }
  }

}
