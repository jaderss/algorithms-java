package dev.jaderss.algorithms.graph;

import static org.assertj.core.api.Assertions.assertThat;

import dev.jaderss.algorithms.graph.CourseSchedule.Prerequisite;
import java.util.List;
import org.junit.jupiter.api.Test;

class CourseScheduleTest {

    @Test
    void ordersCoursesAfterTheirPrerequisites() {
        assertThat(CourseSchedule.order(2, List.of(new Prerequisite(1, 0)))).contains(List.of(0, 1));
        assertThat(CourseSchedule.order(
                        4,
                        List.of(
                                new Prerequisite(1, 0),
                                new Prerequisite(2, 0),
                                new Prerequisite(3, 1),
                                new Prerequisite(3, 2))))
                .contains(List.of(0, 1, 2, 3));
    }

    @Test
    void coursesWithoutPrerequisitesKeepTheirNumberOrder() {
        assertThat(CourseSchedule.order(3, List.of())).contains(List.of(0, 1, 2));
        assertThat(CourseSchedule.order(0, List.of())).contains(List.of());
    }

    @Test
    void cyclicPrerequisitesCannotBeScheduled() {
        assertThat(CourseSchedule.order(2, List.of(new Prerequisite(1, 0), new Prerequisite(0, 1))))
                .isEmpty();
        assertThat(CourseSchedule.order(1, List.of(new Prerequisite(0, 0)))).isEmpty();
    }
}
