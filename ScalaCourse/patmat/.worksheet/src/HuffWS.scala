import patmat._
import patmat.Huffman._
object HuffWS {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(130); 
  val l = List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd');System.out.println("""l  : List[Char] = """ + $show(l ));$skip(66); 
  val pl = l.groupBy(c => c).map(p => (p._1, p._2.length)).toList;System.out.println("""pl  : List[(Char, Int)] = """ + $show(pl ));$skip(20); 
  val tl = times(l);System.out.println("""tl  : List[(Char, Int)] = """ + $show(tl ));$skip(54); val res$0 = 
  tl.sortBy(p => p._2).map(p => new Leaf(p._1, p._2));System.out.println("""res0: List[patmat.Huffman.Leaf] = """ + $show(res$0));$skip(101); 
                                                  
  val decodedSecretL = decode(frenchCode, secret);System.out.println("""decodedSecretL  : List[Char] = """ + $show(decodedSecretL ));$skip(79); 
                                                  
  val i = Vector(1,2,3,4,5);System.out.println("""i  : scala.collection.immutable.Vector[Int] = """ + $show(i ));$skip(102); 
                                                  
  val l5 = createCodeTree(string2Chars("texxtxx"));System.out.println("""l5  : patmat.Huffman.CodeTree = """ + $show(l5 ));$skip(56); 
  val l6 = createCodeTree(string2Chars("Hello, world"));System.out.println("""l6  : patmat.Huffman.CodeTree = """ + $show(l6 ));$skip(68); 
  
  val leaflist1 = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4));System.out.println("""leaflist1  : List[patmat.Huffman.Leaf] = """ + $show(leaflist1 ));$skip(65); 
  val leaflist2 = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4));System.out.println("""leaflist2  : List[patmat.Huffman.Leaf] = """ + $show(leaflist2 ));$skip(30); 
  val l1 = combine(leaflist1);System.out.println("""l1  : List[patmat.Huffman.CodeTree] = """ + $show(l1 ));$skip(30); 
  val l2 = combine(leaflist2);System.out.println("""l2  : List[patmat.Huffman.CodeTree] = """ + $show(l2 ));$skip(47); 
  val l3 = until(singleton,combine)(leaflist1);System.out.println("""l3  : patmat.Huffman.CodeTree = """ + $show(l3 ));$skip(47); 
  val l4 = until(singleton,combine)(leaflist2);System.out.println("""l4  : patmat.Huffman.CodeTree = """ + $show(l4 ))}
}