import patmat._
import patmat.Huffman._
object HuffDopWS {
  val lc = string2Chars("ababecdabababeeeabcdeedabababa")
  val ld = string2Chars("ababecdabababeeeabcdeedabababadddd")
                                                  //> lc  : List[Char] = List(a, b, a, b, e, c, d, a, b, a, b, a, b, e, e, e, a, b
                                                  //| , c, d, e, e, d, a, b, a, b, a, b, a)
  val tl = times(lc)                              //> tl  : List[(Char, Int)] = List((e,6), (a,10), (b,9), (c,2), (d,3))
  val tld = times(ld)
  val otl = makeOrderedLeafList(tl)               //> otl  : List[patmat.Huffman.Leaf] = List(Leaf(c,2), Leaf(d,3), Leaf(e,6), Lea
                                                  //| f(b,9), Leaf(a,10))
  val ct = createCodeTree(lc)                     //> ct  : patmat.Huffman.CodeTree = Fork(Fork(Fork(Leaf(c,2),Leaf(d,3),List(c, d
                                                  //| ),5),Leaf(e,6),List(c, d, e),11),Fork(Leaf(b,9),Leaf(a,10),List(b, a),19),Li
                                                  //| st(c, d, e, b, a),30)
  val lb: List[Bit] = List(1,1,1,0,0,0,0,0,0,1,0,1)
                                                  //> lb  : List[patmat.Huffman.Bit] = List(1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)
  val dlc = decode(ct,lb)                         //> dlc  : List[Char] = List(a, b, c, d, e)
  val elbq = quickEncode(ct)(dlc)                 //> elbq  : List[patmat.Huffman.Bit] = List(1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)
                                                  //| 
  def findChar(tree: CodeTree, char: Char, acc: List[Bit] = Nil): List[Bit] = tree match {
    case Fork(l,r,_,_) => findChar(r, char, 1 :: acc) ::: findChar(l, char, 0 :: acc)
    case Leaf(c,_) if c == char => acc.reverse
    case Leaf(c,_) => Nil
  }                                               //> findChar: (tree: patmat.Huffman.CodeTree, char: Char, acc: List[patmat.Huffm
                                                  //| an.Bit])List[patmat.Huffman.Bit]
  
  val bla = findChar(ct, 'a')                     //> bla  : List[patmat.Huffman.Bit] = List(1, 1)
  val blb = findChar(ct, 'b')                     //> blb  : List[patmat.Huffman.Bit] = List(1, 0)
  val blc = findChar(ct, 'c')                     //> blc  : List[patmat.Huffman.Bit] = List(0, 0, 0)
  val bld = findChar(ct, 'd')                     //> bld  : List[patmat.Huffman.Bit] = List(0, 0, 1)
  val ble = findChar(ct, 'e')                     //> ble  : List[patmat.Huffman.Bit] = List(0, 1)
  
  val elb = encode(ct)(dlc)                       //> elb  : List[patmat.Huffman.Bit] = List(1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)
  
}