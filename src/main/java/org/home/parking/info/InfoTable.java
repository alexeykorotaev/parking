package org.home.parking.info;

import java.util.Objects;

public class InfoTable {
    private Long numberOfFreePlaces;

    public InfoTable() {}

    public InfoTable(Long numberOfFreePlaces) {
        this.numberOfFreePlaces = numberOfFreePlaces;
    }

    public Long getNumberOfFreePlaces() {
        return numberOfFreePlaces;
    }

    public void setNumberOfFreePlaces(Long numberOfFreePlaces) {
        this.numberOfFreePlaces = numberOfFreePlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoTable infoTable = (InfoTable) o;
        return Objects.equals(numberOfFreePlaces, infoTable.numberOfFreePlaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfFreePlaces);
    }

    @Override
    public String toString() {
        return "InfoTable{" +
                "numberOfFreePlaces=" + numberOfFreePlaces +
                '}';
    }
}
