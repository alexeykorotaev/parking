package org.home.parking.barrier;

import java.util.Objects;

public class EntranceBarrier {
    private State state;

    public EntranceBarrier() {}

    public EntranceBarrier(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceBarrier that = (EntranceBarrier) o;
        return state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return "EntranceBarrier{" +
                "state=" + state +
                '}';
    }
}
