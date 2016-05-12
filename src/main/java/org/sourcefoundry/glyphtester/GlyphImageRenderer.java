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

    private CommandLine commandLine;
    private TheFont theFont;
    private Object[] renderingHints;

    public GlyphImageRenderer(CommandLine commandLine, TheFont theFont, Object... renderingHints) {
        this.commandLine = commandLine;
        this.theFont = theFont;
        this.renderingHints = renderingHints.length == 0 ? ALL_RENDERING_HINTS : renderingHints;
    }

    public void render(Integer characterCode, String glyph) throws Exception {
        BufferedImage image = new BufferedImage(commandLine.canvasWidth, commandLine.canvasHeight, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = image.createGraphics();
        for (Object renderingHint : renderingHints) {
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, renderingHint);
            g.clearRect(0, 0, commandLine.canvasWidth, commandLine.canvasHeight);
            theFont.paint(g, glyph);
            ImageIO.write(image, "jpeg", new File(commandLine.outputDirectory + "/" + characterCode + "_" + renderingHint.toString() + ".jpg"));
            //System.out.println("Rendered glyph : " + glyph + " from character code " + characterCode);
        }
    }
}
