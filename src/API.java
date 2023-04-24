public enum API {
    IMDB_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json",
            new ImdbContentExtractor()),
    IMDB_TVSHOWS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json",new ImdbContentExtractor()),
    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json",
            new NasaContentExtractor()),
    LANG_API("http://localhost:8080/languages", new ImdbContentExtractor());

    private String url;
    private ContentExtractor extractor;


    API(String url, ContentExtractor extractor){
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl() {
        return url;
    }
    
    public ContentExtractor getExtractor() {
        return extractor;
    };
}
