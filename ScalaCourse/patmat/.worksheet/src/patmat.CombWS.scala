package patmat

import patmat._
import patmat.Huffman._

object CombWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); 
  
	val l = 1;System.out.println("""l  : Int = """ + $show(l ));$skip(156); 
	def mycombine(trees: List[CodeTree]): List[CodeTree] = trees match {
    case tr1 :: tr2 :: rest => makeCodeTree(tr1, tr2) :: rest
    case _ => trees
  };System.out.println("""mycombine: (trees: List[patmat.Huffman.CodeTree])List[patmat.Huffman.CodeTree]""");$skip(68); 
  
  val leaflist1 = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4));System.out.println("""leaflist1  : List[patmat.Huffman.Leaf] = """ + $show(leaflist1 ));$skip(35); 
  
  val l1 = mycombine(leaflist1);System.out.println("""l1  : List[patmat.Huffman.CodeTree] = """ + $show(l1 ))}
}