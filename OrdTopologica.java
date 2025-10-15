import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrdTopologica {
    private Set<String> marked; // vértices já visitados
    private List<String> ordTopo; // resultado do algoritmo

    public OrdTopologica(Digraph g) {
        marked = new HashSet<>();
        ordTopo = new LinkedList<>();
        for (String v : g.getVerts()) {
            if (!marked.contains(v))
                dfs(g, v);
        }
    }

    public Iterable<String> getOrdemTopo() {
        return ordTopo;
    }

    private void dfs(Graph g, String v) {
        // System.out.println("Estou em " + v);
        marked.add(v); // marca v como visitado
        for (String w : g.getAdj(v)) {
            // Se w não está marcado, visita!
            if (!marked.contains(w)) {
                dfs(g, w);
            }
        }
        // System.out.println("Saindo de " + v);
        // Insere no início para inverter a ordem
        ordTopo.add(0, v);
    }

    public static void main(String[] args) {
        Digraph g = new Digraph("exemplos/tinyG.txt");
        System.out.println(g.toDot());
        OrdTopologica ot = new OrdTopologica(g);
        for (String v : ot.getOrdemTopo())
            System.out.print(v + " ");
        System.out.println();
    }
}