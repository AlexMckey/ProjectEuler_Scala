package streams

object GDWS {
  case class Pos(x: Int, y: Int) {
    def dx(d: Int) = copy(x = x + d, y)
    def dy(d: Int) = copy(x, y = y + d)
  }
  
  sealed abstract class Move
  case object Left  extends Move
  case object Right extends Move
  case object Up    extends Move
  case object Down  extends Move
  
  type Terrain = Pos => Boolean
  val level1str =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin                //> level1str  : String = ooo-------
                                                  //| oSoooo----
                                                  //| ooooooooo-
                                                  //| -ooooooooo
                                                  //| -----ooToo
                                                  //| ------ooo-
  val level1 = Vector(level1str.split("\r?\n").map(str => Vector(str: _*)): _*)
                                                  //> level1  : scala.collection.immutable.Vector[scala.collection.immutable.Vecto
                                                  //| r[Char]] = Vector(Vector(o, o, o, -, -, -, -, -, -, -), Vector(o, S, o, o, o
                                                  //| , o, -, -, -, -), Vector(o, o, o, o, o, o, o, o, o, -), Vector(-, o, o, o, o
                                                  //| , o, o, o, o, o), Vector(-, -, -, -, -, o, o, T, o, o), Vector(-, -, -, -, -
                                                  //| , -, o, o, o, -))
  def terrainFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean =
   pos => pos.x < levelVector.length && pos.x >= 0 &&
          pos.y < levelVector(0).length & pos.y >= 0 &&
          levelVector(pos.x)(pos.y) != '-'        //> terrainFunction: (levelVector: Vector[Vector[Char]])streams.GDWS.Pos => Bool
                                                  //| ean
  val terrain = terrainFunction(level1)           //> terrain  : streams.GDWS.Pos => Boolean = <function1>
  
  case class Block(b1: Pos, b2: Pos) {
    require(b1.x <= b2.x && b1.y <= b2.y, "Invalid block position: b1=" + b1 + ", b2=" + b2)
    def dx(d1: Int, d2: Int) = Block(b1.dx(d1), b2.dx(d2))
    def dy(d1: Int, d2: Int) = Block(b1.dy(d1), b2.dy(d2))
    def left = if (isStanding)         dy(-2, -1)
               else if (b1.x == b2.x)  dy(-1, -2)
               else                    dy(-1, -1)
    def right = if (isStanding)        dy(1, 2)
                else if (b1.x == b2.x) dy(2, 1)
                else                   dy(1, 1)
    def up = if (isStanding)           dx(-2, -1)
             else if (b1.x == b2.x)    dx(-1, -1)
             else                      dx(-1, -2)
    def down = if (isStanding)         dx(1, 2)
               else if (b1.x == b2.x)  dx(1, 1)
               else                    dx(2, 1)
    def neighbors: List[(Block, Move)] =
      (left, Left) :: (right, Right) :: (up, Up) :: (down, Down) :: Nil
    def legalNeighbors: List[(Block, Move)] = neighbors.filter(p => p._1.isLegal)
     def isStanding: Boolean = b1 == b2
    def isLegal: Boolean = terrain(b1) && terrain(b2)
  }
  
  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos = {
    val y = levelVector.indexWhere(v => v.contains(c))
    val x = levelVector(y).indexOf(c)
    //Pos(x,y)
    Pos(y,x)
  }                                               //> findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.GDWS.Pos
  
