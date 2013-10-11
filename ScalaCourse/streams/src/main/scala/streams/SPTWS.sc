package streams
   
object SPTWS {
  case class Pos(x: Int, y: Int) {
    /** The position obtained by changing the `x` coordiante by `d` */
    def dx(d: Int) = copy(x = x + d, y)

    /** The position obtained by changing the `y` coordiante by `d` */
    def dy(d: Int) = copy(x, y = y + d)
  }
 
  val level = Vector(Vector('S', 'T'), Vector('-', 'o'), Vector('o', 'o'))
                                                  //> level  : scala.collection.immutable.Vector[scala.collection.immutable.Vector
                                                  //| [Char]] = Vector(Vector(S, T), Vector(-, o), Vector(o, o))
  def terrainFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean =
    pos => pos.x < levelVector.length && pos.x >= 0 &&
           pos.y < levelVector(0).length & pos.y >= 0 &&
           levelVector(pos.x)(pos.y) != '-'       //> terrainFunction: (levelVector: Vector[Vector[Char]])streams.SPTWS.Pos => Boo
                                                  //| lean
  val ft = terrainFunction(level)                 //> ft  : streams.SPTWS.Pos => Boolean = <function1>
  
  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos = {
      val x = levelVector.indexWhere(v => v.contains(c))
      val y = levelVector(x).indexOf(c)
      Pos(x,y)
    }                                             //> findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.SPTWS.Pos
  
  ft(Pos(3,1))                                    //> res0: Boolean = false
  ft(Pos(1,2))                                    //> res1: Boolean = false
  ft(Pos(1,0))                                    //> res2: Boolean = false
  ft(Pos(1,1))                                    //> res3: Boolean = true
  ft(Pos(1,3))                                    //> res4: Boolean = false
  ft(Pos(0,1))                                    //> res5: Boolean = true
  
  findChar('S',level)                             //> res6: streams.SPTWS.Pos = Pos(0,0)
  findChar('T',level)                             //> res7: streams.SPTWS.Pos = Pos(0,1)
  findChar('-',level)                             //> res8: streams.SPTWS.Pos = Pos(1,0)
  findChar('o',level)                             //> res9: streams.SPTWS.Pos = Pos(1,1)
}