package com.valkryst.VTerminal.TileGridTest;

import com.valkryst.VTerminal.Tile;
import org.junit.Assert;
import org.junit.Test;

public class GetRectangularSubsetTest {
    @Test
    public void testGetRectangularSubset_withValidParams() {
        final Tile[][] rectSubset = StaticGrid.TILE_GRID.getRectangularSubset(0, 0, 2, 2);
        Assert.assertEquals(2, rectSubset.length);
        Assert.assertEquals(2, rectSubset[0].length);
        Assert.assertEquals(2, rectSubset[1].length);
        Assert.assertEquals(rectSubset[0][0].getCharacter(), 'A');
        Assert.assertEquals(rectSubset[0][1].getCharacter(), 'B');
        Assert.assertEquals(rectSubset[1][0].getCharacter(), 'A');
        Assert.assertEquals(rectSubset[1][1].getCharacter(), 'B');
    }

    @Test
    public void testGetRectangularSubset_withNegativeStartRow() {
        final Tile[][] rectSubset = StaticGrid.TILE_GRID.getRectangularSubset(-1, 0, 2, 2);
        Assert.assertEquals(1, rectSubset.length);
        Assert.assertEquals(2, rectSubset[0].length);
        Assert.assertEquals(rectSubset[0][0].getCharacter(), 'A');
        Assert.assertEquals(rectSubset[0][1].getCharacter(), 'B');
    }

    @Test
    public void testGetRectangularSubset_withNegativeStartColumn() {
        final Tile[][] rectSubset = StaticGrid.TILE_GRID.getRectangularSubset(0, -1, 2, 2);
        Assert.assertEquals(2, rectSubset.length);
        Assert.assertEquals(1, rectSubset[0].length);
        Assert.assertEquals(1, rectSubset[1].length);
        Assert.assertEquals(rectSubset[0][0].getCharacter(), 'A');
        Assert.assertEquals(rectSubset[1][0].getCharacter(), 'A');
    }

    @Test
    public void testGetRectangularSubset_withStartRowExceedingGridHeight() {
        final Tile[][] rectSubset = StaticGrid.TILE_GRID.getRectangularSubset(100, 0, 2, 2);
        Assert.assertEquals(0, rectSubset.length);
    }

    @Test
    public void testGetRectangularSubset_withStartColumnExceedingGridHeight() {
        final Tile[][] rectSubset = StaticGrid.TILE_GRID.getRectangularSubset(0, 100, 2, 2);
        Assert.assertEquals(0, rectSubset.length);
    }

    @Test
    public void testGetRectangularSubset_withEndRowExceedingGridHeight() {
        final Tile[][] rectSubset = StaticGrid.TILE_GRID.getRectangularSubset(5, 5, 1, 2);
        Assert.assertEquals(1, rectSubset.length);
        Assert.assertEquals(1, rectSubset[0].length);
        Assert.assertEquals(rectSubset[0][0].getCharacter(), 'F');
    }

    @Test
    public void testGetRectangularSubset_withEndRowExceedingGridWidth() {
        final Tile[][] rectSubset = StaticGrid.TILE_GRID.getRectangularSubset(5, 5, 2, 1);
        Assert.assertEquals(1, rectSubset.length);
        Assert.assertEquals(1, rectSubset[0].length);
        Assert.assertEquals(rectSubset[0][0].getCharacter(), 'F');
    }
}