  //------
  //--ST--
  //--oo--
  //--oo--
  //------
  val level0 = Vector(Vector('S', 'T'), Vector('o', 'o'), Vector('o', 'o'))
                                                  //> level0  : scala.collection.immutable.Vector[scala.collection.immutable.Vect
                                                  //| or[Char]] = Vector(Vector(S, T), Vector(o, o), Vector(o, o))
  val ft0 = terrainFunction(level0)               //> ft0  : streams.GDWS.Pos => Boolean = <function1>
  
    
  val spl0 = findChar('S',level0)                 //> spl0  : streams.GDWS.Pos = Pos(0,0)
  val sbl0 = Block(spl0,spl0)                     //> sbl0  : streams.GDWS.Block = Block(Pos(0,0),Pos(0,0))
  val sbnl0 = sbl0.neighbors                      //> sbnl0  : List[(streams.GDWS.Block, streams.GDWS.Move)] = List((Block(Pos(0,
                                                  //| -2),Pos(0,-1)),Left), (Block(Pos(0,1),Pos(0,2)),Right), (Block(Pos(-2,0),Po
                                                  //| s(-1,0)),Up), (Block(Pos(1,0),Pos(2,0)),Down))
  def isLegal0(bl: Block): Boolean = ft0(bl.b1) && ft0(bl.b2)
                                                  //> isLegal0: (bl: streams.GDWS.Block)Boolean
  val sbnbl0 = sbnl0.map(p => p._1)               //> sbnbl0  : List[streams.GDWS.Block] = List(Block(Pos(0,-2),Pos(0,-1)), Block
                                                  //| (Pos(0,1),Pos(0,2)), Block(Pos(-2,0),Pos(-1,0)), Block(Pos(1,0),Pos(2,0)))
                                                  //| 
  val sbnpterl0 = sbnbl0.map(x => List(ft0(x.b1),ft0(x.b2)))
                                                  //> sbnpterl0  : List[List[Boolean]] = List(List(false, false), List(true, fals
                                                  //| e), List(false, false), List(true, true))
  val sblnl0 = sbnbl0.map(x => isLegal0(x))       //> sblnl0  : List[Boolean] = List(false, false, false, true)
  
  val ft1 = terrainFunction(level1)               //> ft1  : streams.GDWS.Pos => Boolean = <function1>
  val spl1 = findChar('S',level1)                 //> spl1  : streams.GDWS.Pos = Pos(1,1)
  val sbl1 = Block(spl1,spl1)                     //> sbl1  : streams.GDWS.Block = Block(Pos(1,1),Pos(1,1))
  val sbnl1 = sbl1.neighbors                      //> sbnl1  : List[(streams.GDWS.Block, streams.GDWS.Move)] = List((Block(Pos(1,
                                                  //| -1),Pos(1,0)),Left), (Block(Pos(1,2),Pos(1,3)),Right), (Block(Pos(-1,1),Pos
                                                  //| (0,1)),Up), (Block(Pos(2,1),Pos(3,1)),Down))
  val sbnbl1 = sbnl1.map(p => p._1)               //> sbnbl1  : List[streams.GDWS.Block] = List(Block(Pos(1,-1),Pos(1,0)), Block(
                                                  //| Pos(1,2),Pos(1,3)), Block(Pos(-1,1),Pos(0,1)), Block(Pos(2,1),Pos(3,1)))
  val sbnpterl1 = sbnbl1.map(x => List(ft1(x.b1),ft1(x.b2)))
                                                  //> sbnpterl1  : List[List[Boolean]] = List(List(false, true), List(true, true)
                                                  //| , List(false, true), List(true, true))
  def isLegal1(bl: Block): Boolean = ft1(bl.b1) && ft1(bl.b2)
                                                  //> isLegal1: (bl: streams.GDWS.Block)Boolean
  val sblnl1 = sbnbl1.map(x => isLegal1(x))       //> sblnl1  : List[Boolean] = List(false, true, false, true)
  val ln = sbl1.legalNeighbors                    //> ln  : List[(streams.GDWS.Block, streams.GDWS.Move)] = List((Block(Pos(1,2),
                                                  //| Pos(1,3)),Right), (Block(Pos(2,1),Pos(3,1)),Down))
  val hist = List(Left,Up)                        //> hist  : List[Product with Serializable with streams.GDWS.Move] = List(Left,
                                                  //|  Up)
  val nh = ln.map({case (bl,m) => (bl, m :: hist)})
                                                  //> nh  : List[(streams.GDWS.Block, List[streams.GDWS.Move])] = List((Block(Pos
                                                  //| (1,2),Pos(1,3)),List(Right, Left, Up)), (Block(Pos(2,1),Pos(3,1)),List(Down
                                                  //| , Left, Up)))
}