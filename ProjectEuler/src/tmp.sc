object tmp {
  lazy val f: Stream[Int] = Stream.cons(1, Stream.cons(2, f.zip(f.tail).map(p => p._1+p._2)))
                                                  //> f: => Stream[Int]
  f.filter(_%2==0).takeWhile(_<=4000000).sum      //> res0: Int = 4613732
}