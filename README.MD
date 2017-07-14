#### License

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

[![MIT License](https://img.shields.io/badge/license-MIT_License-green.svg)](https://github.com/Valkryst/VTerminal/blob/master/LICENSE.md) ![](https://travis-ci.org/Valkryst/VTerminal.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VTerminal/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VTerminal) [![Release](https://jitpack.io/v/Valkryst/VTerminal.svg)](https://jitpack.io/#Valkryst/VTerminal)

# VTerminal


VPanel simulates a Unicode terminal display. It supports arbitrary fore/background colors,  terminal sizes, and allows
the use of any font.

This project is currently under active development and code-breaking changes may be committed as development progresses.

## Jar Files & Maven

To use this project as a Maven dependency, click on the JitPack badge [![Release](https://jitpack.io/v/Valkryst/VTerminal.svg)](https://jitpack.io/#Valkryst/VTerminal), select a version, click the "Get it!" button, and then follow the instructions.

If you would rather use a Jar file, then you can find the Jars on the [releases](https://github.com/Valkryst/VTerminal/releases) page.

## Example Test Code

```java
try {
    final Font font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/20pt/bitmap.png", "Fonts/DejaVu Sans Mono/20pt/data.fnt", 1);
    final Panel panel = new PanelBuilder().setAsciiFont(font).build();

    Thread.sleep(50);
    panel.getCurrentScreen().clear('X');
    panel.draw();
} catch (final IOException | InterruptedException | URISyntaxException e) {
    e.printStackTrace();
}
```

It's possible for the *draw()* call to execute before the *Panel* shows up on-screen. In this case it will display a blank screen.
To prevent this issue, we insert *Thread.sleep(50)* before the first draw of the Panel.

You may not need to do this, but it's required for this example to display as intended.

## JavaDoc Documentation

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VTerminal/

## Fonts
VTerminal supports the use of any bitmap font. The  [DejaVu](http://dejavu-fonts.org/wiki/Main_Page) and 
[GNU FreeFont](https://www.gnu.org/software/freefont/) monospace fonts are supplied with this project in 16pt, 18pt, and 
20pt sizes.

To use a different font:

* Download and install [BMFont](http://www.angelcode.com/products/bmfont/).
* Under *Options->Font Settings*
    * Font: Specify whichever font you want to use.
    * Charset: Unicode
    * Size (px): Set this to whichever size you desire.
    * Match char height: Enabled
    * Height %: 100
    * Enable Bold and/or Italic if you want to.
    * Output invalid char glyph: Enabled
    * Do not include kerning pairs: Enabled
    * Render from TrueType outline: Enabled
    * TrueType hinting: Enabled
    * Font smoothing: Disabled
    * Super sampling: Disabled
    * Outline Thickness: 0
* On the right-hand size of the screen, check every box. They should all be solid green.
* Under *Options->Export Options*
    * All padding/spacing settings must be set to zero.
    * Force offsets to zero: Enabled
    * Width: 2048
        * This may need to be adjusted based on the font size that you choose.
    * Height: 1024
        * This may need to be adjusted based on the font size that you choose.
    * Bit depth: 32
    * Ignore the ARGB fields and their Invert checkboxes.
    * Preset: White text with alpha
    * Font descriptor: Text
    * Textures: png
* Do *Options->Save bitmap font as...* and save your font. This will generate a png and fnt file.

## Misc

* The use of non-mono-space fonts is not supported, but it not specifically prevented.
* Ensure the font png image uses a transparent background with white characters. The program relies on this and unintended behaviour could occur with any other settings.
* In an effort to achieve optimal draw performance, VTerminal makes use of an image cache. Any time an image is drawn with a unique fore/background color, a new image is created, the colors are applied, and then that image is saved in the cache until the process terminates. 
     * This does require some amount of RAM, but it drastically reduces the amount of CPU time spent re-coloring images on every draw call.
     * Assuming that this library is used for GUIs and text-based games, it's reasonable to say that the cache will eventually contain every possible fore/background color & character combination that's used. When this happens, there should be no cache misses and optimal draw times will be reached.
* The -Xmx JVM parameter can be used to reduce the maximum amount of memory that VTerminal is allowed to use.
     * Tests have been run with with -Xmx32M, -Xmx64M, -Xmx128M, and -Xmx256M without affecting performance.
     * Values lower than 32M are not recommended.
     * CPU usage may slightly increase with lower values as the garbage collector may run more frequently.
     * Example Usage: *java -jar MyProgram.jar -Xmx256M*
* All Color objects used with VTerminal must support transparency (the alpha channel).
     * Ensure that you use the *new Color(r, g, b, a)* or *new Color(rgbVal, true)* constructors to save a few CPU cycles when altering/setting fore/background colors.
     * If you prefer to use *new Color(r, g, b)* or *new Color(rgbVal)*, then your Color objects will be re-created with the correct constructors before being applied to any Components or AsciiCharacters.


### Screen Draw Process

1. All components are drawn onto the Screen.
2. The Screen is drawn onto the Panel.
3. The Layers are drawn on top of the Screen on the Panel.
4. The Sub-Screens are drawn on top of the Screen/Layers on the Panel.


### FPS Testing

```java
final Font font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/18pt/bitmap.png", "Fonts/DejaVu Sans Mono/18pt/data.fnt", 1);
final Panel panel = new PanelBuilder().setAsciiFont(font).build();

double smoothing = 0.9; // larger=more smoothing
double measurement = 0.0;

int temp = 45;

while(true) {
    panel.getCurrentScreen().clear((char)temp);
    panel.getCurrentScreen().setForegroundColor(new Color(255, 155, temp));
    panel.getCurrentScreen().setBackgroundColor(new Color(temp, 155, 255));

    temp++;

    if (temp > 55) {
        temp = 45;
    }

    final long bef = System.currentTimeMillis();
    panel.draw();

    final double current = 1000 / Math.max((System.currentTimeMillis() - bef), 1);
    measurement = (measurement * smoothing) + (current * (1.0-smoothing));
    System.out.println("Estimated FPS based on Past Render Times:\t" + measurement);
    Thread.sleep(100);
}
```