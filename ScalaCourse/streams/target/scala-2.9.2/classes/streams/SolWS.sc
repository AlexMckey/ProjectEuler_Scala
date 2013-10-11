package streams

object SolWS {
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
          levelVector(pos.x)(pos.y) != '-'        //> terrainFunction: (levelVector: Vector[Vector[Char]])streams.SolWS.Pos => Boo
                                                  //| lean
  val terrain = terrainFunction(level1)           //> terrain  : streams.SolWS.Pos => Boolean = <function1>
  
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
    val x = levelVector.indexWhere(v => v.contains(c))
    val y = levelVector(x).indexOf(c)
    Pos(x,y)
  }                                               //> findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.SolWS.Pos
  
  def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] =
    b.legalNeighbors.map({case (bl, m) => (bl, m :: history)}).toStream
                                                  //> neighborsWithHistory: (b: streams.SolWS.Block, history: List[streams.SolWS.
                                                  //| Move])Stream[(streams.SolWS.Block, List[streams.SolWS.Move])]
  def newNeighborsOnly(neighbors: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] =
    neighbors.filter({case (bl, m) => !explored.contains(bl)})
                                                  //> newNeighborsOnly: (neighbors: Stream[(streams.SolWS.Block, List[streams.Sol
                                                  //| WS.Move])], explored: Set[streams.SolWS.Block])Stream[(streams.SolWS.Block,
                                                  //|  List[streams.SolWS.Move])]
  lazy val goal: Pos = findChar('T', level1)      //> goal  : streams.SolWS.Pos = <lazy>
  
  val startPos = findChar('S',level1)             //> startPos  : streams.SolWS.Pos = Pos(1,1)
  val startBlock = Block(startPos,startPos)       //> startBlock  : streams.SolWS.Block = Block(Pos(1,1),Pos(1,1))
  val endBlock = goal                             //> endBlock  : streams.SolWS.Pos = Pos(4,7)
  
  val n = neighborsWithHistory(startBlock,List()) //> n  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = Stream((Bloc
                                                  //| k(Pos(1,2),Pos(1,3)),List(Right)), ?)
  val nn = newNeighborsOnly(n,Set())              //> nn  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = Stream((Blo
                                                  //| ck(Pos(1,2),Pos(1,3)),List(Right)), ?)
                                                  
  n.toList.take(2)                                //> res0: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = List((Block(P
                                                  //| os(1,2),Pos(1,3)),List(Right)), (Block(Pos(2,1),Pos(3,1)),List(Down)))
  nn.toList                                       //> res1: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = List((Block(P
                                                  //| os(1,2),Pos(1,3)),List(Right)), (Block(Pos(2,1),Pos(3,1)),List(Down)))
  def done(b: Block): Boolean = b.isStanding && b.b1 == goal
                                                  //> done: (b: streams.SolWS.Block)Boolean
  def from(initial: Stream[(Block, List[Move])],
           explored: Set[Block]): Stream[(Block, List[Move])] =
   if (initial.isEmpty) Stream.empty
      else {
        val more = for {
          pth <- initial.map({case (bl, lm) => neighborsWithHistory(bl, lm)})
          nxt <- newNeighborsOnly(pth,explored)
          if !(explored.exists(done))
        } yield nxt
        initial #::: from(more, explored ++ more.map({case (bl,_) => bl}).toSet)
      }                                           //> from: (initial: Stream[(streams.SolWS.Block, List[streams.SolWS.Move])], ex
                                                  //| plored: Set[streams.SolWS.Block])Stream[(streams.SolWS.Block, List[streams.
                                                  //| SolWS.Move])]
  lazy val pathsFromStart: Stream[(Block, List[Move])] =
    from(Stream((startBlock,List())),Set())       //> pathsFromStart  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] =
                                                  //|  <lazy>
  val x = Stream((startBlock,List()))             //> x  : scala.collection.immutable.Stream[(streams.SolWS.Block, List[Nothing])
                                                  //| ] = Stream((Block(Pos(1,1),Pos(1,1)),List()), ?)
  val xx = newNeighborsOnly(x,Set())              //> xx  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = Stream((Blo
                                                  //| ck(Pos(1,1),Pos(1,1)),List()), ?)
  x.toList                                        //> res2: List[(streams.SolWS.Block, List[Nothing])] = List((Block(Pos(1,1),Pos
                                                  //| (1,1)),List()))
  xx.toList                                       //> res3: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = List((Block(P
                                                  //| os(1,1),Pos(1,1)),List()))
  
  val exp1 = Set(startBlock)                      //> exp1  : scala.collection.immutable.Set[streams.SolWS.Block] = Set(Block(Pos
                                                  //| (1,1),Pos(1,1)))
  val pth1 = x.head                               //> pth1  : (streams.SolWS.Block, List[Nothing]) = (Block(Pos(1,1),Pos(1,1)),Li
                                                  //| st())
  val nxt1 = newNeighborsOnly(neighborsWithHistory(pth1._1,pth1._2),exp1)
                                                  //> nxt1  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = Stream((B
                                                  //| lock(Pos(1,2),Pos(1,3)),List(Right)), ?)
  nxt1.toList                                     //> res4: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = List((Block(P
                                                  //| os(1,2),Pos(1,3)),List(Right)), (Block(Pos(2,1),Pos(3,1)),List(Down)))
  val exp2 = exp1 ++ nxt1.map({case (bl,_) => bl}).toSet
                                                  //> exp2  : scala.collection.immutable.Set[streams.SolWS.Block] = Set(Block(Pos
                                                  //| (1,1),Pos(1,1)), Block(Pos(1,2),Pos(1,3)), Block(Pos(2,1),Pos(3,1)))
  
  val pth2_1 = nxt1.head                          //> pth2_1  : (streams.SolWS.Block, List[streams.SolWS.Move]) = (Block(Pos(1,2)
                                                  //| ,Pos(1,3)),List(Right))
  val pth2_2 = nxt1.tail.head                     //> pth2_2  : (streams.SolWS.Block, List[streams.SolWS.Move]) = (Block(Pos(2,1)
                                                  //| ,Pos(3,1)),List(Down))
  val nxt2_1 = newNeighborsOnly(neighborsWithHistory(pth2_1._1,pth2_1._2),exp2)
                                                  //> nxt2_1  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = Stream(
                                                  //| (Block(Pos(1,4),Pos(1,4)),List(Right, Right)), ?)
  val nxt2_2 = newNeighborsOnly(neighborsWithHistory(pth2_2._1,pth2_2._2),exp2)
                                                  //> nxt2_2  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = Stream(
                                                  //| (Block(Pos(2,2),Pos(3,2)),List(Right, Down)), ?)
  nxt2_1.toList                                   //> res5: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = List((Block(P
                                                  //| os(1,4),Pos(1,4)),List(Right, Right)), (Block(Pos(2,2),Pos(2,3)),List(Down,
                                                  //|  Right)))
  nxt2_2.toList                                   //> res6: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = List((Block(P
                                                  //| os(2,2),Pos(3,2)),List(Right, Down)))
  pathsFromStart.takeRight(10).toList             //> res7: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = List((Block(P
                                                  //| os(0,0),Pos(0,0)),List(Left, Up, Left, Up, Right, Right, Down)), (Block(Pos
                                                  //| (2,3),Pos(2,3)),List(Right, Down, Left, Up, Right, Right, Down)), (Block(Po
                                                  //| s(3,1),Pos(3,2)),List(Down, Down, Left, Up, Right, Right, Down)), (Block(Po
                                                  //| s(2,3),Pos(2,3)),List(Left, Down, Right, Up, Right, Right, Down)), (Block(P
                                                  //| os(2,6),Pos(2,6)),List(Right, Down, Right, Up, Right, Right, Down)), (Block
                                                  //| (Pos(3,4),Pos(3,5)),List(Down, Down, Right, Up, Right, Right, Down)), (Bloc
                                                  //| k(Pos(2,6),Pos(2,7)),List(Right, Right, Down, Right, Up, Right, Down)), (Bl
                                                  //| ock(Pos(3,5),Pos(4,5)),List(Down, Right, Down, Right, Up, Right, Down)), (B
                                                  //| lock(Pos(3,2),Pos(3,2)),List(Left, Down, Down, Right, Up, Right, Down)), (B
                                                  //| lock(Pos(3,5),Pos(3,5)),List(Right, Down, Down, Right, Up, Right, Down)))
}