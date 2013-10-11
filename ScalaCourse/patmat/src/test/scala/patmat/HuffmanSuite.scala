package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
      assert(weight(t2) === 9)
      val tt1 = Fork(Leaf('a',2), Leaf('b',3), Nil , 0)
      val tt2 = Fork(Fork(Leaf('a',2), Leaf('b',3), Nil, 0), Leaf('d',4), Nil, 0)
      assert(weight(tt1) === 5)
      assert(weight(tt2) === 9)
      val ttt1 = makeCodeTree(Leaf('a',2),Leaf('b',3))
      val ttt2 = makeCodeTree(makeCodeTree(Leaf('a',2), Leaf('b',3)), Leaf('d',4))
      assert(weight(ttt1) === 5)
      assert(weight(ttt2) === 9)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
      assert(chars(t1) === List('a','b'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("times(\"hello, world\")") {
    val l = string2Chars("hello, world")
    val tl = Huffman.times(l)
    val op = tl.find(c => c._1 == 'l')
    assert(op.get._2 === 3)
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("check singleton the List of CodeTree") {
    val t1 = Leaf('a',2) :: Nil
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9) :: Nil
    val t3 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5) :: Leaf('d',4) :: Nil
    assert(singleton(t1)," List of one simple element is singleton")
    assert(singleton(t2)," List of one  element is singleton")
    assert(!singleton(t3)," List of many elements is singleton")
  }
  
  test("check singleton of Nil") {
    val t1 = Nil
    assert(!singleton(t1)," List of many elements is singleton")
  }
  
  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("combine of Nil leaf list") {
    val leaflist = Nil
    assert(combine(leaflist) === Nil)
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
