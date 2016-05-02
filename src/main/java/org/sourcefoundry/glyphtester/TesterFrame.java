package org.sourcefoundry.glyphtester;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class TesterFrame {

    private static final String FONT_TO_TEST = "Hack";
    private static String DEFAULT_TEST_STRING = "[]={}_()";

    /**
     * @param args
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (args.length == 1) {
                    DEFAULT_TEST_STRING = args[0];
                }
                final JFrame frame = new JFrame("Glyph Tester");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.add(new FontComponent(FONT_TO_TEST, DEFAULT_TEST_STRING, 12));
                frame.setSize(1000, 1200);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                .getAvailableFontFamilyNames();
                        if (!Arrays.asList(fontFamilies).contains(FONT_TO_TEST)) {
                            JOptionPane.showMessageDialog(frame, FONT_TO_TEST + " font is not installed.",
                                    frame.getTitle(), JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });
    }

}