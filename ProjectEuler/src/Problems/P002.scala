package Problems

//Problem 2
//Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
//1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
//By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
object P002 {
  lazy val f: Stream[Int] = Stream.cons(1, Stream.cons(2, f.zip(f.tail).map(p => p._1+p._2)))
  val res = f.filter(_%2==0).takeWhile(_<=4000000).sum
  println(res)
  assert(res == 4613732)
}