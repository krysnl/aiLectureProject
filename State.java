import java.util.ArrayList;

public class State {

    private ArrayList<int[]> adjacencyList;
    private StateType stateType;
    private int cost;
    private int totalCost;
    private int manhattanDistance;

    public boolean isGoal() {
        return isGoal;
    }

    private boolean isGoal;
    private int x;
    private int y;

    public State(ArrayList<int[]> adjacencyList, String stateType, int x , int y ) {
        this.adjacencyList = adjacencyList;
        converter(stateType);
        this.x = x;
        this.y = y;
        this.totalCost = 0;
        this.manhattanDistance = 0;
    }

    private void converter (String stateType){
        switch (stateType) {
            case "S" -> {
                this.stateType = StateType.S;
                this.cost = 1;
                this.isGoal = false;
            }
            case "G" -> {
                this.stateType = StateType.G;
                this.cost = 1;
                this.isGoal = true;
            }
            case "T" -> {
                this.stateType = StateType.T;
                this.cost = 10;
                this.isGoal = false;
            }
            default -> {
                this.stateType = StateType.E;
                this.cost = 1;
                this.isGoal = false;
            }
        }
    }


    public StateType getStateType() {
        return stateType;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<int[]> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(ArrayList<int[]> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public String toString() {
        return "State{" +
                "adjacencyList=" + adjacencyList.toString() +
                ", stateType=" + stateType +
                ", cost=" + cost +
                '}';
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getManhattanDistance() {
        return manhattanDistance;
    }

    public void setManhattanDistance(int manhattanDistance) {
        this.manhattanDistance = manhattanDistance;
    }

}