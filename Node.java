import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Node {
    int row;
    int col;
    boolean block;
    int feature;
    Node parent;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.block = false;
        this.feature = 0;
        this.parent = null;
    }

    public void calculateHeuristic(Node finalNode) {
        this.feature = Math.abs(finalNode.row - this.row) + Math.abs(finalNode.col - this.col);
    }

    public void setNode(Node parent, int cost) {
        this.parent = parent;
        this.feature = parent.feature + cost;
    }

    public boolean checkBetterPath(Node parent, int cost) {
        if (parent.feature + cost < this.feature) {
            this.parent = parent;
            this.feature = parent.feature + cost;
            return true;
        }
        return false;
    }
}

class AStar {
    private static final int HV_COST = 10;
    private static final int DIAGONAL_COST = 14;
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private List<Node> closedList;
    private Node initialNode;
    private Node finalNode;

    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
        this.searchArea = new Node[rows][cols];
        Comparator<Node> nodeComparator = Comparator.comparingInt(node -> node.feature);
        this.openList = new PriorityQueue<>(nodeComparator);
        setNodes();
        this.closedList = new ArrayList<>();
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(finalNode);
                searchArea[i][j] = node;
            }
        }
    }

    public void setBlocks(int[][] blocksArray) {
        for (int[] block : blocksArray) {
            int row = block[0];
            int col = block[1];
            setBlock(row, col);
        }
    }

    public List<Node> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedList.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<>();
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.parent) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.row;
        int col = currentNode.col;
        int lowerRow = row + 1;
        if (lowerRow < searchArea.length) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, lowerRow, DIAGONAL_COST);
            }
            if (col + 1 < searchArea[0].length) {
                checkNode(currentNode, col + 1, lowerRow, DIAGONAL_COST);
            }
            checkNode(currentNode, col, lowerRow, HV_COST);
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.row;
        int col = currentNode.col;
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, HV_COST);
        }
        if (col + 1 < searchArea[0].length) {
            checkNode(currentNode, col + 1, middleRow, HV_COST);
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.row;
        int col = currentNode.col;
        int upperRow = row - 1;
        if (upperRow >= 0) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, upperRow, DIAGONAL_COST);
            }
            if (col + 1 < searchArea[0].length) {
                checkNode(currentNode, col + 1, upperRow, DIAGONAL_COST);
            }
            checkNode(currentNode, col, upperRow, HV_COST);
        }
    }

    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = searchArea[row][col];
        if (!adjacentNode.block && !closedList.contains(adjacentNode)) {
            if (!openList.contains(adjacentNode)) {
                adjacentNode.setNode(currentNode, cost);
                openList.add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    openList.remove(adjacentNode);
                    openList.add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.isEmpty();
    }

    private void setBlock(int row, int col) {
        searchArea[row][col].block = true;
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public List<Node> getClosedList() {
        return closedList;
    }

    public void setClosedList(List<Node> closedList) {
        this.closedList = closedList;
    }
}

 class Main {
    // Constants
    private static final int HV_COST = 10;
    private static final int DIAGONAL_COST = 14;

    public static void main(String[] args) {
        // Define initial and final nodes
        Node initialNode = new Node(0, 0);
        Node finalNode = new Node(4, 4);

        // Create AStar instance
        AStar astar = new AStar(5, 5, initialNode, finalNode);

        // Define blocked nodes
        int[][] blocksArray = {{1, 1}, {2, 1}, {3, 1}, {3, 2}, {3, 3}};


        // Set blocked nodes
        astar.setBlocks(blocksArray);

        // Find path
        List<Node> path = astar.findPath();
        System.out.print("Path: ");
        for (Node node : path) {
            System.out.print("(" + node.row + ", " + node.col + ") ");
        }
        System.out.println(blocksArray);
    }
}
