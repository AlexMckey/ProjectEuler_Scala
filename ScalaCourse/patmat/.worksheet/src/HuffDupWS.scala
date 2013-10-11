import patmat._
import patmat.Huffman._

object HuffDupWS {import scala.runtime.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(146); 
  val ctbl = List(('a',List(1,1)),('b',List(0,0,0)),('c',List(0,1)),('d',List(0,0,1)));System.out.println("""ctbl  : List[(Char, List[Int])] = """ + $show(ctbl ));$skip(33); 
  val btsa = codeBits(ctbl)('a');System.out.println("""btsa  : List[patmat.Huffman.Bit] = """ + $show(btsa ));$skip(33); 
  val btsb = codeBits(ctbl)('b');System.out.println("""btsb  : List[patmat.Huffman.Bit] = """ + $show(btsb ));$skip(33); 
  val btsc = codeBits(ctbl)('c');System.out.println("""btsc  : List[patmat.Huffman.Bit] = """ + $show(btsc ));$skip(33); 
  val btsd = codeBits(ctbl)('d');System.out.println("""btsd  : List[patmat.Huffman.Bit] = """ + $show(btsd ));$skip(61); 
  
  val lc = string2Chars("ababecdabababeeeabcdeedabababa");System.out.println("""lc  : List[Char] = """ + $show(lc ));$skip(30); 
  val ct = createCodeTree(lc);System.out.println("""ct  : patmat.Huffman.CodeTree = """ + $show(ct ));$skip(26); 
  
  val c1 = convert(ct);System.out.println("""c1  : patmat.Huffman.CodeTable = """ + $show(c1 ));$skip(30); 
  val bsa = codeBits(c1)('a');System.out.println("""bsa  : List[patmat.Huffman.Bit] = """ + $show(bsa ));$skip(30); 
  val bsb = codeBits(c1)('b');System.out.println("""bsb  : List[patmat.Huffman.Bit] = """ + $show(bsb ));$skip(30); 
  val bsc = codeBits(c1)('c');System.out.println("""bsc  : List[patmat.Huffman.Bit] = """ + $show(bsc ));$skip(30); 
  val bsd = codeBits(c1)('d');System.out.println("""bsd  : List[patmat.Huffman.Bit] = """ + $show(bsd ));$skip(30); 
  val bse = codeBits(c1)('e');System.out.println("""bse  : List[patmat.Huffman.Bit] = """ + $show(bse ))}
}