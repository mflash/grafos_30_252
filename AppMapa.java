import java.util.ArrayList;

public class AppMapa {
    public static void main(String[] args) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        In arq = new In("dados.csv");
        while (arq.hasNextLine()) {
            String line = arq.readLine();
            String[] coords = line.split(";");
            double lat = Double.parseDouble(coords[0]);
            double lon = Double.parseDouble(coords[1]);
            System.out.println(lat + " " + lon);
            pontos.add(new Ponto(lon, lat));
        }
    }
}