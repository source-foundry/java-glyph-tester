package org.sourcefoundry.glyphtester;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;

/**
 * Created by jorgheymans on 12/05/16.
 */
public class CommandLine {

    @Parameter(names = {"--fontname"}, required = true)
    String fontName;
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
    @Parameter(names = {"--outputdirectory"})
    String outputDirectory;
    private JCommander jCommander = null;

//    @Parameter(names = {"--glyph"})
//    List<String> glyphsToTest;

    public CommandLine(String[] args) {
        jCommander = new JCommander(this, args);
    }
}
