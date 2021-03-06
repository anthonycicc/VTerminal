package com.valkryst.VTerminal.shader.character;

import com.valkryst.VTerminal.Tile;
import com.valkryst.VTerminal.GraphicTile;
import com.valkryst.VTerminal.misc.ColorFunctions;
import com.valkryst.VTerminal.shader.Shader;
import lombok.Data;
import lombok.NonNull;

import java.awt.Color;
import java.awt.image.BufferedImage;

@Data
public class CharTintShader implements CharShader {
    /** Whether or not to tint the background color. */
    private boolean tintBackground = false;
    /** Whether or not to tint the foreground color. */
    private boolean tintForeground = false;
    /** The factor to tint the background color by. */
    private double backgroundTintFactor = 0.0;
    /** The factor to tint the foreground color by. */
    private double foregroundTintFactor = 0.0;

    @Override
    public BufferedImage run(@NonNull BufferedImage image, final @NonNull Tile character) {
        if (character instanceof GraphicTile) {
            return image;
        }

        if (tintBackground) {
            final Color originalColor = character.getBackgroundColor();
            final Color tintedColor = ColorFunctions.tint(originalColor, backgroundTintFactor);
            image = swapColor(image, originalColor, tintedColor);
        }

        if (tintForeground) {
            final Color originalColor = character.getForegroundColor();
            final Color tintedColor = ColorFunctions.tint(originalColor, foregroundTintFactor);
            image = swapColor(image, originalColor, tintedColor);
        }

        return image;
    }

    @Override
    public Shader copy() {
        final CharTintShader tintShader = new CharTintShader();
        tintShader.setTintBackground(tintBackground);
        tintShader.setTintForeground(tintForeground);
        tintShader.setBackgroundTintFactor(backgroundTintFactor);
        tintShader.setForegroundTintFactor(foregroundTintFactor);
        return tintShader;
    }
}
