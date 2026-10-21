package dev.jaderss.algorithms.twopointers;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.toLowerCase;

public final class ValidPalindrome {

    private ValidPalindrome() {}

    public static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (!isLetterOrDigit(text.charAt(left))) {
                left++;
            } else if (!isLetterOrDigit(text.charAt(right))) {
                right--;
            } else if (toLowerCase(text.charAt(left)) != toLowerCase(text.charAt(right))) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
