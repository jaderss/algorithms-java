package dev.jaderss.algorithms.unionfind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UnionFindTest {

    @Test
    void startsWithEveryElementAlone() {
        UnionFind sets = new UnionFind(3);
        assertThat(sets.components()).isEqualTo(3);
        assertThat(sets.find(2)).isEqualTo(2);
    }

    @Test
    void joiningSetsSharesARootAndReducesTheCount() {
        UnionFind sets = new UnionFind(4);
        assertThat(sets.union(0, 1)).isTrue();
        assertThat(sets.union(2, 3)).isTrue();
        assertThat(sets.union(1, 3)).isTrue();
        assertThat(sets.find(0)).isEqualTo(sets.find(3));
        assertThat(sets.components()).isOne();
    }

    @Test
    void joiningMembersOfTheSameSetChangesNothing() {
        UnionFind sets = new UnionFind(3);
        sets.union(0, 1);
        assertThat(sets.union(1, 0)).isFalse();
        assertThat(sets.union(2, 2)).isFalse();
        assertThat(sets.components()).isEqualTo(2);
    }
}
