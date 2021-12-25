import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

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
        uniformCostSearch(maze);

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
        isVisited[startX][startY] = true;
        queue.add(maze[startX][startY]);
        pathList.add(new Path ("("+(startX+1)+","+(startY+1)+")",0));
        while(!queue.isEmpty()){
            State s = queue.poll();
            if (s.isGoal()) {
                System.out.println(pathList.peek().getPath() + " cost => " + pathList.peek().getCost());
                return;
            }
            Path oldPath = pathList.poll();
            for (int[] ints : s.getAdjacencyList()) {
                if(!isVisited[ints[0]][ints[1]]) {
                    queue.add(maze[ints[0]][ints[1]]);
                    int cost = oldPath.getCost() + s.getCost();
                    pathList.add(new Path(oldPath.getPath()+" -> ("+(ints[0]+1)+","+(ints[1]+1)+")",cost));
                    isVisited[ints[0]][ints[1]] = true;
                }
            }
        }
    }

    public static void depthFirstSearch (State[][] maze){
        Stack<State> stack = new Stack<>();
        Stack<Path> pathList = new Stack<>();
        boolean[][] isVisited = new boolean[mazeX][mazeY];
        stack.push(maze[startX][startY]);
        pathList.push(new Path ("("+(startX+1)+","+(startY+1)+")",0));
        while(!stack.isEmpty()){
            State s = stack.pop();
            isVisited[s.getX()][s.getY()] = true;
            if (s.isGoal()) {
                System.out.println(pathList.peek().getPath() + " cost => " + pathList.peek().getCost());
                return;
            }
            Path oldPath = pathList.pop();
            for (int[] ints : s.getAdjacencyList()) {
                if(!isVisited[ints[0]][ints[1]]) {
                    stack.push(maze[ints[0]][ints[1]]);
                    int cost = oldPath.getCost() + s.getCost();
                    pathList.push(new Path(oldPath.getPath()+" -> ("+(ints[0]+1)+","+(ints[1]+1)+")",cost));
                }
            }
        }
    }

    public static void uniformCostSearch (State[][] maze){
        PriorityQueue<State> queue = new PriorityQueue<State>(new MyComparator());
        PriorityQueue<Path> pathList = new PriorityQueue<Path>(new MyComparatorPath());
        boolean[][] isVisited = new boolean[mazeX][mazeY];
        queue.add(maze[startX][startY]);
        pathList.add(new Path ("("+(startX+1)+","+(startY+1)+")",0));
        while(!queue.isEmpty()){
            State s = queue.poll();
            Path oldPath = pathList.poll();
            if(s.isGoal()){
                System.out.println(oldPath.getPath() + " cost => " + oldPath.getCost());
                return;
            }
            for (int[] ints : s.getAdjacencyList()) {
                if(!isVisited[ints[0]][ints[1]]) {
                    int cost = oldPath.getCost() + s.getCost();
                    State nextState = maze[ints[0]][ints[1]];
                    nextState.setTotalCost(cost);
                    queue.add(nextState);
                    pathList.add(new Path(oldPath.getPath()+" -> ("+(ints[0]+1)+","+(ints[1]+1)+")",cost));
                    isVisited[ints[0]][ints[1]] = true;
                }
            }
        }
    }
}