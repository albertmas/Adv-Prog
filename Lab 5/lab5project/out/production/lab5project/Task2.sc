// MAP, FLATMAP AND FOR COMPREHENSIONS

// EXAMPLE 1 – Comparing map and flatMap
// Ex1
var airports = Map("Glasgow" -> "GLA", "Dubai" -> "DXB", "Berlin" -> "TXL")

// Ex2
airports.get("Glasgow")

// Ex3
airports.get("Edinburgh")
//Results are of type Option[String]

// Ex4
var searchList = List("Glasgow", "Edinburgh", "Berlin")
val codes = searchList.map(x => airports.get(x))
//codes is of type List[Option[String]]

// Ex5
val flatCodes = searchList.flatMap(x => airports.get(x))
//The list is created with the values of each option, discarding the ones that are None

// EXAMPLE 2 – for expression
// Ex1
val codes_for = for{
  x <- searchList
  y <- airports.get(x)
} yield(y)

// EXAMPLE 3 – Chaining functions
// Ex1
//val codes_lower = searchList.map(x => airports.get(x)).map(y => y.toLowerCase)

// Ex2
val codes_lower = searchList.flatMap(x => airports.get(x)).map(y => y.toLowerCase)
//toLowerCase cannot be applied to an Option[String]
//Converting it to Strings with flatMap allows the use of toLowerCase

// Ex3
val codes_for = for{
  x <- searchList
  y <- airports.get(x)
} yield(y.toLowerCase)

// EXAMPLE 4 – A more complex for comprehension
// Ex1
var searchMap = Map( "Glasgow" -> "Scotland", "Edinburgh" -> "Scotland",
"Berlin" -> "Germany")

// Ex2
val codes_countries_for = for{
  x <- searchMap.keys
  y <- airports.get(x)
  z <- searchMap.get(x)
} yield(y + " - " + z)

codes_countries_for.toList

// Ex3
val codes_flatMap = searchMap.keys.flatMap(x => airports.get(x)).toList
val countries_flatMap = searchMap.keys.flatMap(x => searchMap.get(x)).toList
var codes_countries_flatMap = codes_flatMap + " - " + countries_flatMap