package com.valkryst.VTerminal.builder.component;

import com.valkryst.VRadio.Radio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class ProgressBarBuilderTest {
    private ProgressBarBuilder builder;

    @Before
    public void initializeBuilder() {
        builder = new ProgressBarBuilder();
    }

    @Test
    public void testReset() {
        final Radio<String> radio = new Radio<>();

        builder.setWidth(66);
        builder.setHeight(66);

        builder.setRadio(radio);

        builder.setIncompleteCharacter('?');
        builder.setCompleteCharacter('?');

        builder.setBackgroundColor_incomplete(Color.ORANGE);
        builder.setForegroundColor_incomplete(Color.ORANGE);

        builder.setBackgroundColor_complete(Color.ORANGE);
        builder.setForegroundColor_complete(Color.ORANGE);

        builder.reset();

        Assert.assertEquals(10, builder.getWidth());
        Assert.assertEquals(1, builder.getHeight());

        Assert.assertEquals(null, builder.getRadio());

        Assert.assertEquals('█', builder.getIncompleteCharacter());
        Assert.assertEquals('█', builder.getCompleteCharacter());

        Assert.assertEquals(new Color(45, 45, 45), builder.getBackgroundColor_incomplete());
        Assert.assertEquals(new Color(255, 45, 85), builder.getForegroundColor_incomplete());

        Assert.assertEquals(new Color(45, 45, 45), builder.getBackgroundColor_complete());
        Assert.assertEquals(new Color(45, 255, 110), builder.getForegroundColor_complete());
    }
}