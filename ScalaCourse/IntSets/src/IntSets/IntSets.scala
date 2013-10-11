package IntSets

abstract class IntSet {
  def filter(p: Int => Boolean): IntSet = filter0(p, new Empty)
  def filter0(p: Int => Boolean, accu: IntSet): IntSet

  def union(that: IntSet): IntSet = {
    if (that.isEmpty) this
    else this union that.tail incl that.head
  }
  
  // The following methods are provided for you, and do not have to be changed
  // -------------------------------------------------------------------------
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def isEmpty: Boolean
  def head: Int
  def tail: IntSet

  /** This method takes a function and applies it to every element in the set.
   */
  def foreach(f: Int => Unit): Unit = {
    if (!this.isEmpty) {
      f(this.head)
      this.tail.foreach(f)
    }
  }

  def remove(tw: Int): IntSet

  def findMin0(curr: Int): Int =
    if (this.isEmpty) curr
    else if (this.head < curr) this.tail.findMin0(this.head)
    else this.tail.findMin0(curr)

  def findMin: Int =
    this.tail.findMin0(this.head)
}

class Empty extends IntSet {

  def filter0(p: Int => Boolean, accu: IntSet): IntSet = accu
  
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  def isEmpty = true
  def head = throw new Exception("Empty.head")
  def tail = throw new Exception("Empty.tail")
  def remove(tw: Int): IntSet = this
  override def toString = "." 
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def filter0(p: Int => Boolean, accu: IntSet): IntSet = 
    //if (p(head)) {println("h:" + head + "\nt:" + tail + "\na:" + accu); tail.filter0(p, accu incl head)}
    //else {println("h:" + head + "\nt:" + tail + "\na:" + accu); tail.filter0(p, accu)}
    if (p(head)) tail.filter0(p, accu incl head)
    else tail.filter0(p, accu)
  
  def contains(x: Int): Boolean =
    if (x < elem) left.contains(x)
    else if (elem < x) right.contains(x)
    else true

  def incl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left.incl(x), right)
    else if (elem < x) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def isEmpty = false
  def head = if (left.isEmpty) elem else left.head
  def tail = if (left.isEmpty) right else new NonEmpty(elem, left.tail, right)

  def remove(tw: Int): IntSet =
    if (tw < elem) new NonEmpty(elem, left.remove(tw), right)
    else if (elem < tw) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)
    
  override def toString = "{" + left + elem + right + "}"
}

object Main extends App {
  val i1 = new NonEmpty(1, new Empty, new Empty)  //> i1  : IntSets.NonEmpty = {.1.}
  val i2 = new NonEmpty(2, new Empty, new Empty)  //> i2  : IntSets.NonEmpty = {.2.}
  val i12 = i1 incl 2                             //> i12  : IntSets.IntSet = {.1{.2.}}
  val i21 = i2 incl 1                             //> i21  : IntSets.IntSet = {{.1.}2.}
  val i3 = new NonEmpty(3, new Empty, new Empty)  //> i3  : IntSets.NonEmpty = {.3.}
  val i6 = new NonEmpty(6, new Empty, new Empty)  //> i6  : IntSets.NonEmpty = {.6.}
  val i_1 = i3 incl 1 incl 5 incl 4               //> i_1  : IntSets.IntSet = {{.1.}3{{.4.}5.}}
  val i_2 = i6 incl 2 incl 7                      //> i_2  : IntSets.IntSet = {{.2.}6{.7.}}
  println(i_1.filter(x => x < 5))
}