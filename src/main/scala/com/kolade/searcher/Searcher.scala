package com.kolade.searcher

import com.kolade.searcher.model._

import scala.annotation.tailrec

object Searcher extends App {
  new Searcher(args).run()
  System.exit(0)
}

class Searcher(args: Array[String]) extends ResultWriter with InputOutput {
  private val MaxNumberOfResults = 10
  private val directory = Validator.validate(args)
  private val fileIndexer = Indexer(directory)
  private val ranker = Ranker { searchResults => searchResults.toVector.sortBy(-_.score) }

  def run(): Unit = {
    println("Enter word(s) to search for or :quit to exit:")
    process()
  }

  @tailrec
  private def process(): Unit = {
    Console.out.print("search>")
    read() match {
      case ":quit" => ()
      case text =>
        searchFiles(text)
        process()
    }
  }

  private def searchFiles(text: String): Unit = {
    val terms = tokenize(text)
    val searchResults = fileIndexer.search(terms)
    val rankedResults = ranker.rank(searchResults).filter(_.score > 0).take(MaxNumberOfResults)
    if (rankedResults.isEmpty) write("No matches found") else writeResults(rankedResults, write)
  }
}
