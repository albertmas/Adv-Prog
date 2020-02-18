// STRING
val myString = "A Santa Lived As a Devil At NASA"

// Ex1
val revString = myString.reverse

// Ex2
def isPalindrome(string: String) : Boolean = {
  var res = false
  val copy = string.toLowerCase().filter((x: Char) => x.isLetterOrDigit)
  if (copy.equals(copy.reverse)) {
    res = true
  }
  res
}
println(isPalindrome(myString)) // It is a Palindrome

// Ex3
val modString = myString.tail
println(isPalindrome(modString)) // It is NOT a Palindrome anymore
val numSpaces = myString.count((x: Char) => x.isWhitespace)
val numUniqueChars = myString.toLowerCase().toSeq.distinct.unwrap.filterNot((x: Char) => x.isWhitespace).length

// Ex4
val myString1 = "A man, a plan, a canal, Panama"
println(isPalindrome(myString1))