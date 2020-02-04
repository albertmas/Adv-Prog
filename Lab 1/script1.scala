def power(value:Int, power:Int):Int = {
  var result = 1
  for(i <- 0 to power-1) {
    result = result * value
  }
  result
}

println(power(2, 4))

println(power(3, 5))

println(power(3, -5))