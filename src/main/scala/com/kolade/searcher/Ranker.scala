package com.kolade.searcher

import com.kolade.searcher.model._

class Ranker(rankingFunction: RankingFunction) {

  def rank(searchResults: Set[SearchResult]): Vector[SearchResult] = {
    rankingFunction(searchResults)
  }

}

object Ranker {
  def apply(rankingFunction: RankingFunction) = new Ranker(rankingFunction)
}
