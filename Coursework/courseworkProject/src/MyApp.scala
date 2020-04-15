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
  var mapPersonalisedRoutes: Map[String, String] = Map()

  // Map of menu operations
  val menuMap = Map[Int, () => Boolean](1 -> handleOne, 2 -> handleTwo, 3 -> handleThree, 4 -> handleFour,
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
          x => val stage = x.split(":").map(_.trim).toList     // split elements at :
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
      """
         |****************************************************
         |Please select one of the following:
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
    println("\nPRINTING ROUTES DETAILS")
    mnuShowAllRoutesValues(routeValues)
    true
  }

  def handleTwo(): Boolean = {
    mnuShowTotalDistanceAndStages(totalDistanceAndStages)
    true
  }

  def handleThree(): Boolean = {
    mnuShowAvgDistanceAndStages(avgDistanceAndStages)
    true
  }

  def handleFour(): Boolean = {
    println("ROUTES REPORT")
    mnuShowStagesSorted(sortStagesByDistance)
    println("SUMMARY")
    mnuShowAvgDistanceAndStages(avgDistanceAndStages)
    true
  }

  def handleFive(): Boolean = {
    print("Route>")
    mnuShowRouteDetails(getRouteDetails)
    true
  }

  def handleSix(): Boolean = {
    mnuShowPersonalisingMenu()
    true
  }

  def handleSeven(): Boolean = {
    println("selected quit")         // returns false so loop terminates
    false
  }

  // *******************************************************************************************************************
  // PERSONALISED LIST MENU

  // Map of menu operations
  val persListMenuMap = Map[Int, () => Boolean](1 -> handlePersOne, 2 -> handlePersTwo,
    3 -> handlePersThree, 4 -> handlePersFour, 5 -> handlePersFive) //TODO fix this being nullptr

  def readPersonaliseOption: Int = {
    println(
      """
        |Please select one of the following:
        |  1 - show list
        |  2 - add route
        |  3 - remove route
        |  4 - clear list
        |  5 - go back""".stripMargin)
    print("\nSelection>")
    readInt()
  }

  // Reads input
  def menuPersonalisedList(option: Int): Boolean = {
//    persListMenuMap.get(option) match {
//      case Some(f) => f()
//      case None =>
//        println("Sorry, that command is not recognized")
//        true
//    }

    option match {
      case 1 => handlePersOne()
      case 2 => handlePersTwo()
      case 3 => handlePersThree()
      case 4 => handlePersFour()
      case 5 => handlePersFive()
      case _ =>
        println("Sorry, that command is not recognized")
        true
    }
  }

  // Handlers for personalised list menu options
  def handlePersOne(): Boolean = {
    mnuShowPersonalisedRoutes()
    true
  }
  def handlePersTwo(): Boolean = {
    mnuShowAddRouteToPersList(addRouteToPersList)
    true
  }
  def handlePersThree(): Boolean = {
    mnuShowRemoveRouteFromPersList(removeRouteFromPersList)
    true
  }
  def handlePersFour(): Boolean = {
    mnuShowClearRoutePersList()
    true
  }
  def handlePersFive(): Boolean = {
    false       // returns false so loop terminates
  }

  // *******************************************************************************************************************
  // FUNCTIONS THAT INVOKE ACTION AND INTERACT WITH USER

  // Operation 1
  def mnuShowAllRoutesValues(f:() => List[String]): Unit = {
    f() foreach {println(_)}
  }

  // Operation 2
  def mnuShowTotalDistanceAndStages(f:() => Map[String, (Float, Int)]): Unit = {
    f() foreach {case (x,y) => println(s"$x has ${y._2} stages and a total distance of ${floatPrecision(y._1, 2)} Km")}
  }

  // Operation 3
  def mnuShowAvgDistanceAndStages(f:() => (Float, Float)): Unit = {
    val (avgDist, avgStages) = f()
    println(s"There is an average of ${floatPrecision(avgStages, 1)} stages and ${floatPrecision(avgDist, 2)} Km in the routes")
  }

  // Operation 4
  def mnuShowStagesSorted(f:() => Map[String, Float]): Unit = {
    f() foreach {case (x,y) => println(s"$x: total distance of ${floatPrecision(y, 2)} Km")}
  }

  // Operation 5
  def mnuShowRouteDetails(f:(String) => String): Unit = {
    val routeDetails = f(readLine()) //TODO display available routes and make user insert number instead of name

    if (routeDetails != ("")) {
      println(routeDetails)
    }
    else {
      println("Route not found!")
    }
  }

  // Operation 6

  def mnuShowPersonalisingMenu(): Unit = {
    // Loops personalising menu after each operation until app finishes
    var opt = 0
    do {
      opt = readPersonaliseOption
    } while (menuPersonalisedList(opt))
  }

  def mnuShowPersonalisedRoutes(): Unit = {
    if (mapPersonalisedRoutes.nonEmpty)
      mapPersonalisedRoutes foreach { case (x, y) => println(s"$x: $y") }
    else
      println("Routes list is empty!")
  }

  def mnuShowAddRouteToPersList(f:(String) => Boolean): Unit = {
    print("Route>")
    if (f(readLine()))
      println("Route correctly added to personalised route list!")
    else
      println("Incorrect route name!")
  }

  def mnuShowRemoveRouteFromPersList(f:(String) => Boolean): Unit = {
    print("Route>")
    if (f(readLine()))
      println("Route correctly removed from personalised route list!")
    else
      println("Incorrect route name!")
  }

  def mnuShowClearRoutePersList(): Unit = {
    mapPersonalisedRoutes = Map()
    println("Personalised route list cleared!")
  }


  // *******************************************************************************************************************
  // OPERATION FUNCTIONS

  def routeValues(): List[String] = {
    var routesList: ListBuffer[String] = ListBuffer()
    mapData foreach {
      case (k, v) =>
        routesList += getRouteDetails(k)
    }

    routesList.toList
  }

  def totalDistanceAndStages(): Map[String, (Float, Int)] = {
    var distAndStages: Map[String, (Float, Int)] = Map()
    mapData foreach {
      case (k, v) =>
        var distance: Float = 0
        var stages: Int = 0

        v foreach {
          case (i, s, f) =>
            distance += f
            stages += 1
        }

        distAndStages = distAndStages ++ Map(k -> (distance, stages))
    }

    distAndStages
  }

  def avgDistanceAndStages(): (Float, Float) = {
    var totalDist: Float = 0
    var totalStages: Float = 0
    val mapTotal = totalDistanceAndStages()

    mapTotal foreach {
      case (_, (dist, stages)) =>
        totalDist += dist
        totalStages += stages
    }

    (totalDist/mapTotal.size, totalStages/mapTotal.size)
  }

  def sortStagesByDistance(): Map[String, Float] = {
    var distMap: Map[String, Float] = Map()
    mapData foreach {
      case (k, v) =>
        var distance: Float = 0

        v foreach {
          case (_, _, f) =>
            distance += f
        }

        distMap = distMap ++ Map(k -> distance)
    }

    ListMap(distMap.toSeq.sortWith(_._2 > _._2):_*)
  }

  def getRouteDetails(route: String): String = {
    mapData.get(route) match {
      case Some(routeStages) =>
        var routeDetails = route
        routeStages foreach {
          case (i, s, f) =>
            routeDetails += s"\n   -Stage $i: $s has a distance of $f Km"
        }
        routeDetails
      case None => ""
    }
  }

  def addRouteToPersList(route: String): Boolean = {
    mapData.get(route) match {// Check if route exists
      case Some(_) =>
        mapPersonalisedRoutes.get(route) match {// Check if route is already on personalised list
          case Some(_) =>
            println("Route is already on the list, comment will be replaced")
            removeRouteFromPersList(route)
          case None =>
        }

        println("You can add a comment to the route")
        print("Comment>")
        val comment = readLine()
        mapPersonalisedRoutes ++= Map(route -> comment)
        true
      case None => false
    }
  }

  def removeRouteFromPersList(route: String): Boolean = {
    mapPersonalisedRoutes.get(route) match {
      case Some(_) =>
        mapPersonalisedRoutes -= route
        true
      case None => false
    }
  }

  def clearRoutePersList(): Unit = {
    mapPersonalisedRoutes = Map()
  }


  // Useful functions
  def floatPrecision(f: Float, p: Int): Float = {
    BigDecimal(f).setScale(p, BigDecimal.RoundingMode.HALF_DOWN).toFloat
  }
}
