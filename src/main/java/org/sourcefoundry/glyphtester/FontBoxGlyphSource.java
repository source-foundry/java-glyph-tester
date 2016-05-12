package org.sourcefoundry.glyphtester;

import org.apache.fontbox.ttf.CmapSubtable;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by jorgheymans on 09/05/16.
 */
public class FontBoxGlyphSource implements GlyphSource {

    private TTFParser ttfParser = new TTFParser();

    @Override
    public Map<Integer, String> read(File fontFile) throws Exception {
        TTFParser parser = ttfParser;
        System.out.println("Parsing font " + fontFile.getAbsolutePath());
        TrueTypeFont font = parser.parse(fontFile);
        System.out.println("Detected font " + font.getNaming().getFontFamily() + " (" + font.getNaming().getFontSubFamily() + ") version " + font.getHeader().getFontRevision());
        System.out.println("Total number of glyphs : " + font.getNumberOfGlyphs());
        Map<Integer, String> glyphs = new HashMap<>();
        CmapSubtable unicodeCmap = font.getUnicodeCmap();
        List<String> unmappedCharacterCodes = new ArrayList<>();
        for (int i = 0; i < font.getNumberOfGlyphs(); i++) {
            Integer characterCode = unicodeCmap.getCharacterCode(i);
            if (characterCode != null) {
                glyphs.put(characterCode, String.valueOf(Character.toChars(unicodeCmap.getCharacterCode(i))));
            } else {
                unmappedCharacterCodes.add(String.valueOf(i));
            }
        }
        if (!unmappedCharacterCodes.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            ListIterator<String> it = unmappedCharacterCodes.listIterator();
            while (it.hasNext()) {
                sb.append(it.next());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
            System.out.println("Glyph ids that cannot be rendered : " + sb);
        }
        return glyphs;
    }
}
