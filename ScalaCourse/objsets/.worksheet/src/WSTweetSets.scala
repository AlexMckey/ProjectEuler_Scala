import objsets._

trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

object WSTweetSets {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(408); val res$0 = 
  new TestSets {
  	println(set5.findMin)
  };System.out.println("""res0: java.lang.Object with TestSets = """ + $show(res$0))}
  object WSTweetSets {
   new TestSets {
   		(set5.head)
   		(set5.tail)
   }
}