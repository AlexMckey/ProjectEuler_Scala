package forcomp
import forcomp._
import forcomp.Anagrams._

object AnagWS {
  val l1 = List(('i', 1))                         //> l1  : List[(Char, Int)] = List((i,1))
  val l1_ = List(('I', 1))                        //> l1_  : List[(Char, Int)] = List((I,1))
  val l2 = List(('b', 1),('y',1))                 //> l2  : List[(Char, Int)] = List((b,1), (y,1))
  val l3 = List(('e', 1),('n',1),('o',1))         //> l3  : List[(Char, Int)] = List((e,1), (n,1), (o,1))
  dictionaryByOccurrences(l1)                     //> res0: List[forcomp.Anagrams.Word] = List()
  dictionaryByOccurrences(l1_)                    //> res1: List[forcomp.Anagrams.Word] = List()
  dictionaryByOccurrences(l2)                     //> res2: List[forcomp.Anagrams.Word] = List(by)
  dictionaryByOccurrences(l3)                     //> res3: List[forcomp.Anagrams.Word] = List(one)
  val l = List("Yes", "man")                      //> l  : List[java.lang.String] = List(Yes, man)
  val oc = sentenceOccurrences(l)                 //> oc  : forcomp.Anagrams.Occurrences = List((a,1), (e,1), (m,1), (n,1), (s,1),
                                                  //|  (y,1))
  val c = combinations(oc)                        //> c  : List[forcomp.Anagrams.Occurrences] = List(List(), List((a,1)), List((e,
                                                  //| 1)), List((m,1)), List((n,1)), List((s,1)), List((y,1)), List((a,1), (e,1)),
                                                  //|  List((a,1), (m,1)), List((a,1), (n,1)), List((a,1), (s,1)), List((a,1), (y,
                                                  //| 1)), List((e,1), (m,1)), List((e,1), (n,1)), List((e,1), (s,1)), List((e,1),
                                                  //|  (y,1)), List((m,1), (n,1)), List((m,1), (s,1)), List((m,1), (y,1)), List((n
                                                  //| ,1), (s,1)), List((n,1), (y,1)), List((s,1), (y,1)), List((a,1), (e,1), (m,1
                                                  //| )), List((a,1), (e,1), (n,1)), List((a,1), (e,1), (s,1)), List((a,1), (e,1),
                                                  //|  (y,1)), List((a,1), (m,1), (n,1)), List((a,1), (m,1), (s,1)), List((a,1), (
                                                  //| m,1), (y,1)), List((a,1), (n,1), (s,1)), List((a,1), (n,1), (y,1)), List((a,
                                                  //| 1), (s,1), (y,1)), List((e,1), (m,1), (n,1)), List((e,1), (m,1), (s,1)), Lis
                                                  //| t((e,1), (m,1), (y,1)), List((e,1), (n,1), (s,1)), List((e,1), (n,1), (y,1))
                                                  //| , List((e,1), (s,1), (y,1)), List((m,1), (n,1), (s,1)), List((m,1), (n,1), (
                                                  //| y,1)), List((m,1), (s,1)
                                                  //| Output exceeds cutoff limit.
  
  (c map dictionaryByOccurrences).toSet.toList    //> res4: List[List[forcomp.Anagrams.Word]] = List(List(man), List(yes), List(sa
                                                  //| ne, Sean), List(men), List(aye, yea), List(amen, mane, mean, name), List(en)
                                                  //| , List(any, nay), List(Sam), List(seamy), List(many), List(sea), List(), Lis
                                                  //| t(manes, means, names), List(as), List(Mae), List(mens), List(my), List(ayes
                                                  //| , easy, yeas), List(an), List(say), List(am), List(em, me), List(Mans), List
                                                  //| (Ames, same, seam), List(Amy, May), List(San))
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
  }                                               //> mySentenceAnagrams: (sentence: forcomp.Anagrams.Sentence)List[forcomp.Anagra
                                                  //| ms.Sentence]
  mySentenceAnagrams(List("Yes", "man"))          //> res5: List[forcomp.Anagrams.Sentence] = List(List(as, en, my), List(as, my, 
                                                  //| en), List(en, as, my), List(en, my, as), List(my, as, en), List(my, en, as),
                                                  //|  List(my, sane), List(my, Sean), List(man, yes), List(say, men), List(men, s
                                                  //| ay), List(yes, man), List(sane, my), List(Sean, my))
  val x = sentenceOccurrences(List("Yes", "man")) //> x  : forcomp.Anagrams.Occurrences = List((a,1), (e,1), (m,1), (n,1), (s,1),
                                                  //|  (y,1))
  val comb = combinations(x)                      //> comb  : List[forcomp.Anagrams.Occurrences] = List(List(), List((a,1)), List
                                                  //| ((e,1)), List((m,1)), List((n,1)), List((s,1)), List((y,1)), List((a,1), (e
                                                  //| ,1)), List((a,1), (m,1)), List((a,1), (n,1)), List((a,1), (s,1)), List((a,1
                                                  //| ), (y,1)), List((e,1), (m,1)), List((e,1), (n,1)), List((e,1), (s,1)), List
                                                  //| ((e,1), (y,1)), List((m,1), (n,1)), List((m,1), (s,1)), List((m,1), (y,1)),
                                                  //|  List((n,1), (s,1)), List((n,1), (y,1)), List((s,1), (y,1)), List((a,1), (e
                                                  //| ,1), (m,1)), List((a,1), (e,1), (n,1)), List((a,1), (e,1), (s,1)), List((a,
                                                  //| 1), (e,1), (y,1)), List((a,1), (m,1), (n,1)), List((a,1), (m,1), (s,1)), Li
                                                  //| st((a,1), (m,1), (y,1)), List((a,1), (n,1), (s,1)), List((a,1), (n,1), (y,1
                                                  //| )), List((a,1), (s,1), (y,1)), List((e,1), (m,1), (n,1)), List((e,1), (m,1)
                                                  //| , (s,1)), List((e,1), (m,1), (y,1)), List((e,1), (n,1), (s,1)), List((e,1),
                                                  //|  (n,1), (y,1)), List((e,1), (s,1), (y,1)), List((m,1), (n,1), (s,1)), List(
                                                  //| (m,1), (n,1), (y,1)), L
                                                  //| Output exceeds cutoff limit.
  val z = List(('e',1), ('m',1))                  //> z  : List[(Char, Int)] = List((e,1), (m,1))
  val y = dictionaryByOccurrences(z)              //> y  : List[forcomp.Anagrams.Word] = List(em, me)
  val sr = subtract(x, z)                         //> sr  : forcomp.Anagrams.Occurrences = List((a,1), (n,1), (s,1), (y,1))
}