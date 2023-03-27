import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados relevantes(titulo, imagem, clssificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.printf("\u001b[1m\u001b[41mTitulo:\u001b[m \u001b[1m%s%n", filme.get("title"));
            System.out.printf("\u001b[1m\u001b[41mImagem:\u001b[m %s%n", filme.get("image"));
            System.out.printf("\u001b[1m\u001b[41mNota:\u001b[m %s%n", filme.get("imDbRating"));

            // var nota = filme.get("imDbRating") / 10;
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int quantidadeDeEstrelas = (int) classificacao;

            for (int i = 1; i <= quantidadeDeEstrelas; i++) {
                System.out.print("⭐");                
            }

            System.out.println();            
        }
    }
}
// U+2B50