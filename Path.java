public class Path {
    private String path;
    private int cost;
    private boolean isGoal;

    public Path(String path, int cost) {
        this.path = path;
        this.cost = cost;
        this.isGoal = false;
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
}
