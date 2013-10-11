package forcomp
import forcomp._
import forcomp.Anagrams._

object AnagWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(101); 
  val l1 = List(('i', 1));System.out.println("""l1  : List[(Char, Int)] = """ + $show(l1 ));$skip(27); 
  val l1_ = List(('I', 1));System.out.println("""l1_  : List[(Char, Int)] = """ + $show(l1_ ));$skip(34); 
  val l2 = List(('b', 1),('y',1));System.out.println("""l2  : List[(Char, Int)] = """ + $show(l2 ));$skip(42); 
  val l3 = List(('e', 1),('n',1),('o',1));System.out.println("""l3  : List[(Char, Int)] = """ + $show(l3 ));$skip(30); val res$0 = 
  dictionaryByOccurrences(l1);System.out.println("""res0: List[forcomp.Anagrams.Word] = """ + $show(res$0));$skip(31); val res$1 = 
  dictionaryByOccurrences(l1_);System.out.println("""res1: List[forcomp.Anagrams.Word] = """ + $show(res$1));$skip(30); val res$2 = 
  dictionaryByOccurrences(l2);System.out.println("""res2: List[forcomp.Anagrams.Word] = """ + $show(res$2));$skip(30); val res$3 = 
  dictionaryByOccurrences(l3);System.out.println("""res3: List[forcomp.Anagrams.Word] = """ + $show(res$3));$skip(29); 
  val l = List("Yes", "man");System.out.println("""l  : List[java.lang.String] = """ + $show(l ));$skip(34); 
  val oc = sentenceOccurrences(l);System.out.println("""oc  : forcomp.Anagrams.Occurrences = """ + $show(oc ));$skip(27); 
  val c = combinations(oc);System.out.println("""c  : List[forcomp.Anagrams.Occurrences] = """ + $show(c ));$skip(50); val res$4 = 
  
  (c map dictionaryByOccurrences).toSet.toList;System.out.println("""res4: List[List[forcomp.Anagrams.Word]] = """ + $show(res$4));$skip(450); 
  def mySentenceAnagrams(sentence: Sentence): List[Sentence] = {
  	def sentenceAnagrams0(occ: Occurrences, acc: List[Sentence]): List[Sentence] = {
    	if (occ.isEmpty) acc
    	else {
      	for {
      		comb <- combinations(occ)
      		word <- dictionaryByOccurrences(comb)
      		rest <- sentenceAnagrams0(subtract(occ, comb),acc)
      	} yield word :: rest
    	}
    }
    sentenceAnagrams0(sentenceOccurrences(sentence),List(List()))
  };System.out.println("""mySentenceAnagrams: (sentence: forcomp.Anagrams.Sentence)List[forcomp.Anagrams.Sentence]""");$skip(41); val res$5 = 
  mySentenceAnagrams(List("Yes", "man"));System.out.println("""res5: List[forcomp.Anagrams.Sentence] = """ + $show(res$5));$skip(50); 
  val x = sentenceOccurrences(List("Yes", "man"));System.out.println("""x  : forcomp.Anagrams.Occurrences = """ + $show(x ));$skip(29); 
  val comb = combinations(x);System.out.println("""comb  : List[forcomp.Anagrams.Occurrences] = """ + $show(comb ));$skip(33); 
  val z = List(('e',1), ('m',1));System.out.println("""z  : List[(Char, Int)] = """ + $show(z ));$skip(37); 
  val y = dictionaryByOccurrences(z);System.out.println("""y  : List[forcomp.Anagrams.Word] = """ + $show(y ));$skip(26); 
  val sr = subtract(x, z);System.out.println("""sr  : forcomp.Anagrams.Occurrences = """ + $show(sr ))}
}