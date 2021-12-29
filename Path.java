import java.util.ArrayList;

public class Path {
    private ArrayList<State> path;
    private int cost;
    private boolean isGoal;
    private int manhattanCost;

    public Path(ArrayList<State> path, int cost) {
        this.path = (ArrayList<State>)path.clone();
        this.cost = cost;
        this.isGoal = false;
        this.manhattanCost = 0;
    }

    public Path(State s, int cost) {
        this.path = new ArrayList<>();
        this.path.add(s);
        this.cost = cost;
        this.isGoal = false;
        this.manhattanCost = 0;
    }

    public Path(State s, int cost, int manhattanCost) {
        this.path = new ArrayList<>();
        this.path.add(s);
        this.cost = cost;
        this.isGoal = false;
        this.manhattanCost = manhattanCost;
    }

    public Path(ArrayList<State> path, int cost, int manhattanCost) {
        this.path = (ArrayList<State>)path.clone();
        this.cost = cost;
        this.isGoal = false;
        this.manhattanCost = manhattanCost;
    }

    public ArrayList<State> getPath() {
        return path;
    }

    public void setPath(ArrayList<State> path) {
        this.path = (ArrayList<State>)path.clone();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public void setGoal(boolean goal) {
        isGoal = goal;
    }

    public int getManhattanCost() {
        return manhattanCost;
    }

    public void setManhattanCost(int manhattanCost) {
        this.manhattanCost = manhattanCost;
    }

}
