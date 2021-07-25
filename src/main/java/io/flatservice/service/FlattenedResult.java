package io.flatservice.service;

import java.util.List;
import java.util.Objects;

public class FlattenedResult {
    private final List<String> values;
    private final int depth;

    public FlattenedResult(List<String> values, int depth) {
        this.values = values;
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlattenedResult that = (FlattenedResult) o;
        return depth == that.depth && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, depth);
    }
}
