package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  test("terrain function level 1") {
  new Level1 {
    assert(terrain(Pos(0,0)), "0,0 should be true")
    assert(!terrain(Pos(0,9)), "0,9 should be false")
    assert(terrain(startPos), "startPos should be true")
    assert(terrain(goal), "goal should be true")
    assert(!terrain(Pos(4,11)), "4,11 should be false")
  }
}

test("findChar level 1") {
  new Level1 {
    assert(startPos === Pos(1,1))
    assert(startBlock == Block(startPos, startPos))
    //assert(goal === Pos(7,4)) /// ???????????? (4,7)
    assert(goal === Pos(4,7))
  }
}

test("legal level 1") {
  new Level1 {
    assert(Block(Pos(0,0),Pos(0,0)).isLegal, "0,0 should be true")
    assert(!Block(Pos(0,9),Pos(0,9)).isLegal, "0,9 should be false")
    assert(Block(startPos,startPos).isLegal, "startPos should be true")
    assert(Block(goal,goal).isLegal, "goal should be true")
    assert(!Block(Pos(4,11),Pos(4,11)).isLegal, "4,11 should be false")
  }
}

test("neighbors level 1") {
  new Level1 {
    val h = startBlock.neighbors
    val r = Set(
      (Block(Pos(-1,1),Pos(0,1)), Up),
      (Block(Pos(2,1),Pos(3,1)), Down),
      (Block(Pos(1,-1),Pos(1,0)), Left),
      (Block(Pos(1,2),Pos(1,3)), Right)
    )
    assert(h.toSet === r)
  }
}

test("neighbors above goal level 1") {
  new Level1 {
    val h = Block(Pos(2,7),Pos(3,7)).neighbors
    val r = Set(
      (Block(Pos(2,6),Pos(3,6)),Left),
      (Block(Pos(2,8),Pos(3,8)),Right),
      (Block(Pos(1,7),Pos(1,7)),Up),
      (Block(Pos(4,7),Pos(4,7)),Down)
    )
    assert(h.toSet === r)
  }
}

test("legalNeighbors level 1") {
  new Level1 {
    val h = startBlock.legalNeighbors
    val r = Set(
      (Block(Pos(1,2),Pos(1,3)), Right),
      (Block(Pos(2,1),Pos(3,1)), Down)
    )
    assert(h.toSet === r)
  }
}

test("legalNeighbors above goal level 1") {
  new Level1 {
    val h = Block(Pos(2,7),Pos(3,7)).legalNeighbors
    val r = Set(
      (Block(Pos(2,6),Pos(3,6)),Left),
      (Block(Pos(2,8),Pos(3,8)),Right),
      (Block(Pos(4,7),Pos(4,7)),Down)
    )
    assert(h.toSet === r)
  }
}

