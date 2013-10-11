package forcomp
import forcomp._
import forcomp.Anagrams._
 
object ForWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(93); 
  val w = "World";System.out.println("""w  : java.lang.String = """ + $show(w ));$skip(97); val res$0 = 
  w.toLowerCase.groupBy(c => c).map({case(c,l) => (c,l.length)}).toList.sortBy({case(c,_) => c});System.out.println("""res0: List[(Char, Int)] = """ + $show(res$0));$skip(48); 
  val s = List("Sasha","Alex","Mckey","Shurik");System.out.println("""s  : List[java.lang.String] = """ + $show(s ));$skip(30); val res$1 = 
  wordOccurrences(s.mkString);System.out.println("""res1: forcomp.Anagrams.Occurrences = """ + $show(res$1));$skip(46); val res$2 = 
  dictionary.groupBy(wordOccurrences).take(5);System.out.println("""res2: scala.collection.immutable.Map[forcomp.Anagrams.Occurrences,List[forcomp.Anagrams.Word]] = """ + $show(res$2));$skip(23); val res$3 = 
 
	wordAnagrams("tea");System.out.println("""res3: List[forcomp.Anagrams.Word] = """ + $show(res$3));$skip(32); val res$4 = 
	"dgdfg".combinations(2).toList;System.out.println("""res4: List[String] = """ + $show(res$4));$skip(35); 
	val ll = List(('a', 2), ('b', 2));System.out.println("""ll  : List[(Char, Int)] = """ + $show(ll ));$skip(163); val res$5 = 
	"aabb".combinations(0).toList ++ "aabb".combinations(1).toList ++ "aabb".combinations(2).toList ++ "aabb".combinations(3).toList ++ "aabb".combinations(4).toList;System.out.println("""res5: List[String] = """ + $show(res$5));$skip(56); 
  val st = ll.map({case(c,i) => c.toString*i}).mkString;System.out.println("""st  : String = """ + $show(st ));$skip(70); 
  val lst = for (i <- 0 to st.length) yield st.combinations(i).toList;System.out.println("""lst  : scala.collection.immutable.IndexedSeq[List[String]] = """ + $show(lst ));$skip(23); 
  val fl = lst.flatten;System.out.println("""fl  : scala.collection.immutable.IndexedSeq[String] = """ + $show(fl ));$skip(37); 
  val comb = fl.map(wordOccurrences);System.out.println("""comb  : scala.collection.immutable.IndexedSeq[forcomp.Anagrams.Occurrences] = """ + $show(comb ));$skip(222); 
  def myComb(occurrences: Occurrences): List[Occurrences] = {
  	val st = ll.map({case(c,i) => c.toString*i}).mkString
  	(for (i <- 0 to st.length) yield st.combinations(i).toList).flatten.map(wordOccurrences).toList
  };System.out.println("""myComb: (occurrences: forcomp.Anagrams.Occurrences)List[forcomp.Anagrams.Occurrences]""");$skip(28); 
  
  val comb1 = myComb(ll);System.out.println("""comb1  : List[forcomp.Anagrams.Occurrences] = """ + $show(comb1 ));$skip(19); val res$6 = 
  "aabb" diff "ab";System.out.println("""res6: String = """ + $show(res$6));$skip(141); val res$7 = 
  
  ("aabb".combinations(0) ++ "aabb".combinations(1) ++ "aabb".combinations(2) ++ "aabb".combinations(3) ++ "aabb".combinations(4)).toList;System.out.println("""res7: List[String] = """ + $show(res$7))}
}