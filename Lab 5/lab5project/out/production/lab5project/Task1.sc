// PARTIAL FUNCTION AND APPLICATION CURRYING

// EXAMPLE 1 - Partially applied function
// Ex1
def isDivisible(x: Int, y: Int) : Boolean = {
  if (x % y == 0)
    return true
  else
    return false
}
isDivisible(8, 2)
isDivisible(7, 2)
isDivisible(54, 9)

// Ex2
val isEven = isDivisible(_: Int, 2) //isEven is of type Int => Boolean

// Ex3
isEven(10)
//isEven acts as a function
//Acts as a call to isDivisible with x = single isEven parameter and y = 2

// Ex4
List(1,2,3,4,5,6,7,8,9,10).filter(isEven(_))

// EXAMPLE 2 - Another partially applied function
// Ex1
var airports = Map("Glasgow" -> "GLA", "Dubai" -> "DXB", "Berlin" -> "TXL")

// Ex2
def printMap(myMap: Map[String, String]): Unit = {
    for ((k, v) <- myMap) {
        println(s"$k - $v")
    }
}
printMap(airports)

// Generic approach of the function
//def printMap[A,B](myMap: Map[A,B]): Unit = {
//    for ((k, v) <- myMap) {
//        println(s"$k - $v")
//    }
//}

// Ex3
def printMap2(myMap: Map[String,String], f:(String,String)=>
  Unit): Unit = {
    for ((k, v) <- myMap) {
        f(k,v)
    }
}

// Ex4
def printKeyValue(k:String, v:String): Unit = {
    println(s"$k - $v")
}

// Ex5
def printValueOnly(k: String, v: String): Unit = {
    println(s"Value - $v")
}

// Ex6
printMap2(airports, printKeyValue)
printMap2(airports, printValueOnly)

// Ex7
val printKeysValues = printMap2(_, printKeyValue)

printKeysValues(airports)

// EXAMPLE 3 - Curried function
// Ex1
def printMap2_curried(myMap: Map[String,String])(f:(String,String)=>
  Unit): Unit = {
    for ((k, v) <- myMap) {
        f(k,v)
    }
}
printMap2_curried(airports)(printValueOnly)

// Ex2
printMap2_curried(airports){(k,v)=> println(s"Key - $k")}

// Ex3
// When using a lambda expression as a parameter, the curried function looks more readable