import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BFS {
    private Set<String> marked; // vértices já visitados
    private Map<String, String> edgeTo; // caminhos w->v
    private Map<String, Integer> distTo; // distâncias de cada vértice ao vértice inicial
    private String s; // vértice inicial

    public BFS(Graph g, String s) {
        this.s = s;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        edgeTo.put(s, null);
        distTo.put(s, 0);
        bfs(g, s);
    }

    public boolean hasPathTo(String w) {
        return marked.contains(w);
        // if(marked.contains(w))
        // return true;
        // return false;
    }

    public Iterable<String> pathTo(String w) {
        List<String> path = new LinkedList<>();
        if (!hasPathTo(w))
            return path;
        // Enquanto não chegar no inicial...
        while (edgeTo.get(w) != null) {
            path.add(0, w);
            // Volta ao anterior no caminho
            w = edgeTo.get(w);
        }
        path.add(0, w); // adiciona o inicial
        return path;
    }

    private void bfs(Graph g, String v) {
        LinkedList<String> fila = new LinkedList<>();
        fila.add(v);
        marked.add(v); // marca como visitado
        while (!fila.isEmpty()) {
            v = fila.removeFirst();
            for (String w : g.getAdj(v)) {
                // Se w não está marcado, visita!
                if (!marked.contains(w)) {
                    // Para chegar em w, vim de v
                    edgeTo.put(w, v);
                    // Distância até w = distância até v + 1
                    distTo.put(w, distTo.get(v) + 1);
                    // E adiciona o vizinho na fila!
                    fila.add(w);
                    marked.add(w); // marca como visitado
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph("exemplos/tinyG.txt");
        BFS bfs = new BFS(g, "0");
        for (String v : g.getVerts()) {
            System.out.print(v + ": ");
            for (String w : bfs.pathTo(v))
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
