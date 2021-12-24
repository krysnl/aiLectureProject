import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("src/mazeinput.txt"));
        String[] borders = bufReader.readLine().split(",");
        State[][] maze = new State[Integer.parseInt(borders[0])][Integer.parseInt(borders[1])];
        int startX ;
        int startY ;
        for(int i = 0 ; i < maze.length ; i++){
            for(int j = 0 ; j< maze[i].length ; j++){
                maze[i][j] = createState(bufReader.readLine().split(",") ,maze , i ,j);
                if (maze[i][j].getStateType() == StateType.S) {
                    startX = j;
                    startY = i;
                }
            }
        }
        for (State[] states : maze) {
            for (State state : states) {
                System.out.println(state.toString());
            }
        }
    }

    public static State createState(String[] str, State[][] maze,int i,int j){
        ArrayList<State> adjacencyList = new ArrayList<State>();
        if (str[0].equals("0")) {
            adjacencyList.add(maze[i-1][j]);
        }
        if (str[1].equals("0")) {
            adjacencyList.add(maze[i][j+1]);
        }
        if (str[2].equals("0")) {
            adjacencyList.add(maze[i+1][j]);
        }
        if (str[3].equals("0")) {
            adjacencyList.add(maze[i][j-1]);
        }
        return new State(adjacencyList,str[4]);
    }

}