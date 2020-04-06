//MAPS

// Creating maps
// Ex1
var airports = scala.collection.mutable.Map("Glasgow" -> "GLA", "Dubai" -> "DXB", "Berlin" -> "TXL")

// Ex2
val moreAirports = scala.collection.Map("Helsinki" -> "HEL")

// Ex3
val evenMoreAirports = scala.collection.Map("Glasgow" -> "PIK", "Los Angeles" -> "LAX")

// Ex4
val newAirports = moreAirports ++ evenMoreAirports

// Ex5
airports ++= newAirports
// GLA airport is lost because two values cannot have the same key

// Ex6
airports += ("Tokyo" -> "HAN")

// Extracting data from maps
// Ex1
val cities = airports.keys.toList
val codes = airports.values.toList

// Ex2
val gla = airports.get("Glasgow") // Returns an option because the key could be missing
val ldn = airports.get("London") // Key "London" is missing, so get returns None

// Ex3
val gla2 = airports.get("Glasgow") match {
  case Some(ap) => ap
  case None => "not found"
}

val ldn2 = airports.get("London") match {
  case Some(ap) => ap
  case None => "not found"
}

val airports2 = Map("GLA" -> "Glasgow", "TXL" -> "Berlin", "PIK" -> "Glasgow",
  "LAX" -> "Los Angeles")
val value = "Berlin"
val default = ("not found","")
airports2.find(_._2==value).getOrElse(default)._1
airports2.find(x =>x._2==value).getOrElse(default)._1
airports2.find(_._2==value).getOrElse(default)._1

// Iterating
// Ex1
