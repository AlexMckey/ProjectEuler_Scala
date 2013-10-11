import objsets._

trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

object WSTweetSets1 {
  new TestSets {
  	val t = new EmptyTrending
  	println(t)
  	val m1 = set5.findMin
  	val t1 = t + m1
  	val s1 = set5.remove(m1)
  	println(m1)
  	println(t1)
  	println(s1)
  	val m2 = s1.findMin
  	val t2 = t1 + m2
  	val s2 = s1.remove(m2)
  	println(m2)
  	println(t2)
  	println(s2)
  	println(set5.ascendingByRetweet)
  }                                               //> EmptyTrending
                                                  //| c : c body [7]
                                                  //| NonEmptyTrending(7, EmptyTrending)
                                                  //| objsets.NonEmpty@7646bb9f
                                                  //| d : d body [9]
                                                  //| NonEmptyTrending(7, NonEmptyTrending(9, EmptyTrending))
                                                  //| objsets.NonEmpty@1dc80063
                                                  //| NonEmptyTrending(7, NonEmptyTrending(9, NonEmptyTrending(20, NonEmptyTrendin
                                                  //| g(20, EmptyTrending))))
                                                  //| res0: java.lang.Object with TestSets{val t: objsets.EmptyTrending; val m1: o
                                                  //| bjsets.Tweet; val t1: objsets.NonEmptyTrending; val s1: objsets.TweetSet; va
                                                  //| l m2: objsets.Tweet; val t2: objsets.Trending; val s2: objsets.TweetSet} = W
                                                  //| STweetSets1$$anonfun$main$1$$anon$1@7222c0a0
}