package org.sourcefoundry.glyphtester;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    /**
     * @param args
     */
    public static void main(final String[] args) throws Exception {
        long now = System.currentTimeMillis();
        final CommandLine commandLine = new CommandLine(args);
        List<File> fontFiles = new ArrayList<>();
        String fontStyle = commandLine.fontStyle;

        if (commandLine.fontFile.isDirectory()) {
            for (String f : commandLine.fontFile.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".ttf");
                }
            })) {
                fontFiles.add(new File(commandLine.fontFile.getAbsolutePath() + "/" + f));
                fontStyle = null;
            }
        } else {
            fontFiles.add(commandLine.fontFile);
        }
        for (File fontFile : fontFiles) {
            GlyphSource glyphSource = new FontBoxGlyphSource(fontFile);
            TheFont theFont = new TheFont(fontFile, fontStyle, glyphSource.getFontName(), commandLine.fontSize);
            Map<Integer, String> fontGlyphs = glyphSource.read();
            GlyphImageRenderer glyphImageRenderer = new GlyphImageRenderer(commandLine.canvasWidth, commandLine.canvasHeight,
              theFont, new File(commandLine.outputDirectory.getAbsolutePath() + "/" + fontFile.getName()));
            if (commandLine.glyphsToTest != null && !commandLine.glyphsToTest.isEmpty()) {
                for (String glyph : commandLine.glyphsToTest) {
                    String theGlyph = glyph;
                    for (int i = 0; i < commandLine.repeatGlyph - 1; i++) {
                        theGlyph += glyph;
                    }
                    glyphImageRenderer.render(commandLine.glyphsToTest.indexOf(glyph), theGlyph);
                }
            } else {
                for (Map.Entry<Integer, String> entry : fontGlyphs.entrySet()) {
                    glyphImageRenderer.render(entry.getKey(), entry.getValue());
                }
            }
            System.out.println("took " + (System.currentTimeMillis() - now) + " ms");
        }
    }
}