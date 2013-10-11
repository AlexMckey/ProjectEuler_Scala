package streams
import streams._

abstract class Level extends Solver with StringParserTerrain
  
  object Level0 extends Level {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(240); 
    val level =
      """------
        |--ST--
        |--oo--
        |--oo--
        |------""".stripMargin;System.out.println("""level  : String = """ + $show(level ))}
  }

object SPTWS {
  val ft = Level0.terrainFunction(Level0.level)
}