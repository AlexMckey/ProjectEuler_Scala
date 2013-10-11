import IntSets._

object WSIntSet {
  val i1 = new NonEmpty(1, new Empty, new Empty)  //> i1  : IntSets.NonEmpty = {.1.}
  val i2 = new NonEmpty(2, new Empty, new Empty)  //> i2  : IntSets.NonEmpty = {.2.}
  val i12 = i1 incl 2                             //> i12  : IntSets.IntSet = {.1{.2.}}
  val i21 = i2 incl 1                             //> i21  : IntSets.IntSet = {{.1.}2.}
  val i3 = new NonEmpty(3, new Empty, new Empty)  //> i3  : IntSets.NonEmpty = {.3.}
  val i6 = new NonEmpty(6, new Empty, new Empty)  //> i6  : IntSets.NonEmpty = {.6.}
  val i_1 = i3 incl 1 incl 5 incl 4               //> i_1  : IntSets.IntSet = {{.1.}3{{.4.}5.}}
  val i_2 = i6 incl 2 incl 7                      //> i_2  : IntSets.IntSet = {{.2.}6{.7.}}
  i_2.head                                        //> res0: Int = 2
  i_2.tail                                        //> res1: IntSets.IntSet = {.6{.7.}}
  i_1.head                                        //> res2: Int = 1
  i_1.tail                                        //> res3: IntSets.IntSet = {.3{{.4.}5.}}
  i_1 incl i_2.head                               //> res4: IntSets.IntSet = {{.1{.2.}}3{{.4.}5.}}
  i_1 incl i_2.head incl i_2.tail.head            //> res5: IntSets.IntSet = {{.1{.2.}}3{{.4.}5{.6.}}}
  
  val ii1 = i_1 union i_2                         //> ii1  : IntSets.IntSet = {{.1{.2.}}3{{.4.}5{{.6.}7.}}}
  val ii2 = i_2 union i_1                         //> ii2  : IntSets.IntSet = {{{.1.}2{{{.3.}4.}5.}}6{.7.}}
  
  def f = (x: Int) => x < 3                       //> f: => Int => Boolean
  val fii1 = ii1.filter(f)                        //> fii1  : IntSets.IntSet = {.1{.2.}}
  val fii2 = ii2.filter(f)                        //> fii2  : IntSets.IntSet = {.1{.2.}}
  
}