package com.valkryst.VTerminal.TileGridTest;

import com.valkryst.VTerminal.Tile;
import org.junit.Assert;
import org.junit.Test;

public class GetHeightTest {
    @Test
    public void testGetHeight_ensureValueIsAsExpected() {
        final int length = StaticGrid.TILE_GRID.getColumn(0).length;
        Assert.assertEquals(6, length);
    }

    @Test
    public void testGetHeight_ensureAllColumnsAreOfEqualLength() {
        final int length = StaticGrid.TILE_GRID.getColumn(0).length;

        for (int x = 0 ; x < StaticGrid.TILE_GRID.getHeight() ; x++) {
            final Tile[] column = StaticGrid.TILE_GRID.getColumn(x);
            Assert.assertEquals(column.length, length);
        }
    }
}
