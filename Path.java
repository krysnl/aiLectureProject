public class Path {
    private String path;
    private int cost;
    private boolean isGoal;
    private int manhattanCost;

    public Path(String path, int cost) {
        this.path = path;
        this.cost = cost;
        this.isGoal = false;
        this.manhattanCost = 0;
    }

    public Path(String path, int cost, int manhattanCost) {
        this.path = path;
        this.cost = cost;
        this.isGoal = false;
        this.manhattanCost = manhattanCost;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
