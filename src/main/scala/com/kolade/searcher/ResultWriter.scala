package com.kolade.searcher

import java.text.DecimalFormat

import com.kolade.searcher.model._

trait ResultWriter {

  private val format = "###.##"
  private val decimalFormat = new DecimalFormat(format)

  def writeResults(rankedResults: Vector[SearchResult], writerFunc: PrintFunction): Unit = {
    val maxScore = rankedResults.headOption.map(_.score).getOrElse(1F)
    rankedResults foreach { result =>
      val formattedResult = s"${result.filePath} : ${decimalFormat.format(percentageScore(maxScore, result))}%"
      writerFunc(formattedResult)
    }
  }

  private def percentageScore(maxScore: Float, result: SearchResult): Float = {
    result.score * (100F / math.max(1F, maxScore))
  }

}