test("neighbors with history level 1") {
  new Level1 {
    val h = neighborsWithHistory(Block(Pos(1,1),Pos(1,1)), List(Left,Up))
    val r = Set(
      (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
      (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
    )
    assert(h.toSet === r)
  }
}

test("new neighbors only level 1") {
  new Level1 {
    val h = newNeighborsOnly(
      Set(
        (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
        (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
      ).toStream,

      Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
    )
    val r = Set(
      (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
    ).toStream
    assert(h === r)
  }
}

test("one step before optimal goal level 1") {
  new Level1 {
    val allResults = Set(
      (Block(Pos(2,6),Pos(3,6)),List(Left, Right, Right, Right, Down, Right, Right)),
      (Block(Pos(2,8),Pos(3,8)),List(Right, Right, Right, Right, Down, Right, Right)),
      (Block(Pos(4,7),Pos(4,7)),List(Down, Right, Right, Right, Down, Right, Right))
    ).toStream

    val n = neighborsWithHistory(Block(Pos(2,7),Pos(3,7)),List(Right, Right, Right, Down, Right, Right))
    assert(n === allResults)

    val h = newNeighborsOnly(n, Set(Block(Pos(2,6),Pos(3,6))))
    val newResults = Set(
      (Block(Pos(2,8),Pos(3,8)),List(Right, Right, Right, Right, Down, Right, Right)),
      (Block(Pos(4,7),Pos(4,7)),List(Down, Right, Right, Right, Down, Right, Right))
    ).toStream
    assert(h === newResults)
  }
}

test("terrain function: level 1") {
    new Level1 {

      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
      assert(terrain(Pos(1,1)), "1,1")
      assert(!terrain(Pos(5,9)), "5,9")
      assert(!terrain(Pos(12,14)), "12,14")
      assert(terrain(Pos(4,7)), "4,7")
      assert(terrain(Pos(1,4)), "1,4")
      assert(terrain(Pos(5,6)), "5,6")
      assert(!terrain(Pos(-4,-5)), "-4,-5")
    }
  }

  test("Block: isStanding") {
    new Level1 {
      assert(Block(Pos(0,0),Pos(0,0)).isStanding)
      assert(!(Block(Pos(0,1),Pos(0,2)).isStanding))
    }
  }

  test("Block: isLegal") {
    new Level1 {
      assert(Block(Pos(0,0),Pos(0,0)).isLegal)
      assert(!(Block(Pos(0,2),Pos(0,3)).isLegal))
    }
  }

  test("Block: neighbors") {
    new Level1 {
      assert(Block(Pos(2,2),Pos(2,2)).neighbors.toSet == List(
        (Block(Pos(0,2),Pos(1,2)), Up), 
        (Block(Pos(2,0),Pos(2,1)), Left), 
        (Block(Pos(2,3),Pos(2,4)), Right), 
        (Block(Pos(3,2),Pos(4,2)), Down)).toSet)
    }
  }

  test("Block: legalNeighbors") {
    new Level1 {
      assert(Block(Pos(2,2),Pos(2,2)).legalNeighbors.toSet == List(
        (Block(Pos(0,2),Pos(1,2)), Up), 
        (Block(Pos(2,0),Pos(2,1)), Left), 
        (Block(Pos(2,3),Pos(2,4)), Right)).toSet)
    }
  }

  test("Solver: done") {
    new Level1 {
      assert(done(Block(Pos(4,7),Pos(4,7))))
      assert(!done(Block(Pos(4,7),Pos(4,8))), "Block must be standing on the terminate point")
    }
  }
  
  trait Level2 extends SolutionChecker {
    val level =
    """ooo-----T-
      |oSo-------
      |ooo-------""".stripMargin
  }



  test("Solver: pathsFromStart") {
    new Level1 {

      def pathsToComparablePaths(paths: List[(Block, List[Move])]): List[Set[(Block, List[Move])]] = {
        paths match {
          case Nil => Nil
          case x :: xs => {
            val (first, rest) = paths span (y => y._2.length == x._2.length)
            first.toSet :: pathsToComparablePaths(rest)
          }
        }
      }

      // Complete list of possible positions with 3 or less moves 
      val fromPaths = List((Block(Pos(1,1),Pos(1,1)),List()), 
        (Block(Pos(2,1),Pos(3,1)),List(Down)), 
        (Block(Pos(1,2),Pos(1,3)),List(Right)), 
        (Block(Pos(2,2),Pos(3,2)),List(Right, Down)), 
        (Block(Pos(1,4),Pos(1,4)),List(Right, Right)), 
        (Block(Pos(2,2),Pos(2,3)),List(Down, Right)), 
        (Block(Pos(1,2),Pos(1,2)),List(Up, Right, Down)), 
        (Block(Pos(2,3),Pos(3,3)),List(Right, Right, Down)), 
        (Block(Pos(3,2),Pos(3,3)),List(Down, Down, Right)), 
        (Block(Pos(2,4),Pos(2,4)),List(Right, Down, Right)), 
        (Block(Pos(2,1),Pos(2,1)),List(Left, Down, Right)), 
        (Block(Pos(2,4),Pos(3,4)),List(Down, Right, Right)))


      // The comparison shouldn't take in account the order between similar length paths, so the paths are converted to Sets containing these
      assert(pathsToComparablePaths(fromPaths) === pathsToComparablePaths(pathsFromStart.take(fromPaths.length).toList), "Order of paths and completeness of those")

    }
  }

  test("Solver: pathsToGoal") {
    new Level1 {
      val pathsToGoal3 = List((Block(Pos(4,7),Pos(4,7)),List(Right, Down, Right, Right, Down, Down, Right)), 
                              (Block(Pos(4,7),Pos(4,7)),List(Right, Down, Down, Right, Right, Down, Right)), 
                              (Block(Pos(4,7),Pos(4,7)),List(Down, Right, Right, Right, Down, Right, Right)))

      /* For this level, all solutions have same length, so the order shouldn't matter, hence the toSet */
      assert(pathsToGoal.take(3).toSet === pathsToGoal3.toSet)
    }
  }

  test("Solver: solve for IMPOSSIBRU level") {
    new Level2 {
      assert(pathsToGoal.take(5).toList === Nil)
    }
  }
}
