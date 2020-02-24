// RECURSION
// Ex1
def factorial(n: Int): BigInt =  {
  if (n == 0)
    1
  else
    n * factorial (n - 1)
}


def factorialMatch(n: Int): BigInt = n match {
  case 0 => 1
  case _ => factorialMatch (n-1) * n
}

val fac = factorial(10)
val fac1 = factorialMatch(10)

// Ex2
def gcd(n1: Int, n2: Int): Int = n2 match {
  case 0 => n1
  case _ => gcd(n2, n1 % n2)
}

val resultGCD = gcd(12, 1300)

// Ex3
def power(value: Int, pow: Int): BigInt = pow match {
  case 1 => value
  case _ => value * power(value, pow - 1)
}

val poweredVal = power(3, 3)