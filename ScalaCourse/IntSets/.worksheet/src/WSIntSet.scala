import IntSets._

object WSIntSet {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
  val i1 = new NonEmpty(1, new Empty, new Empty);System.out.println("""i1  : IntSets.NonEmpty = """ + $show(i1 ));$skip(49); 
  val i2 = new NonEmpty(2, new Empty, new Empty);System.out.println("""i2  : IntSets.NonEmpty = """ + $show(i2 ));$skip(22); 
  val i12 = i1 incl 2;System.out.println("""i12  : IntSets.IntSet = """ + $show(i12 ));$skip(22); 
  val i21 = i2 incl 1;System.out.println("""i21  : IntSets.IntSet = """ + $show(i21 ));$skip(49); 
  val i3 = new NonEmpty(3, new Empty, new Empty);System.out.println("""i3  : IntSets.NonEmpty = """ + $show(i3 ));$skip(49); 
  val i6 = new NonEmpty(6, new Empty, new Empty);System.out.println("""i6  : IntSets.NonEmpty = """ + $show(i6 ));$skip(36); 
  val i_1 = i3 incl 1 incl 5 incl 4;System.out.println("""i_1  : IntSets.IntSet = """ + $show(i_1 ));$skip(29); 
  val i_2 = i6 incl 2 incl 7;System.out.println("""i_2  : IntSets.IntSet = """ + $show(i_2 ));$skip(11); val res$0 = 
  i_2.head;System.out.println("""res0: Int = """ + $show(res$0));$skip(11); val res$1 = 
  i_2.tail;System.out.println("""res1: IntSets.IntSet = """ + $show(res$1));$skip(11); val res$2 = 
  i_1.head;System.out.println("""res2: Int = """ + $show(res$2));$skip(11); val res$3 = 
  i_1.tail;System.out.println("""res3: IntSets.IntSet = """ + $show(res$3));$skip(20); val res$4 = 
  i_1 incl i_2.head;System.out.println("""res4: IntSets.IntSet = """ + $show(res$4));$skip(39); val res$5 = 
  i_1 incl i_2.head incl i_2.tail.head;System.out.println("""res5: IntSets.IntSet = """ + $show(res$5));$skip(30); 
  
  val ii1 = i_1 union i_2;System.out.println("""ii1  : IntSets.IntSet = """ + $show(ii1 ));$skip(26); 
  val ii2 = i_2 union i_1;System.out.println("""ii2  : IntSets.IntSet = """ + $show(ii2 ));$skip(31); 
  
  def f = (x: Int) => x < 3;System.out.println("""f: => Int => Boolean""");$skip(27); 
  val fii1 = ii1.filter(f);System.out.println("""fii1  : IntSets.IntSet = """ + $show(fii1 ));$skip(27); 
  val fii2 = ii2.filter(f);System.out.println("""fii2  : IntSets.IntSet = """ + $show(fii2 ))}
  
}