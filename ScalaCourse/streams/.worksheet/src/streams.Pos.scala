package streams

case class Pos(x: Int, y: Int) {
    /** The position obtained by changing the `x` coordiante by `d` */
    def dx(d: Int) = copy(x = x + d, y)

    /** The position obtained by changing the `y` coordiante by `d` */
    def dy(d: Int) = copy(x, y = y + d)
  }
    
object SPTWS {
  
  val level = Vector(Vector('S', 'T'), Vector('-', 'o'), Vector('o', 'o'))
  def terrainFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean =
    pos => pos.y <= levelVector.length && pos.x <= levelVector(0).length && levelVector(pos.x)(pos.y) != '-'
  val ft = terrainFunction(level)
  
  ft(Pos(2,1))
  ft(Pos(1,2))
  ft(Pos(1,1))
  ft(Pos(1,0))
}