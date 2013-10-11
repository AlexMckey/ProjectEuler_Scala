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
  
  type Terrain = Pos => Boolean;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(502); 
  val level1str =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin;System.out.println("""level1str  : String = """ + $show(level1str ));$skip(80); 
  val level1 = Vector(level1str.split("\r?\n").map(str => Vector(str: _*)): _*);System.out.println("""level1  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Char]] = """ + $show(level1 ));$skip(228); 
  def terrainFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean =
   pos => pos.x < levelVector.length && pos.x >= 0 &&
          pos.y < levelVector(0).length & pos.y >= 0 &&
          levelVector(pos.x)(pos.y) != '-';System.out.println("""terrainFunction: (levelVector: Vector[Vector[Char]])streams.SolWS.Pos => Boolean""");$skip(40); 
  val terrain = terrainFunction(level1)
  
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
  };System.out.println("""terrain  : streams.SolWS.Pos => Boolean = """ + $show(terrain ));$skip(1315); 
  
  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos = {
    val x = levelVector.indexWhere(v => v.contains(c))
    val y = levelVector(x).indexOf(c)
    Pos(x,y)
  };System.out.println("""findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.SolWS.Pos""");$skip(164); 
  
  def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] =
    b.legalNeighbors.map({case (bl, m) => (bl, m :: history)}).toStream;System.out.println("""neighborsWithHistory: (b: streams.SolWS.Block, history: List[streams.SolWS.Move])Stream[(streams.SolWS.Block, List[streams.SolWS.Move])]""");$skip(179); 
  def newNeighborsOnly(neighbors: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] =
    neighbors.filter({case (bl, m) => !explored.contains(bl)});System.out.println("""newNeighborsOnly: (neighbors: Stream[(streams.SolWS.Block, List[streams.SolWS.Move])], explored: Set[streams.SolWS.Block])Stream[(streams.SolWS.Block, List[streams.SolWS.Move])]""");$skip(45); 
  lazy val goal: Pos = findChar('T', level1);System.out.println("""goal  : streams.SolWS.Pos = <lazy>""");$skip(41); 
  
  val startPos = findChar('S',level1);System.out.println("""startPos  : streams.SolWS.Pos = """ + $show(startPos ));$skip(44); 
  val startBlock = Block(startPos,startPos);System.out.println("""startBlock  : streams.SolWS.Block = """ + $show(startBlock ));$skip(22); 
  val endBlock = goal;System.out.println("""endBlock  : streams.SolWS.Pos = """ + $show(endBlock ));$skip(53); 
  
  val n = neighborsWithHistory(startBlock,List());System.out.println("""n  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(n ));$skip(37); 
  val nn = newNeighborsOnly(n,Set());System.out.println("""nn  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(nn ));$skip(71); val res$0 = 
                                                  
  n.toList.take(2);System.out.println("""res0: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(res$0));$skip(12); val res$1 = 
  nn.toList;System.out.println("""res1: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(res$1));$skip(61); 
  def done(b: Block): Boolean = b.isStanding && b.b1 == goal;System.out.println("""done: (b: streams.SolWS.Block)Boolean""");$skip(461); 
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
      };System.out.println("""from: (initial: Stream[(streams.SolWS.Block, List[streams.SolWS.Move])], explored: Set[streams.SolWS.Block])Stream[(streams.SolWS.Block, List[streams.SolWS.Move])]""");$skip(101); 
  lazy val pathsFromStart: Stream[(Block, List[Move])] =
    from(Stream((startBlock,List())),Set());System.out.println("""pathsFromStart  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = <lazy>""");$skip(38); 
  val x = Stream((startBlock,List()));System.out.println("""x  : scala.collection.immutable.Stream[(streams.SolWS.Block, List[Nothing])] = """ + $show(x ));$skip(37); 
  val xx = newNeighborsOnly(x,Set());System.out.println("""xx  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(xx ));$skip(11); val res$2 = 
  x.toList;System.out.println("""res2: List[(streams.SolWS.Block, List[Nothing])] = """ + $show(res$2));$skip(12); val res$3 = 
  xx.toList;System.out.println("""res3: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(res$3));$skip(32); 
  
  val exp1 = Set(startBlock);System.out.println("""exp1  : scala.collection.immutable.Set[streams.SolWS.Block] = """ + $show(exp1 ));$skip(20); 
  val pth1 = x.head;System.out.println("""pth1  : (streams.SolWS.Block, List[Nothing]) = """ + $show(pth1 ));$skip(74); 
  val nxt1 = newNeighborsOnly(neighborsWithHistory(pth1._1,pth1._2),exp1);System.out.println("""nxt1  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(nxt1 ));$skip(14); val res$4 = 
  nxt1.toList;System.out.println("""res4: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(res$4));$skip(57); 
  val exp2 = exp1 ++ nxt1.map({case (bl,_) => bl}).toSet;System.out.println("""exp2  : scala.collection.immutable.Set[streams.SolWS.Block] = """ + $show(exp2 ));$skip(28); 
  
  val pth2_1 = nxt1.head;System.out.println("""pth2_1  : (streams.SolWS.Block, List[streams.SolWS.Move]) = """ + $show(pth2_1 ));$skip(30); 
  val pth2_2 = nxt1.tail.head;System.out.println("""pth2_2  : (streams.SolWS.Block, List[streams.SolWS.Move]) = """ + $show(pth2_2 ));$skip(80); 
  val nxt2_1 = newNeighborsOnly(neighborsWithHistory(pth2_1._1,pth2_1._2),exp2);System.out.println("""nxt2_1  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(nxt2_1 ));$skip(80); 
  val nxt2_2 = newNeighborsOnly(neighborsWithHistory(pth2_2._1,pth2_2._2),exp2);System.out.println("""nxt2_2  : Stream[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(nxt2_2 ));$skip(16); val res$5 = 
  nxt2_1.toList;System.out.println("""res5: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(res$5));$skip(16); val res$6 = 
  nxt2_2.toList;System.out.println("""res6: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(res$6));$skip(38); val res$7 = 
  pathsFromStart.takeRight(10).toList;System.out.println("""res7: List[(streams.SolWS.Block, List[streams.SolWS.Move])] = """ + $show(res$7))}
}