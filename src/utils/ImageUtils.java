package utils;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static Image carregarImagem(String caminho) {
        try {
            return ImageIO.read(new File(caminho));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
