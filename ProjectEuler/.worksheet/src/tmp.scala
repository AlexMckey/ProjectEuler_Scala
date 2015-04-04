object tmp {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(36); 
	val n = 600851475143L;System.out.println("""n  : Long = """ + $show(n ));$skip(142); ;
	def factors(n: Long): List[Long] =
  (2 to math.sqrt(n).toInt).find(n % _ == 0)
    .map(i => i.toLong :: factors(n / i)).getOrElse(List(n));System.out.println("""factors: (n: Long)List[Long]""");$skip(13); val res$0 = 
  factors(n);System.out.println("""res0: List[Long] = """ + $show(res$0));$skip(14); val res$1 = 
  factors(91);System.out.println("""res1: List[Long] = """ + $show(res$1));$skip(17); val res$2 = 
  factors(13195);System.out.println("""res2: List[Long] = """ + $show(res$2))}
}
