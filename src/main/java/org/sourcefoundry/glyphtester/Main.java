package org.sourcefoundry.glyphtester;

import java.util.Map;

public class Main {

    /**
     * @param args
     */
    public static void main(final String[] args) throws Exception {
        long now = System.currentTimeMillis();
        final CommandLine commandLine = new CommandLine(args);
        GlyphSource glyphSource = new FontBoxGlyphSource(commandLine.fontFile);
        final TheFont theFont = new TheFont(commandLine, glyphSource.getFontName());
        Map<Integer, String> fontGlyphs = glyphSource.read();
        GlyphImageRenderer glyphImageRenderer = new GlyphImageRenderer(commandLine, theFont);
        if (commandLine.glyphsToTest != null && !commandLine.glyphsToTest.isEmpty()) {
            for (String glyph : commandLine.glyphsToTest) {
                glyphImageRenderer.render(commandLine.glyphsToTest.indexOf(glyph), glyph);
            }
        } else {
            for (Map.Entry<Integer, String> entry : fontGlyphs.entrySet()) {
                glyphImageRenderer.render(entry.getKey(), entry.getValue());
            }
        }

        System.out.println("took " + (System.currentTimeMillis() - now) + " ms");
    }
}