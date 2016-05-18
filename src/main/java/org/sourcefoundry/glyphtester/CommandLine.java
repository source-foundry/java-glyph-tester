package org.sourcefoundry.glyphtester;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;
import java.util.List;

/**
 * Created by jorgheymans on 12/05/16.
 */
public class CommandLine {

    @Parameter(names = {"--fontfile"}, required = true, converter = FileConverter.class)
    File fontFile;

    @Parameter(names = {"--fontsize"}, required = true)
    int fontSize = 12;

    @Parameter(names = {"--fontstyle"}, required = true)
    String fontStyle;

    @Parameter(names = {"--canvaswidth"})
    int canvasWidth = 200;

    @Parameter(names = {"--canvasheight"})
    int canvasHeight = 200;

    @Parameter(names = {"--outputdirectory"}, required = true, converter = FileConverter.class)
    File outputDirectory;

    @Parameter(names = {"--renderinghints"})
    String renderingHints;

    @Parameter(names = {"--repeatGlyph"})
    int repeatGlyph = 1;

    @Parameter
    List<String> glyphsToTest;
    private JCommander jCommander = null;

    public CommandLine(String[] args) {
        jCommander = new JCommander(this, args);
    }
}
