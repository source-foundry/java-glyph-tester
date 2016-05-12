package org.sourcefoundry.glyphtester;

import java.io.File;
import java.util.Map;

/**
 * Created by jorgheymans on 08/05/16.
 */
public interface GlyphSource {

    /**
     * @return a map of glyph id -> character
     */
    Map<Integer, String> read(File font) throws Exception;
}
