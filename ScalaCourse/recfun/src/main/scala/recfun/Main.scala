package recfun
import common._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  //@tailrec
  def pascal(c: Int, r: Int): Int = {
    (c,r) match {
      case _ if c == 0 || r == c 		=> 1
      //case _ if c == 1 || c == r-1 	=> r
      case _ 							=> pascal(c-1,r-1)+pascal(c,r-1)
    }
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    
    @tailrec
    def baltrav(l: List[Char], cnt: Int = 0): Boolean = {
      l match {
        case Nil 					=> cnt == 0
      	case ')' :: _ if cnt == 0 	=> false
      	case ')' :: tl 				=> baltrav(tl, cnt-1)
      	case '(' :: tl 				=> baltrav(tl, cnt+1)
      	case _ 						=> baltrav(l.tail, cnt)
      }
    }
    
    baltrav(chars)
  } 
    
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    (money, coins) match {
      case _ if money == 0 					=> 1
      case _ if money < 0 || coins.isEmpty 	=> 0
      case _ 								=> countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}
