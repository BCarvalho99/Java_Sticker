import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImdbContentExtractor implements ContentExtractor{
    
    public List<Content> contentExtract(String json) {
         
        //extraindo os dados que interessam titulo, poster, classifiacao
         JsonParser parser = new JsonParser();
         List<Map<String, String>> contentList = parser.extract(json);
        
         List<Content> contents = new ArrayList<>();
         
         //populando lista de conteudos
         for (Map<String, String> attributes : contentList) {
            String title = attributes.get("title");
            String imageURL = attributes.get("image").replaceAll("(@+).(.*).jpg$", "$1.jpg");
            Content content = new Content(title, imageURL);

            contents.add(content);
         }
         return contents;
    };
}
