package org.sourcefoundry.glyphtester;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;

/**
 * Encapsulates the font we are going to do tests on.
 * <p>
 * Created by jorgheymans on 12/05/16.
 */
public class TheFont {

    private Font font;

    public TheFont(File fontFile, String fontStyle, String fontName, int fontSize) throws Exception {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
        font = new Font(fontName, getFontStyle(fontStyle == null ? fontFile.getName() : fontStyle), fontSize);
    }

    public void paint(Graphics2D g, String glyph, int canvasWidth, int canvasHeight) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        FontMetrics fm = g.getFontMetrics();
        int x = (canvasWidth - fm.stringWidth(glyph)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = ((canvasHeight - fm.getHeight()) / 2) + fm.getAscent();
        g.setColor(Color.BLACK);
        g.drawString(glyph, x, y);
    }

    /**
     * @param fontStyle
     * @return
     */
    protected int getFontStyle(String fontStyle) {
        if (fontStyle.toLowerCase().contains("regular")) {
            return Font.PLAIN;
        } else if (fontStyle.toLowerCase().contains("italic")) {
            return Font.ITALIC;
        } else if (fontStyle.toLowerCase().contains("bold")) {
            return Font.BOLD;
        } else if (fontStyle.toLowerCase().contains("bolditalic")) {
            return Font.BOLD + Font.ITALIC;
        }
        throw new IllegalArgumentException("cannot parse fontstyle " + fontStyle);
    }
}
