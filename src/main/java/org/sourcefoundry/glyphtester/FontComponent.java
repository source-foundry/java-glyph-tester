package org.sourcefoundry.glyphtester;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jorgheymans on 02/05/16.
 */
public class FontComponent extends JComponent {

    private static final Object[] ANTI_ALIASING_HINTS = new Object[]{RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR,
            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR,
            RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON};

    private final Font plainFont;
    private final Font boldFont;
    private final Font italicFont;
    private final Font boldItalicFont;
    private final String testString;

    public FontComponent(String fontName, String testString, int fontsize) {
        plainFont = new Font(fontName, Font.PLAIN, fontsize);
        boldFont = new Font(fontName, Font.BOLD, fontsize);
        italicFont = new Font(fontName, Font.ITALIC, fontsize);
        boldItalicFont = new Font(fontName, Font.BOLD + Font.ITALIC, fontsize);
        this.testString = testString;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm = g.getFontMetrics();
        for (int i = 0; i < ANTI_ALIASING_HINTS.length; i++) {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, ANTI_ALIASING_HINTS[i]);
            g.setFont(plainFont);
            g.drawString("HINT: " + ANTI_ALIASING_HINTS[i].toString(), 5, 15 * i + 5 + fm.getAscent() + i * 120);
            g.setFont(plainFont);
            g.drawString("PLAIN " + testString, 5, 15 * i + 20 + fm.getAscent() + i * 120);
            g.setFont(boldFont);
            g.drawString("BOLD " + testString, 5, 15 * i + 40 + fm.getAscent() + i * 120);
            g.setFont(italicFont);
            g.drawString("ITALIC " + testString, 5, 15 * i + 60 + fm.getAscent() + i * 120);
            g.setFont(boldItalicFont);
            g.drawString("BOLD ITALIC " + testString, 5, 15 * i + 80 + fm.getAscent() + i * 120);
        }
    }
}
