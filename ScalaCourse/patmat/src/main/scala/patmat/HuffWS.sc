import patmat._
import patmat.Huffman._
object HuffWS {
  val l = List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd')
                                                  //> l  : List[Char] = List(h, e, l, l, o, ,,  , w, o, r, l, d)
  val pl = l.groupBy(c => c).map(p => (p._1, p._2.length)).toList
                                                  //> pl  : List[(Char, Int)] = List((e,1), ( ,1), (,,1), (l,3), (h,1), (r,1), (w,
                                                  //| 1), (o,2), (d,1))
  val tl = times(l)                               //> tl  : List[(Char, Int)] = List((e,1), ( ,1), (,,1), (l,3), (h,1), (r,1), (w,
                                                  //| 1), (o,2), (d,1))
  tl.sortBy(p => p._2).map(p => new Leaf(p._1, p._2))
                                                  //> res0: List[patmat.Huffman.Leaf] = List(Leaf(e,1), Leaf( ,1), Leaf(,,1), Leaf
                                                  //| (h,1), Leaf(r,1), Leaf(w,1), Leaf(d,1), Leaf(o,2), Leaf(l,3))
                                                  
  val decodedSecretL = decode(frenchCode, secret) //> decodedSecretL  : List[Char] = List(h, u, f, f, m, a, n, e, s, t, c, o, o, l
                                                  //| )
                                                  
  val i = Vector(1,2,3,4,5)                       //> i  : scala.collection.immutable.Vector[Int] = Vector(1, 2, 3, 4, 5)
                                                  
  val l5 = createCodeTree(string2Chars("texxtxx"))//> l5  : patmat.Huffman.CodeTree = Fork(Fork(Leaf(e,1),Leaf(t,2),List(e, t),3),
                                                  //| Leaf(x,4),List(e, t, x),7)
  val l6 = createCodeTree(string2Chars("Hello, world"))
                                                  //> l6  : patmat.Huffman.CodeTree = Fork(Fork(Leaf(o,2),Fork(Leaf(d,1),Fork(Leaf
                                                  //| (r,1),Leaf(w,1),List(r, w),2),List(d, r, w),3),List(o, d, r, w),5),Fork(Leaf
                                                  //| (l,3),Fork(Fork(Leaf(,,1),Leaf(H,1),List(,, H),2),Fork(Leaf(e,1),Leaf( ,1),L
                                                  //| ist(e,  ),2),List(,, H, e,  ),4),List(l, ,, H, e,  ),7),List(o, d, r, w, l, 
                                                  //| ,, H, e,  ),12)
  
  val leaflist1 = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
                                                  //> leaflist1  : List[patmat.Huffman.Leaf] = List(Leaf(e,1), Leaf(t,2), Leaf(x,4
                                                  //| ))
  val leaflist2 = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4))
                                                  //> leaflist2  : List[patmat.Huffman.Leaf] = List(Leaf(e,2), Leaf(t,3), Leaf(x,4
                                                  //| ))
  val l1 = combine(leaflist1)                     //> l1  : List[patmat.Huffman.CodeTree] = List(Fork(Leaf(e,1),Leaf(t,2),List(e, 
                                                  //| t),3), Leaf(x,4))
  val l2 = combine(leaflist2)                     //> l2  : List[patmat.Huffman.CodeTree] = List(Leaf(x,4), Fork(Leaf(e,2),Leaf(t,
                                                  //| 3),List(e, t),5))
  val l3 = until(singleton,combine)(leaflist1)    //> l3  : patmat.Huffman.CodeTree = Fork(Fork(Leaf(e,1),Leaf(t,2),List(e, t),3),
                                                  //| Leaf(x,4),List(e, t, x),7)
  val l4 = until(singleton,combine)(leaflist2)    //> l4  : patmat.Huffman.CodeTree = Fork(Leaf(x,4),Fork(Leaf(e,2),Leaf(t,3),List
                                                  //| (e, t),5),List(x, e, t),9)
}