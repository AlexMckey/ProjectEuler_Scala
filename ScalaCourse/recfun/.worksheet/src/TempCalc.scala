object TempCalc {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(61); 
  println("Welcome to the Scala worksheet");$skip(244); 
  
  def travl(c: Int, l: List[Int], a: List[Int] = Nil, x: Int = 0, i: Int = 0): List[Int] =  {
  	l match {
  		//case _ if c == i => a
  		case _ if l.isEmpty => 1 :: a
  		case _ => travl(c, l.tail, (l.head + x) :: a, l.head, i+1)
  	}
  };System.out.println("""travl: (c: Int, l: List[Int], a: List[Int], x: Int, i: Int)List[Int]""");$skip(23); val res$0 = 
  
  travl(3, List(1));System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(22); val res$1 = 
  travl(3, List(1,1));System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(24); val res$2 = 
  travl(3, List(1,2,1));System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(26); val res$3 = 
  travl(3, List(1,3,3,1));System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(28); val res$4 = 
  travl(4, List(1,4,6,4,1));System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(32); val res$5 = 
  travl(4, List(1,5,10,10,5,1));System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(220); 
  
  def travlx(c: Int, l: List[Int], x: Int = 0, i: Int = 0): List[Int] =  {
  	l match {
  		case _ if c == i => Nil
  		case _ if l.isEmpty => Nil
  		case _ => (l.head + x) :: travlx(c, l.tail, l.head, i+1)
  	}
  };System.out.println("""travlx: (c: Int, l: List[Int], x: Int, i: Int)List[Int]""");$skip(24); val res$6 = 
  
  travlx(3, List(1));System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(23); val res$7 = 
  travlx(3, List(1,1));System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(25); val res$8 = 
  travlx(3, List(1,2,1));System.out.println("""res8: List[Int] = """ + $show(res$8));$skip(25); val res$9 = 
  travlx(3, List(1,3,3));System.out.println("""res9: List[Int] = """ + $show(res$9));$skip(27); val res$10 = 
  travlx(4, List(1,4,6,4));System.out.println("""res10: List[Int] = """ + $show(res$10));$skip(26); val res$11 = 
  travlx(3, List(1,5,10));System.out.println("""res11: List[Int] = """ + $show(res$11));$skip(22); val res$12 = 
  
  "()".toList.head;System.out.println("""res12: Char = """ + $show(res$12));$skip(26); val res$13 = 
  "()".toList.head == '(';System.out.println("""res13: Boolean = """ + $show(res$13));$skip(366); 
  
  def bal(chs: List[Char], cnt: Int = 0): Boolean = {
  	chs match {
  		case _ if chs.isEmpty && cnt == 0 => true
  		case _ if chs.isEmpty && cnt != 0 => false
  		case _ if chs.head == ')' && cnt == 0 => false
  		case _ if chs.head == '(' => bal(chs.tail, cnt+1)
  		case _ if chs.head == ')' => bal(chs.tail, cnt-1)
  		case _ => bal(chs.tail, cnt)
  	}
  };System.out.println("""bal: (chs: List[Char], cnt: Int)Boolean""");$skip(31); val res$14 = 
  
  bal("asdfas(ghj)".toList);System.out.println("""res14: Boolean = """ + $show(res$14));$skip(36); val res$15 = 
  bal("asdf(as(ghj)sfgdf )".toList);System.out.println("""res15: Boolean = """ + $show(res$15));$skip(31); val res$16 = 
  bal("asd(fas((ghj)(".toList);System.out.println("""res16: Boolean = """ + $show(res$16));$skip(31); val res$17 = 
  bal("asd(fas((ghj)(".toList);System.out.println("""res17: Boolean = """ + $show(res$17))}
}