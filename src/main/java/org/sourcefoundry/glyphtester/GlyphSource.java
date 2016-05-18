package org.sourcefoundry.glyphtester;

import java.util.Map;

/**
 * Created by jorgheymans on 08/05/16.
 */
public interface GlyphSource {

    String getFontName() throws Exception;

    /**
     * @return a map of glyph id -> character
     */
    Map<Integer, String> read() throws Exception;
}
