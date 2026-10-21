package dev.jaderss.algorithms.linkedlist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.Test;

class LruCacheTest {

    @Test
    void evictsTheLeastRecentlyUsedEntry() {
        LruCache<Integer, Integer> cache = new LruCache<>(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1)).contains(1);
        cache.put(3, 3);
        assertThat(cache.get(2)).isEmpty();
        cache.put(4, 4);
        assertThat(cache.get(1)).isEmpty();
        assertThat(cache.get(3)).contains(3);
        assertThat(cache.get(4)).contains(4);
    }

    @Test
    void replacingAValueKeepsTheSizeAndRefreshesTheEntry() {
        LruCache<String, String> cache = new LruCache<>(2);
        cache.put("a", "first");
        cache.put("b", "second");
        cache.put("a", "replaced");
        cache.put("c", "third");
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get("a")).contains("replaced");
        assertThat(cache.get("b")).isEmpty();
    }

    @Test
    void rejectsInvalidCapacityAndNulls() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LruCache<>(0));
        LruCache<String, String> cache = new LruCache<>(1);
        assertThatNullPointerException().isThrownBy(() -> cache.put(null, "value"));
        assertThatNullPointerException().isThrownBy(() -> cache.put("key", null));
    }
}
