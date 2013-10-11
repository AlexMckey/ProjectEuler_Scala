package patmat

import patmat._
import patmat.Huffman._

object CombWS {
  
	val l = 1                                 //> l  : Int = 1
	def mycombine(trees: List[CodeTree]): List[CodeTree] = trees match {
    case tr1 :: tr2 :: rest => makeCodeTree(tr1, tr2) :: rest
    case _ => trees
  }                                               //> mycombine: (trees: List[patmat.Huffman.CodeTree])List[patmat.Huffman.CodeTre
                                                  //| e]
  
  val leaflist1 = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
                                                  //> leaflist1  : List[patmat.Huffman.Leaf] = List(Leaf(e,1), Leaf(t,2), Leaf(x,4
                                                  //| ))
  
  val l1 = mycombine(leaflist1)                   //> l1  : List[patmat.Huffman.CodeTree] = List(Fork(Leaf(e,1),Leaf(t,2),List(e, 
                                                  //| t),3), Leaf(x,4))
}