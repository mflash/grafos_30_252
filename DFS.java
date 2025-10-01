import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFS {
    private Set<String> marked; // vértices já visitados
    private Map<String, String> edgeTo; // caminhos w->v
    private String s; // vértice inicial

    public DFS(Graph g, String s) {
        this.s = s;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        edgeTo.put(s, null);
        dfs(g, s);
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

    private void dfs(Graph g, String v) {
        System.out.println("Estou em " + v);
        marked.add(v); // marca v como visitado
        for (String w : g.getAdj(v)) {
            // Se w não está marcado, visita!
            if (!marked.contains(w)) {
                // Para chegar em w, vim de v
                edgeTo.put(w, v);
                dfs(g, w);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph("exemplos/tinyG.txt");
        DFS dfs = new DFS(g, "0");
        for (String v : g.getVerts()) {
            System.out.print(v + ": ");
            for (String w : dfs.pathTo(v))
                System.out.print(w + " ");
            System.out.println();
        }
    }
}