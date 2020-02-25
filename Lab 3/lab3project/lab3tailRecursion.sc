// TAIL RECURSION
import scala.collection.immutable.::

val list1 = List(1,2,3,4,5,6,7,8,9,10)
var list2 = List.range(1, 11)

// Ex1
def listLength(ls:List[_]):Int = ls match{
  case Nil => 0
  case h :: tail => 1 + listLength(ls.tail)
}

// Ex2
1 to 15 foreach(x=> list2 = list2++list2)
//val len = listLength(list2)
// Stack Overflow error appears as maximum amount of recursive cycles have been performed

// Ex3
def listLengthFixed(ls:List[_]):Int = {
  def listLength_nested(ls:List[_], len: Int):Int = ls match{
    case Nil => len
    case h :: tail => listLength_nested(ls.tail, len+1)
  }
  listLength_nested(ls, 0)
}

val len = listLengthFixed(list2)
// Length function doesn't crash anymore
// Tail recursive function symbol appears now

// Ex4
def sum(ls: List[Int]): Int = {
  def sum_nested(ls: List[Int], sum: Int): Int = ls match {
    case Nil => sum + ls.head
    case h::tail => sum_nested(ls.tail, sum + ls.head)
  }
  sum_nested(ls, 0)
}

val listSum = sum(list1)
val listSumCheck = list1.sum
val listSum1 = sum(list2)
val listSum1Check = list2.sum

// Ex5


// Ex6
