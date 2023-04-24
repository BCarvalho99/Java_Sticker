import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
 
        API api = API.LANG_API;
        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();

        HttpCli http = new HttpCli();
        String json = http.searchData(url);

        // expressoes regulares || regex pro JSON
        List<Content> contents = extractor.contentExtract(json);

        StickerGenerator generator = new StickerGenerator();
        for (int i = 0; i < 2; i++) {
            Content content = contents.get(i);
         
            InputStream inputStream = new URL(content.getImageURL()).openStream();
            String archiveName = "services/stickerImages/" + content.getTitle() + ".png";
            generator.create(inputStream, archiveName);
            
            System.out.println(content.getTitle());
            System.out.println();
        }
    }
}

