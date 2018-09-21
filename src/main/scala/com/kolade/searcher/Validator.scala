package com.kolade.searcher

import java.io.File

object Validator {

  def validate(args: Array[String]): File = {
    require(args.length == 1, "A single file path is required")
    val file = new File(args.head)
    require(file.isDirectory, s"${args.head} does not exists or is not a directory")
    file
  }

}
