import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        var diretorio = new File("imagens/");
        diretorio.mkdir();

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));

            String textoFigurinha;
            InputStream imagemJards;
            if (classificacao >= 8.0) {
                textoFigurinha = "TOPZERA";
                imagemJards = new FileInputStream(new File("imagens/jards.png"));
            } else {
                textoFigurinha = "MEIA BOCA";
                imagemJards = null;
            }

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            var gerador = new GeradorDeFigurinhas();
            gerador.cria(inputStream, nomeArquivo, textoFigurinha, imagemJards);

            System.out.println(titulo);

            System.out.println();
        }
    }
}
// U+2B50