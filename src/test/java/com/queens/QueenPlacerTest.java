package com.queens;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;

public class QueenPlacerTest {

    @Test
    public void solutionToAscii() {
        List<Integer> solution = Arrays.asList(4, 6, 0, 3, 1, 7, 5, 2);
        String expected =
                ". . . . Q . . . \n" +
                ". . . . . . Q . \n" +
                "Q . . . . . . . \n" +
                ". . . Q . . . . \n" +
                ". Q . . . . . . \n" +
                ". . . . . . . Q \n" +
                ". . . . . Q . . \n" +
                ". . Q . . . . . \n";
        String actual = QueenPlacer.solutionToAscii(solution);

        assertEquals(expected, actual);
    }

    @Test
    public void containsDiagonalAttack() {
        assertTrue( QueenPlacer.containsDiagonalAttack( Arrays.asList( 0, 1, 2, 3)));
        assertTrue( QueenPlacer.containsDiagonalAttack( Arrays.asList( 3, 2, 1, 0)));
    }

    @Test
    public void noDiagonalAttack() {
        assertFalse( QueenPlacer.containsDiagonalAttack( Arrays.asList( 1, 3, 0, 2)));
        assertFalse( QueenPlacer.containsDiagonalAttack( Arrays.asList( 5, 2, 0, 6, 4, 7, 1, 3)));
    }

    @Test
    public void containsStraightLinePlacement() {
        assertTrue( QueenPlacer.containsStraightLinePlacement( Arrays.asList( 0, 2, 4, 1, 0)));
        assertTrue( QueenPlacer.containsStraightLinePlacement( Arrays.asList( 4, 2, 0, 1, 0)));
        assertTrue( QueenPlacer.containsStraightLinePlacement( Arrays.asList( 0, 3, 1, 4, 2)));
        assertTrue( QueenPlacer.containsStraightLinePlacement( Arrays.asList( 2, 3, 1, 4, 0)));
    }

    @Test
    public void noStraightLinePlacement() {
        assertFalse( QueenPlacer.containsStraightLinePlacement( Arrays.asList(2, 0, 3, 1)));
        assertFalse( QueenPlacer.containsStraightLinePlacement( Arrays.asList(0, 3, 1, 4, 3)));
        assertFalse( QueenPlacer.containsStraightLinePlacement( Arrays.asList(5, 3, 0, 4, 7, 1, 6, 2)));
    }

    @Test
    public void solutionNodesEmpty() {
        Set<Integer> columns = IntStream.range(0, 3)
                .boxed().collect(Collectors.toCollection(HashSet::new));

        List<List<Integer>> newSolutionNodes =
            QueenPlacer.createSolutionNodes(new ArrayList<>(), columns);

        assertEquals(3, newSolutionNodes.size());
        assertThat(newSolutionNodes, containsInAnyOrder(
                Collections.singletonList(0), Collections.singletonList(1), Collections.singletonList(2)));
    }

    @Test
    public void solutionNodesFirstRow() {
        Set<Integer> columns = IntStream.range(0, 3)
                .boxed().collect(Collectors.toCollection(HashSet::new));

        List<List<Integer>> newSolutionNodes =
            QueenPlacer.createSolutionNodes(Collections.singletonList(0), columns);

        assertEquals(1, newSolutionNodes.size());
        assertThat(newSolutionNodes, contains(Arrays.asList(0, 2)));
    }

    @Test
    public void solutionNodesSecondRow() {
        Set<Integer> columns = IntStream.range(0, 4)
                .boxed().collect(Collectors.toCollection(HashSet::new));

        List<List<Integer>> newSolutionNodes =
                QueenPlacer.createSolutionNodes(Arrays.asList(0, 3), columns);

        assertEquals(1, newSolutionNodes.size());
        assertThat(newSolutionNodes, contains(Arrays.asList(0, 3, 1)));
    }
}
