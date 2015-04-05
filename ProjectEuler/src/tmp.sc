object tmp {
	def gcd(a: Long, b: Long): Long = if(b == 0) a else gcd(b, a % b)
                                                  //> gcd: (a: Long, b: Long)Long
	def lcm(x: Long, y: Long) = x*y/gcd(x,y)  //> lcm: (x: Long, y: Long)Long
	val M = 2L to 20L                         //> M  : scala.collection.immutable.NumericRange.Inclusive[Long] = NumericRange(
                                                  //| 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
  M.foldRight(1L)(lcm)                            //> res0: Long = 232792560
}