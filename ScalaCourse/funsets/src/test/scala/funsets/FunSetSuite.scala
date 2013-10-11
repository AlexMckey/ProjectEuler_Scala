package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.8/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  //ignore("singletonSet(1) contains 1") {
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton 1")
      assert(!contains(s1, 2), "Singleton 2")
      assert(!contains(s2, 1), "Singleton 3")
      assert(contains(s2, 2), "Singleton 4")
    }
  }

  //ignore("union contains all elements") {
  test("union contains all elements") {
    new TestSets {
	  val s = union(s1, s2)
	  assert(contains(s, 1), "Union 1")
	  assert(contains(s, 2), "Union 2")
	  assert(!contains(s, 3), "Union 3")
    }}
    
    test("intersect contains only both elements") {
	    val s1_1 = singletonSet(1)
	    val s1_2 = singletonSet(1)
	    val s2 = singletonSet(2)
	    val s3 = singletonSet(3)
	    val s11 = intersect(s1_1, s1_2)
	    val s12 = intersect(s1_1, s2)
	    val s13 = union(s1_1, s3)
	    val s123 = union(s13, s2)
	    val s23 = intersect(s123, s1_1)
	    assert(contains(s11, 1), "intersect 1")
	    assert(!contains(s11, 2), "intersect 2")
	    assert(!contains(s11, 3), "intersect 3")
	    assert(!contains(s11, 2), "intersect 4")
	    assert(!contains(s12, 1), "intersect 5")
	    assert(!contains(s12, 2), "intersect 6")
	    assert(contains(s13, 1), "intersect 7")
	    assert(contains(s13, 3), "intersect 8")
	    assert(!contains(s13, 2), "intersect 9")
	    assert(contains(s123, 1), "intersect 10")
	    assert(contains(s123, 3), "intersect 11")
	    assert(contains(s123, 2), "intersect 12")
	    assert(contains(s23, 1), "intersect 13")
	    assert(!contains(s23, 3), "intersect 14")
	    assert(!contains(s23, 2), "intersect 15")
    }
    
    test("diff for sets") {
	    val s1 = singletonSet(1)
	    val s1_ = singletonSet(1)
	    val s2 = singletonSet(2)
	    val s3 = singletonSet(3)
	    val s11 = diff(s1, s1_)
	    val s13 = union(s1, s3)
	    val s123 = union(s13, s2)
	    val s23 = diff(s123, s1)
	    assert(!contains(s11, 1), "diff 1")
	    assert(contains(s123, 1), "diff 2")
	    assert(contains(s123, 3), "diff 3")
	    assert(contains(s123, 2), "diff 4")
	    assert(contains(s23, 2), "diff 5")
	    assert(contains(s23, 3), "diff 6")
	    assert(!contains(s23, 1), "diff 7")
    }
    
    test("filter for sets") {
	    val s1 = singletonSet(1)
	    val s2 = singletonSet(2)
	    val s3 = singletonSet(3)
	    val s4 = singletonSet(4)
	    val s5 = singletonSet(5)
	    val s6 = singletonSet(6)
	    val s7 = singletonSet(7)
	    val s8 = singletonSet(8)
	    val s12 = union(s1, s2)
	    val s34 = union(s3, s4)
	    val s56 = union(s5, s6)
	    val s78 = union(s7, s8)
	    val s1_4 = union(s12, s34)
	    val s5_8 = union(s56, s78)
	    val s1_8 = union(s1_4, s5_8)
	    assert(contains(s1_8, 3), "filter 1")
	    assert(contains(s1_8, 4), "filter 2")
	    val sodd = filter(s1_8, x => x % 2 == 0)
	    assert(!contains(sodd, 3), "filter 3")
	    assert(contains(sodd, 4), "filter 4")
	    assert(!contains(sodd, 5), "filter 5")
	    assert(contains(sodd, 6), "filter 6")
	    assert(!contains(sodd, 9), "filter 7")
	    assert(!contains(sodd, 10), "filter 8")
    }
    
    test("forall tests") {
	    val s = union(x => x > 0, x => x == 0)
	    assert(contains(s, 3), "forall 1")
	    assert(contains(s, 1000), "forall 2")
	    assert(contains(s, 0), "forall 3")
	    assert(!contains(s, -1), "forall 4")
	    assert(!forall(s, x => x > 10), "forall 5")
	    assert(forall(s, x => x > -10), "forall 6")
    }
    
    test("Set(1,2,3,4) and x<5 tests") {
	    val s = union(singletonSet(1), x => x == 2 || x == 3 || x == 4)
	    assert(forall(s, x => x < 5), "Set(1,2,3,4) < 5")
    }
    
    test("Set(-1000,0) and x<1000 tests") {
	    val s = union(singletonSet(-1000), singletonSet(0))
	    assert(forall(s, x => x < 1000), "Set(-1000,0) < 1000")
    }
    
    test("even forall tests") {
    	val s1 = union(singletonSet(2),union(singletonSet(4),singletonSet(6)))
    	val s2 = singletonSet(3)
	    assert(forall(s1, x => x % 2 == 0)," even exists 1")
	    assert(!forall(s2, x => x % 2 == 0)," even exists 2")
    }
    
    test("exists tests") {
	    val s = union(x => x > 0, x => x == 0)
	    assert(contains(s, 3), "exists 1")
	    assert(contains(s, 1000), "exists 2")
	    assert(contains(s, 0), "exists 3")
	    assert(!contains(s, -1), "exists 4")
	    assert(exists(s, x => x == 10), "exists 5")
	    assert(exists(s, x => x == 0), "exists 6")
	    assert(!forall(s, x => x == -10), "exists 7")
    }
    
    test("Set(1,3,4,5,7,1000) and x=2 tests") {
	    val s = union(singletonSet(1), x => x == 3 || x == 4 || x == 5 || x == 7 || x == 1000)
	    assert(!exists(s, x => x == 2), "Set(1,3,4,5,7,1000) !=2 tests")
    }
    
    test("even exist tests") {
    	val s1 = union(singletonSet(2), x => x % 2 == 0)
    	assert(exists(s1, x => x % 2 == 0)," even exists 1")
	    assert(!exists(s1, x => x % 2 != 0)," even exists 2")
	    assert(exists(s1, x => x == 2)," even exists 3")
	    assert(exists(s1, x => x == 0)," even exists 4")
	    assert(!exists(s1, x => x == 3)," even exists 5")
	    assert(!exists(s1, x => x == -1)," even exists 6")
    }
    
    test("even exist fail tests") {
    	val s1 = union(singletonSet(1), x => x % 2 == 0)
    	assert(exists(s1, x => x % 2 == 0)," even exists 1")
    	assert(!forall(s1, x => x % 2 == 0)," even exists 2")
	    assert(exists(s1, x => x % 2 != 0)," even exists 3")
	    assert(exists(s1, x => x == 2)," even exists 4")
	    assert(exists(s1, x => x == 0)," even exists 5")
	    assert(!exists(s1, x => x == 3)," even exists 6")
	    assert(!exists(s1, x => x == -1)," even exists 7")
	    assert(exists(s1, x => x == 1)," even exists 8")
    }
    
    test("Set(1,3,4,5,7,1000) map _+1 tests") {
	    val s = union(singletonSet(1), x => x == 3 || x == 4 || x == 5 || x == 7 || x == 1000)
	    val ms = map(s, _ + 1)
	    assert(FunSets.toString(ms) === "{2,4,5,6,8}", "Set(1,3,4,5,7,1000) map _+1 tests")
    }
    
    test("forall & map: doubling numbers") {
	    val s = union(singletonSet(0), x => true)
	    val ms = map(s, _ * 2)
	    assert(forall(ms, x => x % 2 == 0), "forall & map: doubling numbers")
    }
}
