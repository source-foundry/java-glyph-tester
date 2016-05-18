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

    private TrueTypeFont ttf;

    public FontBoxGlyphSource(File fontFile) throws Exception {
        System.out.println("Parsing font " + fontFile.getAbsolutePath());
        ttf = new TTFParser().parse(fontFile);
    }

    @Override
    public String getFontName() throws Exception {
        return ttf.getNaming().getFontFamily();
    }

    @Override
    public Map<Integer, String> read() throws Exception {

        System.out.println(ttf.getFontBBox());
        System.out.println("Detected font " + ttf.getNaming().getFontFamily() + " (" +
          ttf.getNaming().getFontSubFamily() + ") version " + ttf.getHeader().getFontRevision());
        System.out.println("Total number of glyphs : " + ttf.getNumberOfGlyphs());
        Map<Integer, String> glyphs = new HashMap<>();
        CmapSubtable unicodeCmap = ttf.getUnicodeCmap();
        List<String> unmappedCharacterCodes = new ArrayList<>();
        for (int i = 0; i < ttf.getNumberOfGlyphs(); i++) {
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
