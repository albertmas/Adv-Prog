import scala.io.Source
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.collection.immutable.ListMap

object MyApp extends App {
  // *******************************************************************************************************************
  // APPLICATION LOGIC

  // read data from file
  val mapdata = readFile("data.txt")
  // print data to check it's been read in correctly
  // println(mapdata)

  // define menu options as a Map of actions
  // for each menu item:
  // key is an Int, the value that will be read from the input
  // value is a function () => Boolean, i.e. no params and returns Boolean
  val actionMap = Map[Int, () => Boolean](1 -> handleOne, 2 -> handleTwo, 3 -> handleThree)

  // loop to read input and invoke menu option
  // uses function readOption to show menu and read input
  // uses function menu to invoke menu action
  // will terminate if menu returns false
  var opt = 0
  do {
    opt = readOption
  } while (menu(opt))

  // *******************************************************************************************************************
  // UTILITY FUNCTIONS

  // reads data file - comma separated file
  def readFile(filename: String): Map[String, Int] = {
    // create buffer to build up map as we read each line
    var mapBuffer: Map[String, Int] = Map()
    try {
      for (line <- Source.fromFile(filename).getLines()) {     // for each line
        val splitline = line.split(",").map(_.trim).toList     // split line at , and convert to List

        // add element to map buffer
        // splitline is line from file as List, e.g. List(Bayern Munich, 24)
        // use head as key
        // tail is a list, but need just the first (only in this case) element, so use head of tail and convert to int
        mapBuffer = mapBuffer ++ Map(splitline.head -> splitline.tail.head.toInt)

      }
    } catch {
      case ex: Exception => println("Sorry, an exception happened.")
    }
    mapBuffer
  }

  // *******************************************************************************************************************
  // FUNCTIONS FOR MENU

  // shows menu and reads input
  def readOption: Int = {
    println(
      """|Please select one of the following:
         |  1 - show points for all teams
         |  2 - show points for selected team
         |  3 - quit""".stripMargin)
    print("\nSelection>")
    readInt()
  }

  // invokes selected menu option
  // finds corresponding function to invoke in action map using get
  // pattern matching used as get returns an Option
  def menu(option: Int): Boolean = {
    actionMap.get(option) match {
      case Some(f) => f()
      case None =>
        println("Sorry, that command is not recognized")
        true
    }
  }

  // handlers for menu options
  def handleOne(): Boolean = {
    mnuShowPoints(currentPoints)      // calls function mnuShowPoints, which invokes function currentPoints
    true
  }

  def handleTwo(): Boolean = {
    print("Team>")
    mnuShowPointsForTeam(currentPointsForTeam)      // calls function mnuShowPointsForTeam, which invokes function currentPointsForTeam
    true
  }

  def handleThree(): Boolean = {
    println("selected quit")         // returns false so loop terminates
    false
  }

  // *******************************************************************************************************************
  // FUNCTIONS THAT INVOKE ACTION AND INTERACT WITH USER
  // each of these functions accepts user input if required for an operation,
  // invokes the relevant operation function and displays the results

  def mnuShowPoints(f:() => Map[String, Int]) = {
    f() foreach {case (x,y) => println(s"$x: $y")}
  }

  def mnuShowPointsForTeam(f: (String) => (String, Int)) = {
    // needs to print a prompt and use readline to get user input
    // should apply function f to user input string and print result
    // note that the result of f is a tuple

    val teamPoints = f(readLine())
    if (teamPoints._1 != ("")) {
      println(teamPoints._1 + ": " + teamPoints._2)
    }
    else {
      println("Team not found!")
    }
  }


  // *******************************************************************************************************************
  // OPERATION FUNCTIONS
  // each of these performs the required operation on the data and returns
  // the results to be displayed - does not interact with user

  def currentPoints():Map[String,Int] = {
    // sort map by value in descending order -
    // see http://alvinalexander.com/scala/how-to-sort-map-in-scala-key-value-sortby-sortwith
    ListMap(mapdata.toSeq.sortWith(_._2 > _._2):_*)
  }

  def currentPointsForTeam(team: String): (String, Int) = {
    // should retrieve points value from mapdata for key team and
    // return a tuple of team and points
    // remember that get returns an option so you will have to
    // pattern match to get the value

    val teamOp = mapdata.get(team) match {
      case Some(ap) => ap
      case None => ""
    }

    if (teamOp != "")
      return (team, mapdata(team))
    else
      return ("", 0)
  }
}
