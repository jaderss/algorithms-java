package dev.jaderss.algorithms.twopointers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidPalindromeTest {

    @ParameterizedTest
    @ValueSource(strings = {"A man, a plan, a canal: Panama", " ", "", "a.", "No 'x' in Nixon", "12321"})
    void readsTheSameBothWaysIgnoringCaseAndPunctuation(String text) {
        assertThat(ValidPalindrome.isPalindrome(text)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"race a car", "0P", "ab", "1a2"})
    void rejectsTextThatDiffersWhenReversed(String text) {
        assertThat(ValidPalindrome.isPalindrome(text)).isFalse();
    }
}
