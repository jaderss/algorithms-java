package dev.jaderss.algorithms.stack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidParenthesesTest {

    @ParameterizedTest
    @ValueSource(strings = {"()", "()[]{}", "{[]}", "", "([{}])[]"})
    void acceptsBracketsClosedInTheRightOrder(String text) {
        assertThat(ValidParentheses.isValid(text)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"(]", "([)]", "(", ")", "]", "a", "(a)", "(()"})
    void rejectsUnbalancedOrForeignSymbols(String text) {
        assertThat(ValidParentheses.isValid(text)).isFalse();
    }
}
