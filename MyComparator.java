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