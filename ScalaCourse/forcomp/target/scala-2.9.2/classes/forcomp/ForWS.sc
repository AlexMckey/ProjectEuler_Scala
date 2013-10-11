package forcomp
import forcomp._
import forcomp.Anagrams._
 
object ForWS {
  val w = "World"                                 //> w  : java.lang.String = World
  w.toLowerCase.groupBy(c => c).map({case(c,l) => (c,l.length)}).toList.sortBy({case(c,_) => c})
                                                  //> res0: List[(Char, Int)] = List((d,1), (l,1), (o,1), (r,1), (w,1))
  val s = List("Sasha","Alex","Mckey","Shurik")   //> s  : List[java.lang.String] = List(Sasha, Alex, Mckey, Shurik)
  wordOccurrences(s.mkString)                     //> res1: forcomp.Anagrams.Occurrences = List((a,3), (c,1), (e,2), (h,2), (i,1),
                                                  //|  (k,2), (l,1), (m,1), (r,1), (s,3), (u,1), (x,1), (y,1))
  dictionary.groupBy(wordOccurrences).take(5)     //> res2: scala.collection.immutable.Map[forcomp.Anagrams.Occurrences,List[forco
                                                  //| mp.Anagrams.Word]] = Map(List((c,2), (e,2), (h,1), (r,1), (s,1)) -> List(scr
                                                  //| eech), List((a,2), (l,1), (r,1), (s,1), (t,1)) -> List(altars, astral), List
                                                  //| ((e,1), (f,2), (i,1), (n,1), (s,2), (t,1)) -> List(stiffens), List((b,1), (d
                                                  //| ,1), (e,3), (h,1), (i,1), (k,1), (r,1), (s,1), (w,1)) -> List(bewhiskered), 
                                                  //| List((c,1), (d,1), (e,2), (f,1), (i,1), (n,1), (t,1)) -> List(infected))
 
	wordAnagrams("tea")                       //> res3: List[forcomp.Anagrams.Word] = List(ate, eat, tea)
	"dgdfg".combinations(2).toList            //> res4: List[String] = List(dd, dg, df, gg, gf)
	val ll = List(('a', 2), ('b', 2))         //> ll  : List[(Char, Int)] = List((a,2), (b,2))
	"aabb".combinations(0).toList ++ "aabb".combinations(1).toList ++ "aabb".combinations(2).toList ++ "aabb".combinations(3).toList ++ "aabb".combinations(4).toList
                                                  //> res5: List[String] = List("", a, b, aa, ab, bb, aab, abb, aabb)
  val st = ll.map({case(c,i) => c.toString*i}).mkString
                                                  //> st  : String = aabb
  val lst = for (i <- 0 to st.length) yield st.combinations(i).toList
                                                  //> lst  : scala.collection.immutable.IndexedSeq[List[String]] = Vector(List("")
                                                  //| , List(a, b), List(aa, ab, bb), List(aab, abb), List(aabb))
  val fl = lst.flatten                            //> fl  : scala.collection.immutable.IndexedSeq[String] = Vector("", a, b, aa, a
                                                  //| b, bb, aab, abb, aabb)
  val comb = fl.map(wordOccurrences)              //> comb  : scala.collection.immutable.IndexedSeq[forcomp.Anagrams.Occurrences] 
                                                  //| = Vector(List(), List((a,1)), List((b,1)), List((a,2)), List((a,1), (b,1)), 
                                                  //| List((b,2)), List((a,2), (b,1)), List((a,1), (b,2)), List((a,2), (b,2)))
  def myComb(occurrences: Occurrences): List[Occurrences] = {
  	val st = ll.map({case(c,i) => c.toString*i}).mkString
  	(for (i <- 0 to st.length) yield st.combinations(i).toList).flatten.map(wordOccurrences).toList
  }                                               //> myComb: (occurrences: forcomp.Anagrams.Occurrences)List[forcomp.Anagrams.Occ
                                                  //| urrences]
  
  val comb1 = myComb(ll)                          //> comb1  : List[forcomp.Anagrams.Occurrences] = List(List(), List((a,1)), Lis
                                                  //| t((b,1)), List((a,2)), List((a,1), (b,1)), List((b,2)), List((a,2), (b,1)),
                                                  //|  List((a,1), (b,2)), List((a,2), (b,2)))
  "aabb" diff "ab"                                //> res6: String = ab
  
  ("aabb".combinations(0) ++ "aabb".combinations(1) ++ "aabb".combinations(2) ++ "aabb".combinations(3) ++ "aabb".combinations(4)).toList
                                                  //> res7: List[String] = List("", a, b, aa, ab, bb, aab, abb, aabb)
}