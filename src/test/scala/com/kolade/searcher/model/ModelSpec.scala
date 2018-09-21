package com.kolade.searcher.model

import com.kolade.searcher.TestFixture

class ModelSpec extends TestFixture {

  val scenarios = Table(
    ("text", "tokens"),
    ("", Array("")),
    (" ", Array.empty[String]),
    ("'", Array("'")),
    (""" " """, Array.empty[String]),
    ("hello world", Array("hello", "world")),
    ("Hello World", Array("hello", "world")),
    ("Hello, World", Array("hello", "world")),
    ("Hello (world)", Array("hello", "world")),
    ("Hello [world]", Array("hello", "world")),
    ("Hello <world>", Array("hello", "world")),
    ("Hello {world}", Array("hello", "world")),
    ("Hello _world", Array("hello", "world")),
    ("Hello |world", Array("hello", "world")),
    ("Hello $world", Array("hello", "world")),
    ("Hello @world", Array("hello", "world")),
    ("Hello #world", Array("hello", "world")),
    ("Hello %world", Array("hello", "world")),
    ("Hello ^world", Array("hello", "world")),
    ("Hello &world", Array("hello", "world")),
    ("Hello *world", Array("hello", "world")),
    ("Hello :world", Array("hello", "world")),
    ("O'neil isn't", Array("o'neil", "isn't")),
    ("Ça me plaît.", Array("ça", "me", "plaît")),
    ("hello world 1", Array("hello", "world", "1"))
  )

  describe("model") {

    forAll(scenarios) { (text, tokens) =>
      it(s"""should tokenize '$text' to '${tokens.mkString(", ")}'""") {
        tokenize(text) should contain theSameElementsAs tokens
      }
    }

  }
}
