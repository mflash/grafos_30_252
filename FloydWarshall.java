public class FloydWarshall {

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

        // Percorre todas as arestas do grafo original
        // e preenche as posições correspondentes em
        // dist e next

        // Roda o algoritmo! (3 for + if)

        // Para converter o nome de cada vértice para o seu
        // índice nas matrizes, use mat.mapToArray!
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph("exemplos/tinyEWD.txt");
        FloydWarshall fw = new FloydWarshall(g);
    }
}
