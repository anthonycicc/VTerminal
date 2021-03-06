package com.valkryst.VTerminal.GraphicTileTest;

import com.valkryst.VTerminal.GraphicTile;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;
import com.valkryst.VTerminal.misc.ImageCache;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawIT {
    private GraphicTile character;
    private Font font;

    public DrawIT() throws IOException {
        font = FontLoader.loadFontFromJar("Tiles/Nevanda Nethack/bitmap.png", "Tiles/Nevanda Nethack/data.fnt", 1);
    }

    @Before
    public void initializeCharacter() {
        character = new GraphicTile('?');
        character.setBackgroundColor(Color.BLACK);
        character.setForegroundColor(Color.WHITE);
    }

    @Test(expected=NullPointerException.class)
    public void testWithNullGC() {
        character.draw(null, new ImageCache(font), 0, 0);
    }

    @Test(expected=NullPointerException.class)
    public void testWithNullImageCache() {
        final int width = font.getWidth();
        final int height = font.getHeight();
        final BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        character.draw((Graphics2D) temp.getGraphics(), null, 0, 0);
    }

    @Test
    public void testWithValidInputs_withDefaultSettings() {
        final int width = font.getWidth();
        final int height = font.getHeight();
        final BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        character.draw((Graphics2D) temp.getGraphics(), new ImageCache(font), 0, 0);
    }

    @Test
    public void testWithValidInputs_withHiddenChar() {
        final int width = font.getWidth();
        final int height = font.getHeight();
        final BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        character.setHidden(true);
        character.draw((Graphics2D) temp.getGraphics(), new ImageCache(font), 0, 0);
    }

    @Test
    public void testWithValidInputs_withHorizontalFlip() {
        final int width = font.getWidth();
        final int height = font.getHeight();
        final BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        character.setFlippedHorizontally(true);
        character.draw((Graphics2D) temp.getGraphics(), new ImageCache(font), 0, 0);
    }

    @Test
    public void testWithValidInputs_withVerticalFlip() {
        final int width = font.getWidth();
        final int height = font.getHeight();
        final BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        character.setFlippedVertically(true);
        character.draw((Graphics2D) temp.getGraphics(), new ImageCache(font), 0, 0);
    }

    @Test
    public void testWithValidInputs_withUnderline() {
        final int width = font.getWidth();
        final int height = font.getHeight();
        final BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        character.setUnderlined(true);
        character.draw((Graphics2D) temp.getGraphics(), new ImageCache(font), 0, 0);
    }
}
