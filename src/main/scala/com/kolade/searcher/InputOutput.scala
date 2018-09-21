package com.kolade.searcher

import scala.io.StdIn.readLine

trait InputOutput {
  def write(result: String): Unit = println(result)

  def read(): String = readLine()
}
