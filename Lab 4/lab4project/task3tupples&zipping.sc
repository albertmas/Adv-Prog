//TUPLES AND ZIPPING

// Ex1
val getUserInfo = ("Al", 42, 200.0)
val(name, age, weight) = getUserInfo //Assign all elements of a tuple into vals, all at once

val fruits = List(("apple", "red"), ("banana", "yellow"), ("orange", "orange"))
val fruitMap = fruits.toMap //Turns List of Tuple2 into map

val lists = (List(1,2,3), List(4,5,6), List(7,8,9)) //Tuple of lists

// Ex2
fruitMap foreach {
  case (k, v) => println(s"Fruit: $k - Colour: $v")
}

// Ex3
val cities = List("Glasgow", "Dubai", "Berlin")
val codes = List("GLA","DXB","TXL")

// Ex4
cities zip codes //Combines both Lists, creating a List of Tuple2

// Ex5
val airports = (cities zip codes).toMap
airports foreach {
  case (k, v) => println(s"City: $k - Code: $v")
}

// Ex6
val players = List("Stenson", "Mickelson", "Galllacher")
val round1 = List(70, 68, 70)
val round2 = List(65, 72, 68)

// Ex7
round1 zip round2

// Ex8
val scores2 = round1 zip round2 map{
  case (x, y) => x + y
}

// Ex9
val playersScore = players zip scores2
playersScore foreach {
  case (k, v) => println(s"Player: $k - Total score: $v")
}