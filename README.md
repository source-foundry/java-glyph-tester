# Java Glyph Tester

A tool for testing the JDK font rendering behaviour

###Build
This project uses maven to build : `mvn clean install` builds the uberjar necessary to run the tool.

###Run
Below command will generate an image for all glyphs contained in Hack-Regular, at size 14, using c:\tmp as output directory

`java -jar java-glyphtester-0.1.jar --fontfile C:\Users\jorgheymans\Downloads\Hack-v2_020-ttf\Hack-Regular.ttf --fontsize 14 --fontstyle
regular --outputdirectory c:\tmp`

If you only want to test one or more specific glyphs you can specify them at the end like this:

`java -jar java-glyphtester-0.1.jar --fontfile C:\Users\jorgheymans\Downloads\Hack-v2_020-ttf\Hack-Regular.ttf --fontsize 14 --fontstyle
regular --outputdirectory c:\tmp A B C D`

Note that per glyph 8 images are generated, one for each aliasing mode available in the standard jdk:
RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT
RenderingHints.VALUE_TEXT_ANTIALIAS_GASP
RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR
RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB
RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR
RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB
RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
RenderingHints.VALUE_TEXT_ANTIALIAS_ON

See https://docs.oracle.com/javase/7/docs/api/java/awt/RenderingHints.html for more info
