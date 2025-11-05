import java.util.LinkedList;

public class FloydWarshall {

    private static final String NEWLINE = System.getProperty("line.separator");
    private double[][] dist;
    private int[][] next;
    private AdjMatrixEdgeWeightedDigraph mat;

    public FloydWarshall(EdgeWeightedDigraph g) {
        mat = new AdjMatrixEdgeWeightedDigraph(g);
        // System.out.println(mat);

        int totalVertices = g.getTotalVerts();
        dist = new double[totalVertices][totalVertices];
        next = new int[totalVertices][totalVertices];

        // Inicializa todos os elementos de dist
        // com infinito (Double.POSITIVE_INFINITY)
        // (exceto a diagonal principal, inicializa com 0)

        // Inicializa todos os elementos de next
        // com -1

        for (int i = 0; i < totalVertices; i++) {
            for (int j = 0; j < totalVertices; j++) {
                if (i != j)
                    dist[i][j] = Double.POSITIVE_INFINITY;
                next[i][j] = -1;
            }
        }

        // Percorre todas as arestas do grafo original
        // e preenche as posições correspondentes em
        // dist e next
        for (Edge e : g.getEdges()) {
            String v1 = e.getV();
            String v2 = e.getW();
            double weight = e.getWeight();
            int ind_v1 = mat.mapToArray(v1);
            int ind_v2 = mat.mapToArray(v2);
            dist[ind_v1][ind_v2] = weight;
            next[ind_v1][ind_v2] = ind_v1;
        }

        // Roda o algoritmo! (3 for + if)
        // Para converter o nome de cada vértice para o seu
        // índice nas matrizes, use mat.mapToArray!
        for (int k = 0; k < totalVertices; k++) {
            for (int i = 0; i < totalVertices; i++) {
                for (int j = 0; j < totalVertices; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[k][j];
                    }
                }
            }
        }
    }

    public boolean hasPathTo(String u, String v) {
        int u1 = mat.mapToArray(u);
        int v1 = mat.mapToArray(v);
        // Retorna false se a matriz tiver -1
        return next[u1][v1] != -1;
    }

    public double distTo(String u, String v) {
        int u1 = mat.mapToArray(u);
        int v1 = mat.mapToArray(v);
        // Retorna a distância total (ou infinito, se não houver caminho)
        return dist[u1][v1];
    }

    public Iterable<String> pathTo(String u, String v) {
        LinkedList<String> path = new LinkedList<>();
        // Converte os nomes de vértices para os índices na matriz
        int ind_u = mat.mapToArray(u);
        int ind_v = mat.mapToArray(v);
        // Quando ind_v for -1, chegamos no primeiro
        while (ind_v != -1) {
            // Insere no início da lista (pois percorremos do fim para o início)
            path.addFirst(v);
            // Próximo vértice (i.e. vértice anterior)
            ind_v = next[ind_u][ind_v];
            // Obtém o nome do vértice a partir do índice
            v = mat.mapToString(ind_v);
        }
        return path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Distâncias:" + NEWLINE);
        sb.append("  ");
        for (int i = 0; i < dist.length; i++) {
            String v = mat.mapToString(i);
            sb.append(String.format("%-5s ", v));
        }
        sb.append(NEWLINE);
        for (int i = 0; i < dist.length; i++) {
            String v = mat.mapToString(i);
            sb.append(v + " ");
            for (int j = 0; j < dist[i].length; j++) {
                if (dist[i][j] != Double.POSITIVE_INFINITY)
                    sb.append(String.format("%5.2f ", dist[i][j]));
                else
                    sb.append("----- ");
            }
            sb.append(NEWLINE);
        }
        // Caminhos
        sb.append(NEWLINE + "Caminhos:" + NEWLINE);
        sb.append("  ");
        for (int i = 0; i < next.length; i++) {
            String v = mat.mapToString(i);
            sb.append(String.format("%-5s ", v));
        }
        sb.append(NEWLINE);
        for (int i = 0; i < next.length; i++) {
            String v = mat.mapToString(i);
            sb.append(v + " ");
            for (int j = 0; j < next[i].length; j++) {
                if (next[i][j] != -1) {
                    String w = mat.mapToString(next[i][j]);
                    sb.append(String.format("%-5s ", w));
                } else
                    sb.append("----- ");
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph("exemplos/tinyEWD.txt");
        FloydWarshall fw = new FloydWarshall(g);
        System.out.println(fw);

        System.out.print("Caminho de 0 até 6: ");
        if (fw.hasPathTo("0", "6")) {
            for (String v : fw.pathTo("0", "6")) {
                System.out.print(v + " ");
            }
            System.out.println("(" + fw.distTo("0", "6") + ")");
        }
    }
}
