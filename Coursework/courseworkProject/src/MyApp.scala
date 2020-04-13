import scala.io.Source
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

object MyApp extends App {
  // *******************************************************************************************************************
  // APPLICATION LOGIC

  // Read data from file
  val mapData = readFile("cyclingroutedata.txt")

  // Map of menu operations
  val menuMap = Map[Int, () => Boolean](1 -> handleOne, 2 -> handleTwo, 3 -> handleThree, 4 -> handleTwoFour,
    5 -> handleFive, 6 -> handleSix, 7 -> handleSeven)

  // Loops menu after each operation until app finishes
  var opt = 0
  do {
    opt = readOption
  } while (menu(opt))

  // *******************************************************************************************************************
  // UTILITY FUNCTIONS

  // Reads data file - comma separated file
  def readFile(filename: String): Map[String, List[(Int, String, Float)]] = {
    var mapBuffer: Map[String, List[(Int, String, Float)]] = Map()
    try {
      for (line <- Source.fromFile(filename).getLines()) {     // for each line
        val splitLine = line.split(",").map(_.trim).toList     // split line at , and convert to List

        val routeName = splitLine.head     // get route name
        val routeStages: ListBuffer[(Int, String, Float)] = ListBuffer()
        splitLine.drop(1) foreach {     // loop list, skipping first element (name)
          case x => val stage = x.split(":").map(_.trim).toList     // split elements at :
            routeStages.addOne((stage.apply(0).toInt, stage.apply(1), stage.apply(2).toFloat))     // add to tuple list
        }

        mapBuffer = mapBuffer ++ Map(routeName -> routeStages.toList)     // add element to map (name, tuple list)
      }
    } catch {
      case ex: Exception => println("Sorry, an exception happened.")
    }
    mapBuffer
  }

  // *******************************************************************************************************************
  // FUNCTIONS FOR MENU

  // Prints menu
  def readOption: Int = {
    println(
      """|Please select one of the following:
         |  1 - get route values
         |  2 - total distance and number of stages per route
         |  3 - average total distance and number of stages
         |  4 - routes report
         |  5 - specified route details
         |  6 - personalised routes list
         |  7 - quit""".stripMargin)
    print("\nSelection>")
    readInt()
  }

  // Reads input
  def menu(option: Int): Boolean = {
    menuMap.get(option) match {
      case Some(f) => f()
      case None =>
        println("Sorry, that command is not recognized")
        true
    }
  }

  // Handlers for menu options
  def handleOne(): Boolean = {
    //mnuShowRouteValues()
    true
  }

  def handleTwo(): Boolean = {
    //print("Team>")
    mnuShowTotalDistanceAndStages(totalDistanceAndStages)
    true
  }

  def handleSeven(): Boolean = {
    println("selected quit")         // returns false so loop terminates
    false
  }

  // *******************************************************************************************************************
  // FUNCTIONS THAT INVOKE ACTION AND INTERACT WITH USER

  def mnuShowRouteValues(f:() => Map[String, Int]) = { //TODO
    f() foreach {case (x,y) => println(s"$x: $y")}
  }

  def mnuShowTotalDistanceAndStages(f:() => Map[String, (Float, Int)]) = { //TODO
    f() foreach {case (x,y) => println(s"$x has ${y._2} stages and a total distance of ${y._1} Km")}
  }

  // *******************************************************************************************************************
  // OPERATION FUNCTIONS

  def routeValues() = {
    
  }

  def totalDistanceAndStages(): Map[String, (Float, Int)] = {

  }
}
