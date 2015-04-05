object tmp {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(79); 
	def gcd(a: Long, b: Long): Long = if(b == 0) a else gcd(b, a % b);System.out.println("""gcd: (a: Long, b: Long)Long""");$skip(42); 
	def lcm(x: Long, y: Long) = x*y/gcd(x,y);System.out.println("""lcm: (x: Long, y: Long)Long""");$skip(19); 
	val M = 2L to 20L;System.out.println("""M  : scala.collection.immutable.NumericRange.Inclusive[Long] = """ + $show(M ));$skip(23); val res$0 = 
  M.foldRight(1L)(lcm);System.out.println("""res0: Long = """ + $show(res$0))}
}
