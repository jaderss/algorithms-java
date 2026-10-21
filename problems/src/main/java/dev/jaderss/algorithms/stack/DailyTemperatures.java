package dev.jaderss.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public final class DailyTemperatures {

    private DailyTemperatures() {}

    public static int[] daysUntilWarmer(int[] temperatures) {
        int[] waits = new int[temperatures.length];
        Deque<Integer> waiting = new ArrayDeque<>();
        for (int day = 0; day < temperatures.length; day++) {
            while (!waiting.isEmpty() && temperatures[waiting.peek()] < temperatures[day]) {
                int colderDay = waiting.pop();
                waits[colderDay] = day - colderDay;
            }
            waiting.push(day);
        }
        return waits;
    }
}
