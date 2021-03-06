package com.valkryst.VTerminal.TileGridTest;

import com.valkryst.VTerminal.Tile;
import org.junit.Assert;
import org.junit.Test;

public class GetRowSubsetTest {
    @Test
    public void testGetRowSubset_withValidParams() {
        final Tile[] rowSubset = StaticGrid.TILE_GRID.getRowSubset(0, 1, 4);
        Assert.assertEquals(rowSubset.length, 4);
        Assert.assertEquals(rowSubset[0].getCharacter(), 'B');
        Assert.assertEquals(rowSubset[1].getCharacter(), 'C');
        Assert.assertEquals(rowSubset[2].getCharacter(), 'D');
        Assert.assertEquals(rowSubset[3].getCharacter(), 'E');
    }

    @Test
    public void testGetRowSubset_withLengthExceedingGridWidth() {
        int gridWidth = StaticGrid.TILE_GRID.getWidth() + 10;
        final Tile[] rowSubset = StaticGrid.TILE_GRID.getRowSubset(0, 0, gridWidth);
        Assert.assertEquals(rowSubset.length, 6);
        Assert.assertEquals(rowSubset[0].getCharacter(), 'A');
        Assert.assertEquals(rowSubset[1].getCharacter(), 'B');
        Assert.assertEquals(rowSubset[2].getCharacter(), 'C');
        Assert.assertEquals(rowSubset[3].getCharacter(), 'D');
        Assert.assertEquals(rowSubset[4].getCharacter(), 'E');
        Assert.assertEquals(rowSubset[5].getCharacter(), 'F');
    }

    @Test
    public void testGetRowSubset_withRowIndexLessThanZero() {
        final Tile[] rowSubset = StaticGrid.TILE_GRID.getRowSubset(-1, 1, 4);
        Assert.assertEquals(rowSubset.length, 0);
    }

    @Test
    public void testGetRowSubset_withColumnIndexLessThanZero() {
        final Tile[] rowSubset = StaticGrid.TILE_GRID.getRowSubset(0, -1, 4);
        Assert.assertEquals(rowSubset.length, 0);
    }

    @Test
    public void testGetRowSubset_withLengthLessThanOne() {
        final Tile[] rowSubset = StaticGrid.TILE_GRID.getRowSubset(0, 1, 0);
        Assert.assertEquals(rowSubset.length, 0);
    }

    @Test
    public void testGetRowSubset_withRowIndexExceedingGridHeight() {
        int gridHeight = StaticGrid.TILE_GRID.getHeight() + 1;
        final Tile[] rowSubset = StaticGrid.TILE_GRID.getRowSubset(gridHeight, 1, 0);
        Assert.assertEquals(rowSubset.length, 0);
    }

    @Test
    public void testGetRowSubset_withColumnIndexExceedingGridWidth() {
        int gridWidth = StaticGrid.TILE_GRID.getWidth() + 1;
        final Tile[] rowSubset = StaticGrid.TILE_GRID.getRowSubset(0, gridWidth, 0);
        Assert.assertEquals(rowSubset.length, 0);
    }
}
