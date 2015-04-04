object tmp {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
	def checkPalindrom(i: Int) = i.toString == i.toString.reverse;System.out.println("""checkPalindrom: (i: Int)Boolean""");$skip(22); val res$0 = 
	checkPalindrom(9009);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(22); 
	val l = (100 to 999);System.out.println("""l  : scala.collection.immutable.Range.Inclusive = """ + $show(l ));$skip(41); 
	val fl = for (i <- l; j <- l) yield i*j;System.out.println("""fl  : scala.collection.immutable.IndexedSeq[Int] = """ + $show(fl ));$skip(36); 
  val p = fl.filter(checkPalindrom);System.out.println("""p  : scala.collection.immutable.IndexedSeq[Int] = """ + $show(p ));$skip(8); val res$1 = 
  p.max;System.out.println("""res1: Int = """ + $show(res$1))}
}
