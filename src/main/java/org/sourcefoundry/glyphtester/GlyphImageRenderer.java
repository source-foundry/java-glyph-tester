package org.sourcefoundry.glyphtester;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by jorgheymans on 12/05/16.
 */
public class GlyphImageRenderer {

    private static final Object[] ALL_RENDERING_HINTS = new Object[]{RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR,
            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR,
            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON};

    private final int canvasWidth;
    private final int canvasHeight;
    private TheFont theFont;
    private File outputDirectory;

    public GlyphImageRenderer(int canvasWidth, int canvasHeight, TheFont theFont, File outputDirectory) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.theFont = theFont;
        // todo take renderinghints from commandline ?
//        this.renderingHints = renderingHints.length == 0 ? ALL_RENDERING_HINTS : renderingHints;
        this.outputDirectory = outputDirectory;
        this.outputDirectory.mkdirs();
    }

    public void render(Integer characterCode, String glyph) throws Exception {
        BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = image.createGraphics();
        for (Object renderingHint : ALL_RENDERING_HINTS) {
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, renderingHint);
            g.clearRect(0, 0, canvasWidth, canvasHeight);
            theFont.paint(g, glyph, canvasWidth, canvasHeight);
            ImageIO.write(image, "jpeg", new File(outputDirectory + "/" + characterCode + "_" + renderingHint.toString() + ".jpg"));
        }
    }
}
