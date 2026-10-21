package dev.jaderss.algorithms.graph;

import static org.assertj.core.api.Assertions.assertThat;

import dev.jaderss.algorithms.graph.CourseSchedule.Prerequisite;
import dev.jaderss.algorithms.unionfind.UnionFind;
import java.util.ArrayList;
import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;

class GraphPropertiesTest {

    @Property
    void islandCountMatchesUnionFindOverNeighbouringLand(
            @ForAll @Size(min = 1, max = 8) List<@StringLength(value = 8) @Chars({'0', '1'}) String> rows) {
        char[][] grid = rows.stream().map(String::toCharArray).toArray(char[][]::new);
        int width = 8;
        UnionFind cells = new UnionFind(rows.size() * width);
        int water = 0;
        for (int row = 0; row < rows.size(); row++) {
            for (int column = 0; column < width; column++) {
                if (grid[row][column] == '0') {
                    water++;
                    continue;
                }
                if (row > 0 && grid[row - 1][column] == '1') {
                    cells.union(row * width + column, (row - 1) * width + column);
                }
                if (column > 0 && grid[row][column - 1] == '1') {
                    cells.union(row * width + column, row * width + column - 1);
                }
            }
        }
        assertThat(NumberOfIslands.count(grid)).isEqualTo(cells.components() - water);
    }

    @Property
    void scheduleRespectsEveryPrerequisiteOfAnAcyclicPlan(
            @ForAll @IntRange(min = 1, max = 12) int courses,
            @ForAll @Size(max = 30) List<@IntRange(max = 11) Integer> firsts,
            @ForAll @Size(max = 30) List<@IntRange(max = 11) Integer> seconds) {
        List<Prerequisite> prerequisites = new ArrayList<>();
        for (int index = 0; index < Math.min(firsts.size(), seconds.size()); index++) {
            int lower = Math.min(firsts.get(index), seconds.get(index)) % courses;
            int higher = Math.max(firsts.get(index), seconds.get(index)) % courses;
            if (lower < higher) {
                prerequisites.add(new Prerequisite(higher, lower));
            }
        }
        List<Integer> order = CourseSchedule.order(courses, prerequisites).orElseThrow();
        assertThat(order).hasSize(courses).doesNotHaveDuplicates();
        assertThat(prerequisites)
                .allSatisfy(prerequisite -> assertThat(order.indexOf(prerequisite.requires()))
                        .isLessThan(order.indexOf(prerequisite.course())));
        if (!prerequisites.isEmpty()) {
            Prerequisite edge = prerequisites.getFirst();
            List<Prerequisite> cyclic = new ArrayList<>(prerequisites);
            cyclic.add(new Prerequisite(edge.requires(), edge.course()));
            assertThat(CourseSchedule.order(courses, cyclic)).isEmpty();
        }
    }
}
