object GreetWorkSheet {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(67); 
  println("Welcome to the Scala worksheet");$skip(15); 
  
  val x = 4;System.out.println("""x  : Int = """ + $show(x ));$skip(31); 
  def increase(i: Int) = i + 2;System.out.println("""increase: (i: Int)Int""");$skip(14); val res$0 = 
  increase(x);System.out.println("""res0: Int = """ + $show(res$0));$skip(24); val res$1 = 
  increase(increase(x));System.out.println("""res1: Int = """ + $show(res$1))}
}