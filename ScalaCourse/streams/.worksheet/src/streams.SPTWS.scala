package streams
   
object SPTWS {
  case class Pos(x: Int, y: Int) {
    /** The position obtained by changing the `x` coordiante by `d` */
    def dx(d: Int) = copy(x = x + d, y)

    /** The position obtained by changing the `y` coordiante by `d` */
    def dy(d: Int) = copy(x, y = y + d)
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(373); 
 
  val level = Vector(Vector('S', 'T'), Vector('-', 'o'), Vector('o', 'o'));System.out.println("""level  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Char]] = """ + $show(level ));$skip(231); 
  def terrainFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean =
    pos => pos.x < levelVector.length && pos.x >= 0 &&
           pos.y < levelVector(0).length & pos.y >= 0 &&
           levelVector(pos.x)(pos.y) != '-';System.out.println("""terrainFunction: (levelVector: Vector[Vector[Char]])streams.SPTWS.Pos => Boolean""");$skip(34); 
  val ft = terrainFunction(level);System.out.println("""ft  : streams.SPTWS.Pos => Boolean = """ + $show(ft ));$skip(189); 
  
  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos = {
      val x = levelVector.indexWhere(v => v.contains(c))
      val y = levelVector(x).indexOf(c)
      Pos(x,y)
    };System.out.println("""findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.SPTWS.Pos""");$skip(18); val res$0 = 
  
  ft(Pos(3,1));System.out.println("""res0: Boolean = """ + $show(res$0));$skip(15); val res$1 = 
  ft(Pos(1,2));System.out.println("""res1: Boolean = """ + $show(res$1));$skip(15); val res$2 = 
  ft(Pos(1,0));System.out.println("""res2: Boolean = """ + $show(res$2));$skip(15); val res$3 = 
  ft(Pos(1,1));System.out.println("""res3: Boolean = """ + $show(res$3));$skip(15); val res$4 = 
  ft(Pos(1,3));System.out.println("""res4: Boolean = """ + $show(res$4));$skip(15); val res$5 = 
  ft(Pos(0,1));System.out.println("""res5: Boolean = """ + $show(res$5));$skip(25); val res$6 = 
  
  findChar('S',level);System.out.println("""res6: streams.SPTWS.Pos = """ + $show(res$6));$skip(22); val res$7 = 
  findChar('T',level);System.out.println("""res7: streams.SPTWS.Pos = """ + $show(res$7));$skip(22); val res$8 = 
  findChar('-',level);System.out.println("""res8: streams.SPTWS.Pos = """ + $show(res$8));$skip(22); val res$9 = 
  findChar('o',level);System.out.println("""res9: streams.SPTWS.Pos = """ + $show(res$9))}
}