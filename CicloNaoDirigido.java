import java.util.HashSet;
import java.util.Set;

public class CicloNaoDirigido {
    private Set<String> marked; // vértices já visitados
    private Set<String> edgeSet; // conj. arestas já usadas
    private boolean hasCycle; // true se encontrou ciclo

    public CicloNaoDirigido(Graph g) {
        marked = new HashSet<>();
        edgeSet = new HashSet<>();
        hasCycle = false;
        for(String v: g.getVerts()) {
            if(!marked.contains(v))
                System.out.println("Iniciando em "+v);
                if(dfs(g, v)) // tem ciclo?
                    break;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    private boolean dfs(Graph g, String v) {
        System.out.println("Estou em " + v);
        marked.add(v); // marca v como visitado
        for (String u : g.getAdj(v)) {
            // Se w não está marcado, visita!
            if (!marked.contains(u)) {
                // Para chegar em w, vim de v
                edgeSet.add(u+"-"+v);
                if(dfs(g, u) == true)
                    return true;
            }
            else {
                String aresta = v+"-"+u;
                if(!edgeSet.contains(aresta)) {
                    System.out.println("Achei um ciclo em "+aresta);
                    hasCycle = true;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph("exemplos/tinyG.txt");
        CicloNaoDirigido detciclo = new CicloNaoDirigido(g);
        if(detciclo.hasCycle())
            System.out.println("Tem ciclo!");
        else
            System.out.println("Não tem ciclo...");
    }
}