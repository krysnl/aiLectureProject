import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int mazeX;
    static int mazeY;
    static int startX;
    static int startY;
    static State[][] maze;
    static ArrayList<State> goalStates;

    public static void main(String[] args) throws IOException {
        createMaze();
        depthFirstSearch();
        breadthFirstSearch();
        iterativeDeepening();
        uniformCostSearch();
        greedyBestFirstSearch();
        aStarHeuristicSearch();
    }

    public static void createMaze () throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("mazeinput.txt"));
        String[] borders = bufReader.readLine().split(",");
        goalStates = new ArrayList<>();
        mazeX = Integer.parseInt(borders[0]);
        mazeY = Integer.parseInt(borders[1]);
        maze = new State[mazeX][mazeY];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = createState(bufReader.readLine().split(","),i,j);
                if (maze[i][j].getStateType() == StateType.S) {
                    startX = i;
                    startY = j;
                }
                if (maze[i][j].getStateType() == StateType.G) {
                    goalStates.add(maze[i][j]);
                }
            }
        }
    }

    public static State createState(String[] str, int i, int j) {
        ArrayList<int[]> adjacencyList = new ArrayList<>();
        if (str[1].equals("0")) {
            adjacencyList.add(new int[]{i, j + 1});
        }
        if (str[2].equals("0")) {
            adjacencyList.add(new int[]{i + 1, j});
        }
        if (str[3].equals("0")) {
            adjacencyList.add(new int[]{i, j - 1});
        }
        if (str[0].equals("0")) {
            adjacencyList.add(new int[]{i - 1, j});
        }
        return new State(adjacencyList, str[4], i, j);
    }

    public static void depthFirstSearch() {
        System.out.println("\nDepth First Search ");
        Stack<State> stack = new Stack<>();
        Stack<Path> pathList = new Stack<>();
        int visitCounter = 0;
        boolean[][] isExplored = new boolean[mazeX][mazeY];
        isExplored[startX][startY] = true;
        stack.push(maze[startX][startY]);
        int maxFrontier = 0;
        pathList.push(new Path("(" + (startX + 1) + "," + (startY + 1) + ")", 0));
        while (!stack.isEmpty()) {
            maxFrontier = Math.max(maxFrontier,stack.size());
            State s = stack.pop();
            visitCounter++;
            if (s.isGoal()) {
                System.out.println("Cost of solution => "+ pathList.peek().getCost());
                System.out.println("Number of expanded nodes => "+ visitCounter);
                System.out.println("Maximum size of frontier => "+maxFrontier);
                System.out.println("Maximum size of explored set => "+visitedCount(isExplored));
                System.out.println("Path of solution => "+pathList.peek().getPath());
                return;
            }
            Path oldPath = pathList.pop();
            for (int[] ints : s.getAdjacencyList()) {
                if (!isExplored[ints[0]][ints[1]]) {
                    stack.push(maze[ints[0]][ints[1]]);
                    int cost = oldPath.getCost() + s.getCost();
                    pathList.push(new Path(oldPath.getPath() + " -> (" + (ints[0] + 1) + "," + (ints[1] + 1) + ")", cost));
                    isExplored[ints[0]][ints[1]] = true;
                }
            }
        }
    }

    public static void breadthFirstSearch() {
        System.out.println("\nBreadth First Search ");
        Queue<State> queue = new LinkedList<>();
        Queue<Path> pathList = new LinkedList<>();
        int visitCounter = 0;
        boolean[][] isExplored = new boolean[mazeX][mazeY];
        isExplored[startX][startY] = true;
        queue.add(maze[startX][startY]);
        int maxFrontier = 0;
        pathList.add(new Path("(" + (startX + 1) + "," + (startY + 1) + ")", 0));
        while (!queue.isEmpty()) {
            maxFrontier = Math.max(maxFrontier, queue.size());
            State s = queue.poll();
            visitCounter++;
            if (s.isGoal()) {
                System.out.println("Cost of solution => "+ pathList.peek().getCost());
                System.out.println("Number of expanded nodes => "+ visitCounter);
                System.out.println("Maximum size of frontier => "+maxFrontier);
                System.out.println("Maximum size of explored set => "+visitedCount(isExplored));
                System.out.println("Path of solution => "+pathList.peek().getPath());
                return;
            }
            Path oldPath = pathList.poll();
            for (int[] ints : s.getAdjacencyList()) {
                if (!isExplored[ints[0]][ints[1]]) {
                    queue.add(maze[ints[0]][ints[1]]);
                    int cost = oldPath.getCost() + s.getCost();
                    pathList.add(new Path(oldPath.getPath() + " -> (" + (ints[0] + 1) + "," + (ints[1] + 1) + ")", cost));
                    isExplored[ints[0]][ints[1]] = true;
                }
            }
        }
    }

    public static void iterativeDeepening() {
        System.out.println("\nIterative Deepening ");
        Stack<State> stack = new Stack<>();
        Stack<Path> pathList = new Stack<>();
        Stack<State> stack2 = new Stack<>();
        Stack<Path> pathList2 = new Stack<>();
        int visitCounter = 0;
        boolean[][] isExplored = new boolean[mazeX][mazeY];
        int depth = 0;
        int maxDepth = 0;
        stack.push(maze[startX][startY]);
        int maxFrontier = stack.size();
        pathList.push(new Path("(" + (startX + 1) + "," + (startY + 1) + ")", 0));
        while (true) {
            maxDepth++;
            while (!stack.isEmpty()) {
                maxFrontier = Math.max(maxFrontier, stack.size());
                State s = stack.pop();
                isExplored[s.getX()][s.getY()] = true;
                visitCounter++;
                if (s.isGoal()) {
                    System.out.println("Cost of solution => "+ pathList.peek().getCost());
                    System.out.println("Number of expanded nodes => "+visitCounter);
                    System.out.println("Maximum size of frontier => "+maxFrontier);
                    System.out.println("Maximum size of explored set => "+visitedCount(isExplored));
                    System.out.println("Path of solution => "+pathList.peek().getPath());
                    return;
                }
                Path oldPath = pathList.pop();
                if (depth > maxDepth) {
                    stack2.add(s);
                    pathList2.add(oldPath);
                    continue;
                }
                for (int[] ints : s.getAdjacencyList()) {
                    if (!isExplored[ints[0]][ints[1]]) {
                        stack2.push(maze[ints[0]][ints[1]]);
                        int cost = oldPath.getCost() + s.getCost();
                        pathList2.push(new Path(oldPath.getPath() + " -> (" + (ints[0] + 1) + "," + (ints[1] + 1) + ")", cost));
                    }
                }
            }
            depth++;
            stack = (Stack<State>) stack2.clone();
            pathList = (Stack<Path>)pathList2.clone();
        }
    }

    public static void uniformCostSearch() {
        System.out.println("\nUniform Cost Search ");
        PriorityQueue<State> queue = new PriorityQueue<State>(new MyComparator());
        PriorityQueue<Path> pathList = new PriorityQueue<Path>(new MyComparatorPath());
        int visitCounter = 0;
        boolean[][] isExplored = new boolean[mazeX][mazeY];
        queue.add(maze[startX][startY]);
        int maxFrontier = queue.size();
        pathList.add(new Path("(" + (startX + 1) + "," + (startY + 1) + ")", 0));
        while (!queue.isEmpty()) {
            maxFrontier = Math.max(maxFrontier, queue.size());
            State s = queue.poll();
            Path oldPath = pathList.poll();
            visitCounter++;
            if (s.isGoal()) {
                System.out.println("Cost of solution => "+ oldPath.getCost());
                System.out.println("Number of expanded nodes => "+ visitCounter);
                System.out.println("Maximum size of frontier => "+maxFrontier);
                System.out.println("Maximum size of explored set => "+visitedCount(isExplored));
                System.out.println("Path of solution => "+oldPath.getPath());
                return;
            }
            for (int[] ints : s.getAdjacencyList()) {
                if (!isExplored[ints[0]][ints[1]]) {
                    int cost = oldPath.getCost() + s.getCost();
                    State nextState = maze[ints[0]][ints[1]];
                    nextState.setTotalCost(cost);
                    queue.add(nextState);
                    pathList.add(new Path(oldPath.getPath() + " -> (" + (ints[0] + 1) + "," + (ints[1] + 1) + ")", cost));
                    isExplored[ints[0]][ints[1]] = true;
                }
            }
        }
    }

    public static void greedyBestFirstSearch() {
        System.out.println("\nGreedy Best First Search ");
        PriorityQueue<State> queue = new PriorityQueue<State>(new MyComparatorManhattan());
        PriorityQueue<Path> pathList = new PriorityQueue<Path>(new MyComparatorManhattanPath());
        int visitCounter = 0;
        boolean[][] isExplored = new boolean[mazeX][mazeY];
        State startState = maze[startX][startY];
        queue.add(startState);
        int maxFrontier = queue.size();
        startState.setManhattanDistance(manhattanDistanceCalculator(startState));
        pathList.add(new Path("(" + (startX + 1) + "," + (startY + 1) + ")", 0, startState.getManhattanDistance()));
        while (!queue.isEmpty()) {
            maxFrontier = Math.max(maxFrontier, queue.size());
            State s = queue.poll();
            Path oldPath = pathList.poll();
            visitCounter++;
            if (s.isGoal()) {
                System.out.println("Cost of solution => "+ oldPath.getCost());
                System.out.println("Number of expanded nodes => "+visitCounter);
                System.out.println("Maximum size of frontier => "+maxFrontier);
                System.out.println("Maximum size of explored set => "+visitedCount(isExplored));
                System.out.println("Path of solution => "+oldPath.getPath());
                return;
            }
            for (int[] ints : s.getAdjacencyList()) {
                if (!isExplored[ints[0]][ints[1]]) {
                    int cost = oldPath.getCost() + s.getCost();
                    State nextState = maze[ints[0]][ints[1]];
                    nextState.setManhattanDistance(manhattanDistanceCalculator(nextState));
                    nextState.setTotalCost(cost);
                    queue.add(nextState);
                    pathList.add(new Path(oldPath.getPath() + " -> (" + (ints[0] + 1) + "," + (ints[1] + 1) + ")", cost, nextState.getManhattanDistance()));
                    isExplored[ints[0]][ints[1]] = true;
                }
            }
        }
    }

    public static void aStarHeuristicSearch() {
        System.out.println("\nA* Heuristic Search");
        PriorityQueue<State> queue = new PriorityQueue<State>(new MyComparatorAStar());
        PriorityQueue<Path> pathList = new PriorityQueue<Path>(new MyComparatorAStarPath());
        int visitCounter = 0;
        boolean[][] isExplored = new boolean[mazeX][mazeY];
        State startState = maze[startX][startY];
        queue.add(startState);
        int maxFrontier = queue.size();
        startState.setManhattanDistance(manhattanDistanceCalculator(startState));
        pathList.add(new Path("(" + (startX + 1) + "," + (startY + 1) + ")", 0, startState.getManhattanDistance()));
        while (!queue.isEmpty()) {
            maxFrontier = Math.max(maxFrontier, queue.size());
            State s = queue.poll();
            Path oldPath = pathList.poll();
            visitCounter++;
            if (s.isGoal()) {
                System.out.println("Cost of solution => "+ oldPath.getCost());
                System.out.println("Number of expanded nodes => "+visitCounter);
                System.out.println("Maximum size of frontier => "+maxFrontier);
                System.out.println("Maximum size of explored set => "+visitedCount(isExplored));
                System.out.println("Path of solution => "+oldPath.getPath());
                return;
            }
            for (int[] ints : s.getAdjacencyList()) {
                if (!isExplored[ints[0]][ints[1]]) {
                    int cost = oldPath.getCost() + s.getCost();
                    State nextState = maze[ints[0]][ints[1]];
                    nextState.setManhattanDistance(manhattanDistanceCalculator(nextState));
                    nextState.setTotalCost(cost);
                    queue.add(nextState);
                    pathList.add(new Path(oldPath.getPath() + " -> (" + (ints[0] + 1) + "," + (ints[1] + 1) + ")", cost, nextState.getManhattanDistance()));
                    isExplored[ints[0]][ints[1]] = true;
                }
            }
        }
    }

    public static int manhattanDistanceCalculator(State state) {
        int min = Integer.MAX_VALUE;
        for (State goalState : goalStates) {
            int newManhattan = Math.abs(state.getY() - goalState.getY()) + Math.abs(state.getX() - goalState.getX());
            min = Math.min(min, newManhattan);
        }
        return min;
    }

    public static int visitedCount (boolean isExplored[][]){
        int visitedCount = 0;
        for (boolean[] booleans : isExplored) {
            for (boolean aBoolean : booleans) {
                if(aBoolean){
                    visitedCount++;
                }
            }
        }
        return visitedCount;
    }
}