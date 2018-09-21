package com.kolade.searcher

import scala.collection.mutable

package object model {

  type Count = Int
  type Term = String
  type PrintFunction = String => Unit
  type TermCodes = mutable.Map[Term, Int]
  type RankingFunction = Set[SearchResult] => Vector[SearchResult]

  private val Separators = """[.,:;_~`"!@#$%^&*?()<>\[\]{}|]"""

  case class SearchResult(filePath: String, score: Float)

  case class FileTermFrequency(filePath: String, termFrequency: Map[Int, Count])

  def tokenize(line: String): Array[Term] = {
    line.replaceAll(Separators, " ").split("( )+").map(_.trim.toLowerCase)
  }

}
