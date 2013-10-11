package funsets
import FunSets._
  
object wEEK2ws {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(96); 
  println("Welcome to the Scala worksheet");$skip(210); 
  
  def myforall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (contains(s,a) && !p(a)) false
      else iter(a+1)
    }
    iter(-1000)
  };System.out.println("""myforall: (s: Int => Boolean, p: Int => Boolean)Boolean""");$skip(75); 
  
  val s = union(singletonSet(2),union(singletonSet(4),singletonSet(6)));System.out.println("""s  : Int => Boolean = """ + $show(s ));$skip(14); 
  printSet(s);$skip(31); val res$0 = 
  myforall(s, x => x % 2 == 0);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(51); 
  
  val s1 = intersect(x => x >= 0, x => x <= 20);System.out.println("""s1  : Int => Boolean = """ + $show(s1 ));$skip(15); 
  printSet(s1);$skip(28); val res$1 = 
  myforall(s1, x => x > 10);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(28); val res$2 = 
  myforall(s1, x => x < 10);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(28); val res$3 = 
  myforall(s1, x => x >= 0);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(39); val res$4 = 
  myforall(s1, x => x > -10 && x < 30);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(93); 
  
  val s3 = union(singletonSet(1), x => x == 3 || x == 4 || x == 5 || x == 7 || x == 1000);System.out.println("""s3  : Int => Boolean = """ + $show(s3 ));$skip(14); 
	printSet(s3);$skip(25); val res$5 = 
	exists(s3, x => x == 2);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(25); val res$6 = 
	exists(s3, x => x == 3);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(50); 
  
  val ss = intersect(x => x >= 2, x => x <= 5);System.out.println("""ss  : Int => Boolean = """ + $show(ss ));$skip(15); 
  printSet(ss);$skip(78); 
    
  def mymap(s: Set, f: Int => Int): Set = b => exists(s, a => b == f(a));System.out.println("""mymap: (s: Int => Boolean, f: Int => Int)Int => Boolean""");$skip(33); 
  val ms = mymap(ss, x => x * 2);System.out.println("""ms  : Int => Boolean = """ + $show(ms ));$skip(18); 
  
  printSet(ms);$skip(36); 
  
  var sss: Set = singletonSet(0);System.out.println("""sss  : Int => Boolean = """ + $show(sss ));$skip(79); 
  for (i <- -bound to bound) {if (ss(i)) sss = union(sss,singletonSet(i * 2))};$skip(16); 
  printSet(sss);$skip(31); 
  
  val s2 = (x: Int) => true;System.out.println("""s2  : Int => Boolean = """ + $show(s2 ));$skip(31); val res$7 = 
  forall(s2, (y: Int) => true);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(53); 
  
  def divisors(x:Int):Set =  p=> p>=1 && x%p == 0;System.out.println("""divisors: (x: Int)Int => Boolean""");$skip(78); 
 	def isPrime(x:Int):Boolean = x>1 && forall(divisors(x),p => (p==x || p==1));System.out.println("""isPrime: (x: Int)Boolean""");$skip(16); val res$8 = 
 	
 	isPrime(3);System.out.println("""res8: Boolean = """ + $show(res$8));$skip(16); val res$9 = 
 	isPrime(1111);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(17); val res$10 = 
 	isPrime(11111);System.out.println("""res10: Boolean = """ + $show(res$10));$skip(18); val res$11 = 
 	isPrime(111111);System.out.println("""res11: Boolean = """ + $show(res$11));$skip(19); val res$12 = 
 	isPrime(1111111);System.out.println("""res12: Boolean = """ + $show(res$12))}
}