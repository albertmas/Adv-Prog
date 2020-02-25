// LISTS AND RECURSION
// Ex1
import scala.collection.immutable.::

// Ex2
val list1 = List(1,2,3,4,5,6,7,8,9,10)
var list2 = List.range(1, 11)

// Ex3
val list3 = list1::list2 // Creates a list of the first list and the elements of the second list
val list4 = list1:::list2 // Joins both lists into a single lists
val list5 = list1++list2 // It behaves like the ::: op

// Ex4
def getLastElement(list: List[Int]): Int = {
  if (list.tail.isEmpty)
    list.head
  else
    getLastElement(list.tail)
}
val len = getLastElement(list1)

// Ex5
def lastif(ls:List[Int]):Int = {
  if(ls.tail == Nil)
    ls.head
  else
    lastif(ls.tail)
}

val len1 = lastif(list1)

// Ex6
def last(ls: List[Int]): Int = ls match {
  case h :: Nil => h
  case _ :: tail => last(tail)
}

val len2 = last(list1)

// Ex7
def sum(list: List[Int]): Int = {
  if (list.tail == Nil)
    list.head
  else
    sum(list.init) + list.last
}

val listSum = sum(list1)
val listSumCheck = list1.sum