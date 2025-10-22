import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KruskalMST {

    private List<Edge> mst;

    public KruskalMST(EdgeWeightedGraph g) {
        UnionFind uf = new UnionFind(g);
        mst = new LinkedList<>();
        // System.out.println("Total sets:" + uf.getTotalSets());
        ArrayList<Edge> edges = new ArrayList<>();
        for (Edge e : g.getEdges()) {
            edges.add(e);
        }
        Collections.sort(edges);
        for (Edge e : edges) {
            // System.out.println(e);
            String v = e.getV();
            String w = e.getW();
            String conj_v = uf.find(v);
            String conj_w = uf.find(w);
            if (!conj_v.equals(conj_w)) {
                // Conj. diferentes => não forma ciclo!
                // Adiciona aresta na árvore
                mst.add(e);
                e.setColor("color=red penwidth=3");
                // Faz a união dos "conjuntos"
                uf.union(conj_v, conj_w);
            }
        }
    }

    public Iterable<Edge> getMST() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph("exemplos/tinyEWG.txt");
        KruskalMST k = new KruskalMST(g);
        for (Edge e : k.getMST()) {
            System.out.println(">>> " + e);
        }
        System.out.println(g.toDot());
    }
}
