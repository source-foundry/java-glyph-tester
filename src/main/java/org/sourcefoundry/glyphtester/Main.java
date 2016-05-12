package org.sourcefoundry.glyphtester;

import java.util.Map;

public class Main {

    /**
     * @param args
     */
    public static void main(final String[] args) throws Exception {
        long now = System.currentTimeMillis();
        System.out.println();
        final CommandLine commandLine = new CommandLine(args);
        final TheFont theFont = new TheFont(commandLine);
        GlyphSource glyphSource = new FontBoxGlyphSource();
        Map<Integer, String> fontGlyphs = glyphSource.read(theFont.getFontFile());
        GlyphImageRenderer glyphImageRenderer = new GlyphImageRenderer(commandLine, theFont);

        for (Map.Entry<Integer, String> entry : fontGlyphs.entrySet()) {
            glyphImageRenderer.render(entry.getKey(), entry.getValue());
        }
        System.out.println("took " + (System.currentTimeMillis() - now) + " ms");
        /**
         final JFrame frame = new JFrame("Glyph Tester");
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frame.add(new FontComponent(theFont));
         frame.setLocationByPlatform(true);
         frame.setSize(200, 200);
         frame.setUndecorated(true);
         frame.setVisible(true);

         static class FontComponent extends JComponent {

         private TheFont theFont;

         public FontComponent(TheFont theFont) {
         this.theFont = theFont;
         }

         @Override public void paintComponent(Graphics g) {
         theFont.paint((Graphics2D) g);
         }
         }

         **/
    }

}