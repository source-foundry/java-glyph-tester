package org.sourcefoundry.glyphtester;


import javax.swing.JComponent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Created by jorgheymans on 02/05/16.
 */
public class FontComponent extends JComponent {

    private static final Object[] ANTI_ALIASING_HINTS = new Object[]{RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
      RenderingHints.VALUE_TEXT_ANTIALIAS_GASP, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR,
      RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR,
      RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF,
      RenderingHints.VALUE_TEXT_ANTIALIAS_ON};

    private Main.FontConfig fontConfig;

    public FontComponent(Main.FontConfig fontConfig) {
        this.fontConfig = fontConfig;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font plainFont = new Font(fontConfig.fontName, Font.PLAIN, fontConfig.fontSize);
        Font boldFont = new Font(fontConfig.fontName, Font.BOLD, fontConfig.fontSize);
        Font italicFont = new Font(fontConfig.fontName, Font.ITALIC, fontConfig.fontSize);
        Font boldItalicFont = new Font(fontConfig.fontName, Font.BOLD + Font.ITALIC, fontConfig.fontSize);

        FontMetrics fm = g.getFontMetrics();
        for (int i = 0; i < ANTI_ALIASING_HINTS.length; i++) {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, ANTI_ALIASING_HINTS[i]);
            // TODO print font in use
            // System.out.println(g.getFont().getFontName());
            g.drawString("HINT: " + ANTI_ALIASING_HINTS[i].toString(), 5, 15 * i + 5 + fm.getAscent() + i * 120);
            g.setFont(plainFont);
            g.drawString("PLAIN " + fontConfig.testString, 5, 15 * i + 20 + fm.getAscent() + i * 120);
            g.setFont(boldFont);
            g.drawString("BOLD " + fontConfig.testString, 5, 15 * i + 40 + fm.getAscent() + i * 120);
            g.setFont(italicFont);
            g.drawString("ITALIC " + fontConfig.testString, 5, 15 * i + 60 + fm.getAscent() + i * 120);
            g.setFont(boldItalicFont);
            g.drawString("BOLD ITALIC " + fontConfig.testString, 5, 15 * i + 80 + fm.getAscent() + i * 120);
        }
    }
}
