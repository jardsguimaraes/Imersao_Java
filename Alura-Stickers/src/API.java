public enum API {

    IMDB("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
            new ExtratorConteudoIMDB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=s3hRXJdXFMYvyWzc3i2mC7AdTrG2yyIgrUDGWPnO&start_date=2023-03-2&end_date=2023-03-29",
            new ExtratorConteudoNasa());

    private String url;
    private ExtratorConteudo extrator;

    API(String url, ExtratorConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return this.url;
    }

    public ExtratorConteudo getExtrator() {
        return this.extrator;
    }
}
