package com.valkryst.VTerminal.misc;

import com.valkryst.VTerminal.Tile;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;
import org.junit.Assert;
import org.junit.Test;

import java.awt.Image;
import java.io.IOException;

public class ImageCacheIT {
    private final Font font;

    public ImageCacheIT() throws IOException {
        font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/20pt/bitmap.png", "Fonts/DejaVu Sans Mono/20pt/data.fnt", 1);
    }

    @Test
    public void testConstructor_oneParam_withValidFont() {
        final ImageCache cache = new ImageCache(font);
        Assert.assertEquals(font, cache.getFont());
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_oneParam_withNullFont() {
        new ImageCache(null);
    }

    @Test
    public void testConstructor_twoParams_withValidData() {
        final ImageCache cache = new ImageCache(font, 1);
        Assert.assertEquals(font, cache.getFont());
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_twoParams_withNullFont() {
        new ImageCache(null, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_twoParams_withInvalidDuration() {
        new ImageCache(font, 0);
    }

    @Test
    public void testRetrieveFromCache_withValidInput() {
        final ImageCache cache = new ImageCache(font);
        final Image image = cache.retrieve(new Tile('A'));
        Assert.assertNotNull(image);
    }

    @Test
    public void testRetrieveFromCache_withInvalidInput() {
        final ImageCache cache = new ImageCache(font);
        final Image image = cache.retrieve(new Tile('\u1F5E'));
        Assert.assertNotNull(image);
    }

    @Test(expected=NullPointerException.class)
    public void testRetrieveFromCache_withNullCharacter() {
        final ImageCache cache = new ImageCache(font);
        cache.retrieve(null);
    }
}
