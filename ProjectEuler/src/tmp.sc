object tmp {
	val n = 600851475143L;                    //> n  : Long = 600851475143
	def factors(n: Long): List[Long] =
  (2 to math.sqrt(n).toInt).find(n % _ == 0)
    .map(i => i.toLong :: factors(n / i)).getOrElse(List(n))
                                                  //> factors: (n: Long)List[Long]
  factors(n)                                      //> res0: List[Long] = List(71, 839, 1471, 6857)
  factors(91)                                     //> res1: List[Long] = List(7, 13)
  factors(13195)                                  //> res2: List[Long] = List(5, 7, 13, 29)
}