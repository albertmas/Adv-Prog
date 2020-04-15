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
    //mnuShowRouteValues() // TODO
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
    print("Routes>")
    // TODO
    true
  }

  def handleSeven(): Boolean = {
    println("selected quit")         // returns false so loop terminates
    false
  }

  // *******************************************************************************************************************
  // FUNCTIONS THAT INVOKE ACTION AND INTERACT WITH USER

  // Operation 1
  def mnuShowRouteValues(f:() => Map[String, Int]) = { //TODO
    f() foreach {case (x,y) => println(s"$x: $y")}
  }

  // Operation 2
  def mnuShowTotalDistanceAndStages(f:() => Map[String, (Float, Int)]) = {
    f() foreach {case (x,y) => println(s"$x has ${y._2} stages and a total distance of ${floatPrecision(y._1, 2)} Km")}
  }

  // Operation 3
  def mnuShowAvgDistanceAndStages(f:() => (Float, Float)) = {
    val (avgDist, avgStages) = f()
    println(s"There is an average of ${floatPrecision(avgStages, 1)} stages and ${floatPrecision(avgDist, 2)} Km in the routes")
  }

  // Operation 4
  def mnuShowStagesSorted(f:() => Map[String, Float]) = {
    f() foreach {case (x,y) => println(s"$x: total distance of ${floatPrecision(y, 2)} Km")}
  }

  // Operation 5
  def mnuShowRouteDetails(f:(String) => String) = {
    val routeDetails = f(readLine()) //TODO display available routes and make user insert number instead of name

    if (routeDetails != ("")) {
      println(routeDetails)
    }
    else {
      println("Route not found!")
    }
  }

  // *******************************************************************************************************************
  // OPERATION FUNCTIONS

  def routeValues() = {

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


  // Useful functions
  def floatPrecision(f: Float, p: Int): Float = {
    BigDecimal(f).setScale(p, BigDecimal.RoundingMode.HALF_DOWN).toFloat
  }
}