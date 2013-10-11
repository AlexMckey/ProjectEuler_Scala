object TempCalc {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def travl(c: Int, l: List[Int], a: List[Int] = Nil, x: Int = 0, i: Int = 0): List[Int] =  {
  	l match {
  		//case _ if c == i => a
  		case _ if l.isEmpty => 1 :: a
  		case _ => travl(c, l.tail, (l.head + x) :: a, l.head, i+1)
  	}
  }                                               //> travl: (c: Int, l: List[Int], a: List[Int], x: Int, i: Int)List[Int]
  
  travl(3, List(1))                               //> res0: List[Int] = List(1, 1)
  travl(3, List(1,1))                             //> res1: List[Int] = List(1, 2, 1)
  travl(3, List(1,2,1))                           //> res2: List[Int] = List(1, 3, 3, 1)
  travl(3, List(1,3,3,1))                         //> res3: List[Int] = List(1, 4, 6, 4, 1)
  travl(4, List(1,4,6,4,1))                       //> res4: List[Int] = List(1, 5, 10, 10, 5, 1)
  travl(4, List(1,5,10,10,5,1))                   //> res5: List[Int] = List(1, 6, 15, 20, 15, 6, 1)
  
  def travlx(c: Int, l: List[Int], x: Int = 0, i: Int = 0): List[Int] =  {
  	l match {
  		case _ if c == i => Nil
  		case _ if l.isEmpty => Nil
  		case _ => (l.head + x) :: travlx(c, l.tail, l.head, i+1)
  	}
  }                                               //> travlx: (c: Int, l: List[Int], x: Int, i: Int)List[Int]
  
  travlx(3, List(1))                              //> res6: List[Int] = List(1)
  travlx(3, List(1,1))                            //> res7: List[Int] = List(1, 2)
  travlx(3, List(1,2,1))                          //> res8: List[Int] = List(1, 3, 3)
  travlx(3, List(1,3,3))                          //> res9: List[Int] = List(1, 4, 6)
  travlx(4, List(1,4,6,4))                        //> res10: List[Int] = List(1, 5, 10, 10)
  travlx(3, List(1,5,10))                         //> res11: List[Int] = List(1, 6, 15)
  
  "()".toList.head                                //> res12: Char = (
  "()".toList.head == '('                         //> res13: Boolean = true
  
  def bal(chs: List[Char], cnt: Int = 0): Boolean = {
  	chs match {
  		case _ if chs.isEmpty && cnt == 0 => true
  		case _ if chs.isEmpty && cnt != 0 => false
  		case _ if chs.head == ')' && cnt == 0 => false
  		case _ if chs.head == '(' => bal(chs.tail, cnt+1)
  		case _ if chs.head == ')' => bal(chs.tail, cnt-1)
  		case _ => bal(chs.tail, cnt)
  	}
  }                                               //> bal: (chs: List[Char], cnt: Int)Boolean
  
  bal("asdfas(ghj)".toList)                       //> res14: Boolean = true
  bal("asdf(as(ghj)sfgdf )".toList)               //> res15: Boolean = true
  bal("asd(fas((ghj)(".toList)                    //> res16: Boolean = false
  bal("asd(fas((ghj)(".toList)                    //> res17: Boolean = false
}