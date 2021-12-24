import java.util.ArrayList;

public class State {

    private ArrayList<State> adjacencyList;
    private StateType stateType;
    private int cost;

    public State(ArrayList<State> adjacencyList, String stateType ) {
        System.out.println(adjacencyList);
        this.adjacencyList = adjacencyList;
        converter(stateType);
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
                this.cost = -8;
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

    public ArrayList<State> getAdjacencyList() {
        return adjacencyList;
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