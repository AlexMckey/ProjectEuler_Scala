object tmp {
	val ns = (1 to 10)                        //> ns  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5, 6, 7,
                                                  //|  8, 9, 10)
  def sqr(x: Int) = x*x                           //> sqr: (x: Int)Int
	val sqs = ns.map(sqr).sum                 //> sqs  : Int = 385
	val ssq = sqr(ns.sum)                     //> ssq  : Int = 3025
	ssq-sqs                                   //> res0: Int = 2640
}