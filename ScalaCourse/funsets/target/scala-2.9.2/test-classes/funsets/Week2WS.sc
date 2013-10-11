import FunSets._

object Week2WS {
  println("Welcome to the Scala worksheet")
  
  def mymap(s: Set, f: Int => Int): Set = x => s(f(x))
}