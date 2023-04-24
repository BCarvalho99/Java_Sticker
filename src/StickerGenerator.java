import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
    public void create(InputStream inputStream, String archiveName) throws IOException {
         
        BufferedImage originalImg = ImageIO.read(inputStream);
        //cria nova imagem em memoria com transparecia e com tamanho novo
        int width = originalImg.getWidth();
        int height = originalImg.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        // copiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImg, 0, 0, null);

        // escrever frase na nova imagem
        String text = "VALOR VALUE";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangle = fontMetrics.getStringBounds(text, graphics);
        int textHeight = (int) retangle.getWidth();
        int xTextPosition = (height - textHeight) / 2;
        int yTextPosition = (newHeight - 100);
        graphics.drawString(text, xTextPosition,yTextPosition );
             
        //modify font
        Font textFont = new Font(Font.SANS_SERIF, Font.BOLD, 500);
        graphics.setFont(textFont);
        graphics.setColor(Color.BLUE);
        
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, textFont, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(textHeight, xTextPosition);
        graphics.setTransform(transform);
        BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);
   
        //escrever a nova imagem em um arquivo
        new File("services/stickerImages"); 
        ImageIO.write(newImage, "png", new File(archiveName));
    }

 
}
