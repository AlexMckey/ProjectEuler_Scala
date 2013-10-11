import patmat._
import patmat.Huffman._
object HuffDopWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(116); 
  val lc = string2Chars("ababecdabababeeeabcdeedabababa");System.out.println("""lc  : List[Char] = """ + $show(lc ));$skip(21); 
  val tl = times(lc);System.out.println("""tl  : List[(Char, Int)] = """ + $show(tl ));$skip(36); 
  val otl = makeOrderedLeafList(tl);System.out.println("""otl  : List[patmat.Huffman.Leaf] = """ + $show(otl ));$skip(30); 
  val ct = createCodeTree(lc);System.out.println("""ct  : patmat.Huffman.CodeTree = """ + $show(ct ));$skip(52); 
  val lb: List[Bit] = List(1,1,1,0,0,0,0,0,0,1,0,1);System.out.println("""lb  : List[patmat.Huffman.Bit] = """ + $show(lb ));$skip(26); 
  val dlc = decode(ct,lb);System.out.println("""dlc  : List[Char] = """ + $show(dlc ));$skip(34); 
  val elbq = quickEncode(ct)(dlc);System.out.println("""elbq  : List[patmat.Huffman.Bit] = """ + $show(elbq ));$skip(254); 
  def findChar(tree: CodeTree, char: Char, acc: List[Bit] = Nil): List[Bit] = tree match {
    case Fork(l,r,_,_) => findChar(r, char, 1 :: acc) ::: findChar(l, char, 0 :: acc)
    case Leaf(c,_) if c == char => acc.reverse
    case Leaf(c,_) => Nil
  };System.out.println("""findChar: (tree: patmat.Huffman.CodeTree, char: Char, acc: List[patmat.Huffman.Bit])List[patmat.Huffman.Bit]""");$skip(33); 
  
  val bla = findChar(ct, 'a');System.out.println("""bla  : List[patmat.Huffman.Bit] = """ + $show(bla ));$skip(30); 
  val blb = findChar(ct, 'b');System.out.println("""blb  : List[patmat.Huffman.Bit] = """ + $show(blb ));$skip(30); 
  val blc = findChar(ct, 'c');System.out.println("""blc  : List[patmat.Huffman.Bit] = """ + $show(blc ));$skip(30); 
  val bld = findChar(ct, 'd');System.out.println("""bld  : List[patmat.Huffman.Bit] = """ + $show(bld ));$skip(30); 
  val ble = findChar(ct, 'e');System.out.println("""ble  : List[patmat.Huffman.Bit] = """ + $show(ble ));$skip(31); 
  
  val elb = encode(ct)(dlc);System.out.println("""elb  : List[patmat.Huffman.Bit] = """ + $show(elb ))}
  
}