import scala.math.sqrt

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

object HelloWorld{
  def main(args: Array[String]) {
    println("Hello " + args(0))
  }
}

HelloWorld.main(Array("Albert"))

def square(value:Double):Double = {
  value * value
}

println(square(4))

def distance(x1:Double, y1:Double, x2:Double = 0, y2:Double = 0):Double = {
  sqrt(square(x2 - x1) + square(y2 - y1))
}

println(distance(0, 0, 1, 1))
println(distance(4, -7))

var myArray = Array(4,7,3,2,6)

def max(nums:Array[Int]):Int = {
  var result = nums(0)
  for(i <- 0 to nums.length - 1) {
    if(nums(i) > result)
      result = nums(i)
  }
  result
}

println(max(myArray))