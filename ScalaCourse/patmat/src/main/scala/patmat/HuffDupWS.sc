import patmat._
import patmat.Huffman._

object HuffDupWS {
  val ctbl = List(('a',List(1,1)),('b',List(0,0,0)),('c',List(0,1)),('d',List(0,0,1)))
                                                  //> ctbl  : List[(Char, List[Int])] = List((a,List(1, 1)), (b,List(0, 0, 0)), (c
                                                  //| ,List(0, 1)), (d,List(0, 0, 1)))
  val btsa = codeBits(ctbl)('a')                  //> btsa  : List[patmat.Huffman.Bit] = List(1, 1)
  val btsb = codeBits(ctbl)('b')                  //> btsb  : List[patmat.Huffman.Bit] = List(0, 0, 0)
  val btsc = codeBits(ctbl)('c')                  //> btsc  : List[patmat.Huffman.Bit] = List(0, 1)
  val btsd = codeBits(ctbl)('d')                  //> btsd  : List[patmat.Huffman.Bit] = List(0, 0, 1)
  
  val lc = string2Chars("ababecdabababeeeabcdeedabababa")
                                                  //> lc  : List[Char] = List(a, b, a, b, e, c, d, a, b, a, b, a, b, e, e, e, a, b
                                                  //| , c, d, e, e, d, a, b, a, b, a, b, a)
  val ct = createCodeTree(lc)                     //> ct  : patmat.Huffman.CodeTree = Fork(Fork(Fork(Leaf(c,2),Leaf(d,3),List(c, d
                                                  //| ),5),Leaf(e,6),List(c, d, e),11),Fork(Leaf(b,9),Leaf(a,10),List(b, a),19),Li
                                                  //| st(c, d, e, b, a),30)
  
  val c1 = convert(ct)                            //> c1  : patmat.Huffman.CodeTable = List((a,List(1, 1)), (b,List(1, 0)), (e,Lis
                                                  //| t(0, 1)), (d,List(0, 0, 1)), (c,List(0, 0, 0)))
  val bsa = codeBits(c1)('a')                     //> bsa  : List[patmat.Huffman.Bit] = List(1, 1)
  val bsb = codeBits(c1)('b')                     //> bsb  : List[patmat.Huffman.Bit] = List(1, 0)
  val bsc = codeBits(c1)('c')                     //> bsc  : List[patmat.Huffman.Bit] = List(0, 0, 0)
  val bsd = codeBits(c1)('d')                     //> bsd  : List[patmat.Huffman.Bit] = List(0, 0, 1)
  val bse = codeBits(c1)('e')                     //> bse  : List[patmat.Huffman.Bit] = List(0, 1)
                                                  //| Output exceeds cutoff limit. 
}