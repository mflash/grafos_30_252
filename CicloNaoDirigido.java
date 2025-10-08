import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CicloNaoDirigido {
    private Set<String> marked; // vértices já visitados

    public CicloNaoDirigido(Graph g) {
        marked = new HashSet<>();
        dfs(g, "0");
    }

    private void dfs(Graph g, String v) {
        System.out.println("Estou em " + v);
        marked.add(v); // marca v como visitado
        for (String w : g.getAdj(v)) {
            // Se w não está marcado, visita!
            if (!marked.contains(w)) {
                // Para chegar em w, vim de v
                dfs(g, w);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph("exemplos/tinyG.txt");
        CicloNaoDirigido dfs = new CicloNaoDirigido(g);
    }
}