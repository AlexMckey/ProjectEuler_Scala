package funsets
import FunSets._
  
object wEEK2ws {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def myforall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (contains(s,a) && !p(a)) false
      else iter(a+1)
    }
    iter(-1000)
  }                                               //> myforall: (s: Int => Boolean, p: Int => Boolean)Boolean
  
  val s = union(singletonSet(2),union(singletonSet(4),singletonSet(6)))
                                                  //> s  : Int => Boolean = <function1>
  printSet(s)                                     //> {2,4,6}
  myforall(s, x => x % 2 == 0)                    //> res0: Boolean = true
  
  val s1 = intersect(x => x >= 0, x => x <= 20)   //> s1  : Int => Boolean = <function1>
  printSet(s1)                                    //> {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,...}
  myforall(s1, x => x > 10)                       //> res1: Boolean = false
  myforall(s1, x => x < 10)                       //> res2: Boolean = false
  myforall(s1, x => x >= 0)                       //> res3: Boolean = true
  myforall(s1, x => x > -10 && x < 30)            //> res4: Boolean = true
  
  val s3 = union(singletonSet(1), x => x == 3 || x == 4 || x == 5 || x == 7 || x == 1000)
                                                  //> s3  : Int => Boolean = <function1>
	printSet(s3)                              //> {1,3,4,5,7,1000}
	exists(s3, x => x == 2)                   //> res5: Boolean = false
	exists(s3, x => x == 3)                   //> res6: Boolean = true
  
  val ss = intersect(x => x >= 2, x => x <= 5)    //> ss  : Int => Boolean = <function1>
  printSet(ss)                                    //> {2,3,4,5}
    
  def mymap(s: Set, f: Int => Int): Set = b => exists(s, a => b == f(a))
                                                  //> mymap: (s: Int => Boolean, f: Int => Int)Int => Boolean
  val ms = mymap(ss, x => x * 2)                  //> ms  : Int => Boolean = <function1>
  
  printSet(ms)                                    //> {4,6,8,10}
  
  var sss: Set = singletonSet(0)                  //> sss  : Int => Boolean = <function1>
  for (i <- -bound to bound) {if (ss(i)) sss = union(sss,singletonSet(i * 2))}
  printSet(sss)                                   //> {0,4,6,8,10}
  
  val s2 = (x: Int) => true                       //> s2  : Int => Boolean = <function1>
  forall(s2, (y: Int) => true)                    //> res7: Boolean = true
  
  def divisors(x:Int):Set =  p=> p>=1 && x%p == 0 //> divisors: (x: Int)Int => Boolean
 	def isPrime(x:Int):Boolean = x>1 && forall(divisors(x),p => (p==x || p==1))
                                                  //> isPrime: (x: Int)Boolean
 	
 	isPrime(3)                                //> res8: Boolean = true
 	isPrime(1111)                             //> res9: Boolean = false
 	isPrime(11111)                            //> res10: Boolean = false
 	isPrime(111111)                           //> res11: Boolean = false
 	isPrime(1111111)                          //> res12: Boolean = false
                                                  //| Output exceeds cutoff limit. 
}