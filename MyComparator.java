import java.util.Comparator;

class MyComparator implements Comparator<State> {

    @Override
    public int compare(State a, State b) {
        return a.getTotalCost() > b.getTotalCost() ? 1 : -1;
    }
}

class MyComparatorPath implements Comparator<Path> {

    @Override
    public int compare(Path a, Path b) {
        return a.getCost() > b.getCost() ? 1 : -1;
    }
}

class MyComparatorManhattan implements Comparator<State> {

    @Override
    public int compare(State a, State b) {
        return a.getManhattanDistance() > b.getManhattanDistance() ? 1 : -1;
    }
}

class MyComparatorManhattanPath implements Comparator<Path> {

    @Override
    public int compare(Path a, Path b) {
        return a.getManhattanCost() > b.getManhattanCost() ? 1 : -1;
    }
}

class MyComparatorAStar implements Comparator<State> {

    @Override
    public int compare(State a, State b) {
        return a.getManhattanDistance()+a.getTotalCost() > b.getManhattanDistance()+b.getTotalCost() ? 1 : -1;
    }
}

class MyComparatorAStarPath implements Comparator<Path> {

    @Override
    public int compare(Path a, Path b) {
        return a.getManhattanCost()+a.getCost() > b.getManhattanCost()+b.getCost() ? 1 : -1;
    }
}