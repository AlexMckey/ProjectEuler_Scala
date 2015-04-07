package Problems

//Problem 4
//A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
//Find the largest palindrome made from the product of two 3-digit numbers.
object P004 {
  def checkPalindrom(i: Int) = i.toString == i.toString.reverse
  val M = 100 to 999
  val l = for (i <- M; j <- M) yield i*j
  val res = l.filter(checkPalindrom).max
  println(res)
  assert(res == 906609)
}