// MAPPING AND FOLDING

List(1,2,3,4,5).map(x => x * x)

// Ex1
List("1","2","3","4","5").map(x => x.toInt)

// Ex2
List("aa","bb","cc","dd","ee").map(x => x.toUpperCase())

// Ex3
val list = List.range(1, 20)

// Ex4
def sum(list: List[Int]): Int = {
  list.foldLeft(0)(_ + _)
}
val listSum = sum(list)

// Ex5
def length(list: List[Int]): Int = {
  list.foldLeft(0)((len, _) => len + 1)
}
val listLen = length(list)

// Ex6
def average(list: List[Int]): Float = {
  sum(list) / length(list)
}
val listAvg = average(list)

// Ex7
def last(list: List[Int]): Int = {
  list.foldLeft(0)((_, y) => y)
}
val listLast = last(list)

// Ex8
// The sum stays the same w/ foldRight
// The length function must change the x for y
// The average function uses sum and length
// The last function stops working and would have to be changed

// Ex9
//def penultimate(list: List[Int]): Int = {
//  // TODO
//}
//val listPenultimate = penultimate(list)

def reversed(list: List[Int]): List[Int] = {
  list.foldLeft(List[Int]())((x, y) => y :: x)
}
val listReversed = reversed(list)

//def distinct(list: List[Int]): List[Int] = {
//  // TODO
//}
//val distinctElements = distinct(List(1,2,2,3,3,4,5))