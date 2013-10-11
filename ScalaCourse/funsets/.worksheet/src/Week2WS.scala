object wEEK2ws {
  
  import FunSets._import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); val res$0 = ;
  
  println("Welcome to the Scala worksheet");System.out.println("""res0: <error> = """ + $show(res$0));$skip(19); 
  val bound = 1000
  type Set = Int => Boolean;System.out.println("""bound  : Int = """ + $show(bound ));$skip(81); 
  def contains(s: Set, elem: Int): Boolean = s(elem);System.out.println("""contains: (s: <error> => <error>, elem: <error>)<error>""");$skip(135); 
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")  };System.out.println("""toString: (s: <error> => <error>)<error>""");$skip(53); 
  def printSet(s: Set) {
    println(toString(s))  };System.out.println("""printSet: (s: <error> => <error>)Unit""");$skip(58); 
  
  def mymap(s: Set, f: Int => Int): Set = x => s(f(x));System.out.println("""mymap: (s: <error> => <error>, f: <error> => <error>)<error> => <error>""");$skip(54); 
  
  val s = mymap(x => x >= 2 && x <= 5, x => x * 2);System.out.println("""s  : <error> => <error> = """ + $show(s ));$skip(17); 
  
  printSet(s)}
}