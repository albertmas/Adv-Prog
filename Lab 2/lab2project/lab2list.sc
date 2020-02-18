// LIST
val myList = List(1, 3, 1, 7, 9, 5)

// Ex1
val first = myList.head // Returns the first element of the list

// Ex2
val tailList = myList.tail // Returns all elements except the first

// Ex3
val sum = myList.sum // Returns the sum of all the elements as an Int
val revList = myList.reverse // Returns a list in reverse order

// Ex4
val split = myList.splitAt(3) // Returns 2 separate lists splitting the original at a given position

// Ex5
val filtered = myList.filter(p => p < 6) // Returns a list with the elements smaller than 6
val filtered1 = myList.filter(p => p < 4) // Returns a list with the elements smaller than 4

// Ex6
val numOnes = myList.filter(p => p == 1).length
val numOnes1 = myList.count(p => p == 1)

// Ex7
val splitRev = myList.filter(p => p < 6).reverse.splitAt(2)

// Ex8
val low = myList.takeRight(3).min // Takes 3 last elements and finds the smallest one
