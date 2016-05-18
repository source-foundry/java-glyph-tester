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

    private CommandLine commandLine;

    public TheFont(CommandLine commandLine, String fontName) throws Exception {
        this.commandLine = commandLine;
        // first we register the font
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, commandLine.fontFile));
        // instantiate it
        System.out.println(fontName);
        font = new Font(fontName, getFontStyle(commandLine.fontStyle), commandLine.fontSize);
        new File(commandLine.outputDirectory).mkdirs();
    }

    public void paint(Graphics2D g, String glyph) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, commandLine.canvasWidth, commandLine.canvasHeight);
        FontMetrics fm = g.getFontMetrics();
        int x = (commandLine.canvasWidth - fm.stringWidth(glyph)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = ((commandLine.canvasHeight - fm.getHeight()) / 2) + fm.getAscent();
        g.setColor(Color.BLACK);
        g.drawString(glyph, x, y);
    }

    /**
     * @param fontStyle
     * @return
     */
    protected int getFontStyle(String fontStyle) {
        if ("regular".equalsIgnoreCase(fontStyle)) {
            return Font.PLAIN;
        } else if ("italic".equalsIgnoreCase(fontStyle)) {
            return Font.ITALIC;
        } else if ("bold".equalsIgnoreCase(fontStyle)) {
            return Font.BOLD;
        } else if ("bolditalic".equalsIgnoreCase(fontStyle)) {
            return Font.BOLD + Font.ITALIC;
        }
        throw new IllegalArgumentException("cannot parse fontstyle " + fontStyle);
    }
}
