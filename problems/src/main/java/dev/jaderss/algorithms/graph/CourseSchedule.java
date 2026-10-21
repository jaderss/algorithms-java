package dev.jaderss.algorithms.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class CourseSchedule {

    public record Prerequisite(int course, int requires) {}

    private CourseSchedule() {}

    public static Optional<List<Integer>> order(int courses, Collection<Prerequisite> prerequisites) {
        List<List<Integer>> unlocks =
                Stream.<List<Integer>>generate(ArrayList::new).limit(courses).toList();
        int[] blockers = new int[courses];
        for (Prerequisite prerequisite : prerequisites) {
            unlocks.get(prerequisite.requires()).add(prerequisite.course());
            blockers[prerequisite.course()]++;
        }
        Deque<Integer> ready = new ArrayDeque<>();
        for (int course = 0; course < courses; course++) {
            if (blockers[course] == 0) {
                ready.addLast(course);
            }
        }
        List<Integer> order = new ArrayList<>(courses);
        while (!ready.isEmpty()) {
            int course = ready.removeFirst();
            order.add(course);
            for (int unlocked : unlocks.get(course)) {
                if (--blockers[unlocked] == 0) {
                    ready.addLast(unlocked);
                }
            }
        }
        return order.size() == courses ? Optional.of(List.copyOf(order)) : Optional.empty();
    }
}
