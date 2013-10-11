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
  
  type Terrain = Pos => Boolean;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(501); 
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
          levelVector(pos.x)(pos.y) != '-';System.out.println("""terrainFunction: (levelVector: Vector[Vector[Char]])streams.GDWS.Pos => Boolean""");$skip(40); 
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
  };System.out.println("""terrain  : streams.GDWS.Pos => Boolean = """ + $show(terrain ));$skip(1330); 
  
  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos = {
    val y = levelVector.indexWhere(v => v.contains(c))
    val x = levelVector(y).indexOf(c)
    //Pos(x,y)
    Pos(y,x)
  };System.out.println("""findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.GDWS.Pos""");$skip(134); 
  
  //------
  //--ST--
  //--oo--
  //--oo--
  //------
  val level0 = Vector(Vector('S', 'T'), Vector('o', 'o'), Vector('o', 'o'));System.out.println("""level0  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Char]] = """ + $show(level0 ));$skip(36); 
  val ft0 = terrainFunction(level0);System.out.println("""ft0  : streams.GDWS.Pos => Boolean = """ + $show(ft0 ));$skip(42); 
  
    
  val spl0 = findChar('S',level0);System.out.println("""spl0  : streams.GDWS.Pos = """ + $show(spl0 ));$skip(30); 
  val sbl0 = Block(spl0,spl0);System.out.println("""sbl0  : streams.GDWS.Block = """ + $show(sbl0 ));$skip(29); 
  val sbnl0 = sbl0.neighbors;System.out.println("""sbnl0  : List[(streams.GDWS.Block, streams.GDWS.Move)] = """ + $show(sbnl0 ));$skip(62); 
  def isLegal0(bl: Block): Boolean = ft0(bl.b1) && ft0(bl.b2);System.out.println("""isLegal0: (bl: streams.GDWS.Block)Boolean""");$skip(36); 
  val sbnbl0 = sbnl0.map(p => p._1);System.out.println("""sbnbl0  : List[streams.GDWS.Block] = """ + $show(sbnbl0 ));$skip(61); 
  val sbnpterl0 = sbnbl0.map(x => List(ft0(x.b1),ft0(x.b2)));System.out.println("""sbnpterl0  : List[List[Boolean]] = """ + $show(sbnpterl0 ));$skip(44); 
  val sblnl0 = sbnbl0.map(x => isLegal0(x));System.out.println("""sblnl0  : List[Boolean] = """ + $show(sblnl0 ));$skip(39); 
  
  val ft1 = terrainFunction(level1);System.out.println("""ft1  : streams.GDWS.Pos => Boolean = """ + $show(ft1 ));$skip(34); 
  val spl1 = findChar('S',level1);System.out.println("""spl1  : streams.GDWS.Pos = """ + $show(spl1 ));$skip(30); 
  val sbl1 = Block(spl1,spl1);System.out.println("""sbl1  : streams.GDWS.Block = """ + $show(sbl1 ));$skip(29); 
  val sbnl1 = sbl1.neighbors;System.out.println("""sbnl1  : List[(streams.GDWS.Block, streams.GDWS.Move)] = """ + $show(sbnl1 ));$skip(36); 
  val sbnbl1 = sbnl1.map(p => p._1);System.out.println("""sbnbl1  : List[streams.GDWS.Block] = """ + $show(sbnbl1 ));$skip(61); 
  val sbnpterl1 = sbnbl1.map(x => List(ft1(x.b1),ft1(x.b2)));System.out.println("""sbnpterl1  : List[List[Boolean]] = """ + $show(sbnpterl1 ));$skip(62); 
  def isLegal1(bl: Block): Boolean = ft1(bl.b1) && ft1(bl.b2);System.out.println("""isLegal1: (bl: streams.GDWS.Block)Boolean""");$skip(44); 
  val sblnl1 = sbnbl1.map(x => isLegal1(x));System.out.println("""sblnl1  : List[Boolean] = """ + $show(sblnl1 ));$skip(31); 
  val ln = sbl1.legalNeighbors;System.out.println("""ln  : List[(streams.GDWS.Block, streams.GDWS.Move)] = """ + $show(ln ));$skip(27); 
  val hist = List(Left,Up);System.out.println("""hist  : List[Product with Serializable with streams.GDWS.Move] = """ + $show(hist ));$skip(52); 
  val nh = ln.map({case (bl,m) => (bl, m :: hist)});System.out.println("""nh  : List[(streams.GDWS.Block, List[streams.GDWS.Move])] = """ + $show(nh ))}
}