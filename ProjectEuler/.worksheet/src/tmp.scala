object tmp {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(32); 
	val ns = (1 to 10);System.out.println("""ns  : scala.collection.immutable.Range.Inclusive = """ + $show(ns ));$skip(24); 
  def sqr(x: Int) = x*x;System.out.println("""sqr: (x: Int)Int""");$skip(27); 
	val sqs = ns.map(sqr).sum;System.out.println("""sqs  : Int = """ + $show(sqs ));$skip(23); 
	val ssq = sqr(ns.sum);System.out.println("""ssq  : Int = """ + $show(ssq ));$skip(9); val res$0 = 
	ssq-sqs;System.out.println("""res0: Int = """ + $show(res$0))}
}
