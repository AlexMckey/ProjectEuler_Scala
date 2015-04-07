package Problems

//Problem 5
//2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
//What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
object P005 {
  val M = 2L to 20
  def gcd(a: Long, b: Long): Long = if(b == 0) a else gcd(b, a % b)
  def lcm(x: Long, y: Long) = x*y/gcd(x,y)
  val res = M.foldRight(1L)(lcm)
  println(res)
  assert(res == 232792560)
}