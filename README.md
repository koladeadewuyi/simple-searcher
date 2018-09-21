# Simple Searcher App

A simple searcher command line application that allows a user search for words in files in a directory.
The search takes the words given on the prompt and return a list of the top 10 (maximum) matching filenames in rank order, giving the rank score against each match.

The rank score must be
- 100% if a file contains all the words
- It must be 0% if it contains none of the words
- It should be between 0 and 100% if it contains only some of the words

A simple ranking formula below is used:

score = (sum of frequencies of search terms in document) / (total search terms)

To optimize term lookup speed and index memory footprint, all terms are coded as Integers

## Assumptions made
- All the input strings are sane
- Search terms are NOT case sensitive 
- Input strings can include accented words or numbers
- Words can oly contain non-accented and accented alphabets, numbers, apostrophe and hyphens
- Words can contain an apostrophe e.g O'neil and that's
- The size of the dictionary of words fall within the Integer space (2,147,483,647) 

## Getting Started

To run the app locally via th command line use:
```bash
$ sbt "run .../src/main/resources"

2 files read in directory .../src/main/resources
Enter word(s) to search for or :quit to exit:
search> hello world
.../src/main/resources/file1.txt : 100%
.../src/main/resources/file2.txt : 50%
search> cats
No matches found
search> :quit
```

To run as jar:
```bash
$ sbt assembly
$ java -jar <pathToJar>/simple-searcher-assembly-0.1.0-SNAPSHOT.jar <directoryContainingTextFiles>
```

or by using the -cp option and specifying the main class
```bash
$ sbt assembly
$ java -cp <pathToJar>/simple-searcher-assembly-0.1.0-SNAPSHOT.jar com.kolade.searcher.Searcher <directoryContainingTextFiles>
```

### Prerequisites 

* [Java 8+](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Scala 2.12.6](https://www.scala-lang.org/download/)
* [Sbt 1.1.4](https://www.scala-lang.org/download/)


## Running the tests

```bash
sbt clean test
```

### Coding style

Coding style adopted from: 
* [Scala docs style guide](https://docs.scala-lang.org/style/)
* [Originate scala guide](https://www.originate.com/library/scala-guide-best-practices)
* [scalastyle](http://www.scalastyle.org/sbt.html)


## Built With

* [Sbt](https://docs.scala-lang.org/) - Build tool and dependency management

