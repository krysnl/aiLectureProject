import java.util.ArrayList;

public class State {

    private ArrayList<int[]> adjacencyList;
    private StateType stateType;
    private int cost;

    private int x;
    private int y;

    public State(ArrayList<int[]> adjacencyList, String stateType, int x , int y ) {
        this.adjacencyList = adjacencyList;
        converter(stateType);
        this.x = x;
        this.y = y;
    }

    private void converter (String stateType){
        switch (stateType) {
            case "S" -> {
                this.stateType = StateType.S;
                this.cost = 1;
            }
            case "G" -> {
                this.stateType = StateType.G;
                this.cost = 1;
            }
            case "T" -> {
                this.stateType = StateType.T;
                this.cost = 10;
            }
            default -> {
                this.stateType = StateType.E;
                this.cost = 1;
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
}