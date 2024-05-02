
    import java.util.ArrayDeque;
        import java.util.Queue;
        public class BFS {
    public static void bfs(int[][] graph, int startVertex) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];
        queue.add(startVertex);
        visited[startVertex] = true;
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");
            for (int neighbour: graph[currentVertex]) {
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        queue.add(neighbour);
                    }}}}
            public static void main(String[] args) {
                int[][] graph = {
                        {1, 2}, // Neighbors of vertex 0
                        {2},    // Neighbors of vertex 1
                        {3},    // Neighbors of vertex 2
                        {1, 2}  // Neighbors of vertex 3
                };

                bfs(graph, 0);
            }}
