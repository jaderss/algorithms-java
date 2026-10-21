package dev.jaderss.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public final class ValidParentheses {

    private static final Map<Character, Character> OPENING_FOR = Map.of(')', '(', ']', '[', '}', '{');

    private ValidParentheses() {}

    public static boolean isValid(String text) {
        Deque<Character> open = new ArrayDeque<>();
        for (char symbol : text.toCharArray()) {
            Character opening = OPENING_FOR.get(symbol);
            if (opening == null) {
                open.push(symbol);
            } else if (open.isEmpty() || !opening.equals(open.pop())) {
                return false;
            }
        }
        return open.isEmpty();
    }
}
