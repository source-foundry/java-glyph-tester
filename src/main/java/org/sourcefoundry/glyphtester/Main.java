package org.sourcefoundry.glyphtester;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class Main {

    /**
     * @param args
     */
    public static void main(final String[] args) throws Exception {
        final FontConfig fontConfig = new FontConfig();
        JCommander jCommander = new JCommander(fontConfig, args);
        if (args.length == 0) {
            jCommander.usage();
            return;
        }

        if (!fontConfig.init()) {
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame frame = new JFrame("Glyph Tester");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.add(new FontComponent(fontConfig));
                frame.setSize(1000, 1200);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment()
                          .getAvailableFontFamilyNames();
                        if (!Arrays.asList(fontFamilies).contains(fontConfig.fontName)) {
                            JOptionPane.showMessageDialog(frame, fontConfig.fontName + " font is not installed.",
                              frame.getTitle(), JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });
    }

    protected static class FontConfig {

        @Parameter(names = {"--fontname"})
        String fontName;

        @Parameter(names = {"--fontdir"})
        String fontDir = null;

        @Parameter(names = {"--fontsize"})
        int fontSize = 12;

        @Parameter
        String testString = "[]={}_()";

        public boolean init() throws Exception {

            if (fontDir != null) {
                File fontDirectory = new File(fontDir);
                if (fontDirectory.exists() && fontDirectory.canRead()) {
                    FileFilter fontFilter = new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            return pathname.getAbsolutePath().endsWith(".ttf");
                        }
                    };

                    for (File fontFile : fontDirectory.listFiles(fontFilter)) {
                        System.out.println("registering font " + fontFile);
                        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
                    }
                } else {
                    System.err.println("font directory " + fontDirectory.getAbsolutePath() + " cannot be read or does not exist");
                    return false;
                }
            }
            return true;
        }
    }
}