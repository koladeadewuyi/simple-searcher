package com.kolade.searcher

import java.io.File

import com.kolade.searcher.model._

import scala.annotation.tailrec
import scala.collection.mutable

class Indexer(dir: File) {

  private val Zero = 0
  private val (termCodes, docToTermFrequency) = {
    val files = getFiles(Vector(dir))
    println(s"${files.size} files read in directory ${dir.getPath}")
    buildIndices(files)
  }

  def search(terms: Array[Term]): Set[SearchResult] = {
    docToTermFrequency map { index =>
      val score = computeScore(index, terms)
      SearchResult(index.filePath, score)
    }
  }

  @tailrec
  private def getFiles(files: Vector[File], seenFiles: Set[File] = Set.empty): Set[File] = {
    files.headOption match {
      case Some(file) if file.isDirectory => getFiles(file.listFiles().toVector, seenFiles)
      case Some(file) => getFiles(files.tail, seenFiles + file)
      case None => seenFiles
    }
  }

  private def buildIndices(files: Set[File]): (TermCodes, Set[FileTermFrequency]) = {
    val termCodes = mutable.Map.empty[Term, Int]

    val docToTermMatrix = files map { file =>
      val lines = io.Source.fromFile(file).getLines().toList
      val groupedTerms = lines.flatMap(tokenize).groupBy(identity)
      val termFrequency = groupedTerms map {
        case (term, occurrences) =>
          val termCode = termCodes.getOrElseUpdate(term, termCodes.size + 1)
          termCode -> occurrences.size
      }
      FileTermFrequency(file.getPath, termFrequency)
    }

    (termCodes, docToTermMatrix)
  }

  private def computeScore(index: FileTermFrequency, terms: Array[Term]): Float = {
    val termFrequencies = terms.par.map { term =>
      val maybeTermCode = termCodes.get(term)
      maybeTermCode.map(termCode => index.termFrequency.getOrElse(termCode, Zero)).getOrElse(Zero)
    }
    termFrequencies.sum / terms.length.toFloat
  }

}

object Indexer {
  def apply(dir: File) = new Indexer(dir)
}
