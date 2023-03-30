import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        API api = API.LINGUAGENS;
        String url = api.getUrl();
        ExtratorConteudo extrator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var diretorio = new File("imagens/");
        diretorio.mkdir();
        InputStream imagemJards;
        boolean usarImagem = false;

        for (Conteudo conteudo : conteudos) {
            String textoFigurinha = "TOPZERA";

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo() + ".png";

            var gerador = new GeradorDeFigurinhas();

            if (usarImagem) {
                imagemJards = new FileInputStream(new File("imagens/jards.png"));
                gerador.cria(inputStream, nomeArquivo, textoFigurinha, imagemJards);
            } else {
                gerador.cria(inputStream, nomeArquivo, textoFigurinha);
            }

            System.out.println(conteudo.titulo());

            System.out.println();
        }
    }
}