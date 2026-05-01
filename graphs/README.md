# Graphs

A complete implementation of graph data structures and algorithms in Java,
each paired with explanations, complexity analysis, and real-world context.

## Structure

| Folder | Topic | What's inside |
|---|---|---|
| `01-representation` | Adjacency List & Matrix | Graph.java, comparison, when to use which |
| `02-bfs` | Breadth-First Search | BFS.java, shortest path on unweighted graphs |
| `03-dfs` | Depth-First Search | DFS.java, recursive + iterative versions |
| `04-dijkstra` | Shortest Path | Dijkstra.java, weighted graph, min-heap |
| `05-mst` | Minimum Spanning Tree | Kruskal.java, Union-Find, greedy approach |

## How to read this folder

Start with `01-representation` — everything else builds on top of it.
The Graph class defined there is used by all subsequent implementations.

## Big-O Summary

| Algorithm | Time | Space | Best for |
|---|---|---|---|
| BFS | O(V + E) | O(V) | Shortest path, unweighted |
| DFS | O(V + E) | O(V) | Cycle detection, components |
| Dijkstra | O((V+E) log V) | O(V) | Shortest path, weighted |
| Kruskal MST | O(E log E) | O(V) | Minimum spanning tree |

## Key concepts

- **V** = number of vertices, **E** = number of edges
- Use adjacency list when the graph is sparse (E << V²)
- Use adjacency matrix when you need fast edge lookup O(1)
- Dijkstra requires non-negative edge weights — use Bellman-Ford otherwise

## Mini-app

See `mini-app/` — a Campus Navigator that uses Dijkstra to find the
shortest path between buildings on a weighted campus graph.