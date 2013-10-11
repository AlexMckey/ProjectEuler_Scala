package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  		val s1 = singletonSet(1)
	    val s2 = singletonSet(2)
	    val s3 = singletonSet(3)
	    val s4 = singletonSet(4)
	    val s5 = singletonSet(5)
	    val s6 = singletonSet(6)
	    val s7 = singletonSet(7)
	    val s8 = singletonSet(8)
	    val s12 = union(s1, s2)
	    val s34 = union(s3, s4)
	    val s56 = union(s5, s6)
	    val s78 = union(s7, s8)
	    val s1_4 = union(s12, s34)
	    val s5_8 = union(s56, s78)
	    val s1_8 = union(s1_4, s5_8)
	    val sodd = filter(s1_8, x => x % 2 == 0)
	    printSet(s1_8)
	    printSet(sodd)
	    printSet(map(s1_8, x => x % 2))
}