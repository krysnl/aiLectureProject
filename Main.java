import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static int mazeX ;
    static int mazeY ;
    static int startX ;
    static int startY ;
    public static void main(String[] args) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("mazeinput.txt"));
        String[] borders = bufReader.readLine().split(",");
        mazeX = Integer.parseInt(borders[0]);
        mazeY = Integer.parseInt(borders[1]);
        State[][] maze = new State[mazeX][mazeY];
        for(int i = 0 ; i < maze.length ; i++){
            for(int j = 0 ; j< maze[i].length ; j++){
                maze[i][j] = createState(bufReader.readLine().split(",") ,maze , i ,j);
                if (maze[i][j].getStateType() == StateType.S) {
                    startX = i;
                    startY = j;
                }
            }
        }
        breadthFirstSearch(maze);
        depthFirstSearch(maze);

    }

    public static State createState(String[] str, State[][] maze,int i,int j){
        ArrayList<int[]> adjacencyList = new ArrayList<>();
        if (str[0].equals("0")) {
            adjacencyList.add(new int[]{i - 1, j});
        }
        if (str[1].equals("0")) {
            adjacencyList.add(new int[]{i,j+1});
        }
        if (str[2].equals("0")) {
            adjacencyList.add(new int[]{i+1,j});
        }
        if (str[3].equals("0")) {
            adjacencyList.add(new int[]{i,j-1});
        }
        return new State(adjacencyList,str[4],i,j);
    }

    public static void breadthFirstSearch (State[][] maze){
        Queue<State> queue = new LinkedList<>();
        Queue<Path> pathList = new LinkedList<>();
        boolean[][] isVisited = new boolean[mazeX][mazeY];
        queue.add(maze[startX][startY]);
        pathList.add(new Path ("("+(startX+1)+","+(startY+1)+")",0));
        while(!queue.isEmpty()){
            State s = queue.poll();
            if (s.getStateType() == StateType.G) {
                System.out.println(pathList.peek().getPath() + " cost => " + pathList.peek().getCost());
                return;
            }
            Path oldPath = pathList.poll();
            for (int[] ints : s.getAdjacencyList()) {
                if(!isVisited[ints[0]][ints[1]]) {
                    queue.add(maze[ints[0]][ints[1]]);
                    int cost = oldPath.getCost() + s.getCost();
                    pathList.add(new Path(oldPath.getPath()+" -> ("+(ints[0]+1)+","+(ints[1]+1)+")",cost));
                    isVisited[s.getX()][s.getY()] = true;
                }
            }
        }
    }
    public static void depthFirstSearch (State[][] maze){
        Stack<State> queue = new Stack<>();
        Stack<Path> pathList = new Stack<>();
        boolean[][] isVisited = new boolean[mazeX][mazeY];
        queue.add(maze[startX][startY]);
        pathList.add(new Path ("("+(startX+1)+","+(startY+1)+")",0));
        while(!queue.isEmpty()){
            State s = queue.pop();
            if (s.getStateType() == StateType.G) {
                System.out.println(pathList.peek().getPath() + " cost => " + pathList.peek().getCost());
                return;
            }
            Path oldPath = pathList.pop();
            for (int[] ints : s.getAdjacencyList()) {
                if(!isVisited[ints[0]][ints[1]]) {
                    queue.add(maze[ints[0]][ints[1]]);
                    int cost = oldPath.getCost() + s.getCost();
                    pathList.add(new Path(oldPath.getPath()+" -> ("+(ints[0]+1)+","+(ints[1]+1)+")",cost));
                    isVisited[s.getX()][s.getY()] = true;
                }
            }
        }
    }

}