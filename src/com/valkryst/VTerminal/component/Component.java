package com.valkryst.VTerminal.component;

import com.valkryst.VTerminal.Tile;
import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.TileGrid;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Collections;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

public class Component {
    /** The tiles. */
    @Getter protected final TileGrid tiles;

    /** The event listeners. */
    protected final List<EventListener> eventListeners = new LinkedList<>();

    /** The function used to redraw the parent of the component. */
    @Setter @NonNull protected Runnable redrawFunction = () -> {};

    /**
     * Constructs a new Component.
     *
     * @param width
     *          The width.
     *
     * @param height
     *          The height.
     *
     * @param x
     *          The x-axis position of the component within it's parent.
     *
     * @param y
     *          The y-axis position of the component within it's parent.
     */
    public Component(final int width, final int height, final int x, final int y) {
        this(new Dimension(width, height), new Point(x, y));
    }

    /**
     * Constructs a new Component.
     *
     * @param dimensions
     *          The dimensions of the component
     *
     * @param position
     *          The position of the component within it's parent.
     *
     * @throws NullPointerException
     *         If the dimensions or point is null.
     */
    public Component(final @NonNull Dimension dimensions, final @NonNull Point position) {
        tiles = new TileGrid(dimensions, position);
    }

    /**
     * Creates the event listeners.
     *
     * @param parentScreen
     *          The parent screen.
     *
     * @throws NullPointerException
     *         If the screen is null.
     */
    public void createEventListeners(final @NonNull Screen parentScreen) {}

    /**
     * Draws the component onto a tile grid.
     *
     * @param grid
     *          The grid.
     */
    public void draw(final TileGrid grid) {
        if (grid == null) {
            return;
        }

        final int xOffset = tiles.getXPosition();
        final int yOffset = tiles.getYPosition();

        for (int y = 0 ; y < tiles.getHeight() ; y++) {
            final int yPosition = yOffset + y;

            for (int x = 0 ; x < tiles.getWidth() ; x++) {
                final int xPosition = xOffset + x;

                final Tile componentTile = tiles.getTileAt(x, y);
                final Tile screenTile = grid.getTileAt(xPosition, yPosition);

                if (componentTile != null && screenTile != null) {
                    componentTile.updateCacheHash();
                    screenTile.copy(componentTile);
                }
            }
        }
    }

    /**
     * Determines whether or not a point intersects this component.
     *
     * @param point
     *          The tile-based point position.
     *
     * @return
     *          Whether or not the point intersects this component.
     */
    protected boolean intersects(final Point point) {
        if (point == null) {
            return false;
        }

        boolean intersects = point.x >= tiles.getXPosition();
        intersects &= point.x < (tiles.getWidth() + tiles.getXPosition());
        intersects &= point.y >= tiles.getYPosition();
        intersects &= point.y < (tiles.getHeight() + tiles.getYPosition());
        return intersects;
    }

    /**
     * Retrieves an unmodifiable version of the event listeners list.
     *
     * @return
     *          An unmodifiable version of the event listeners list.
     */
    public List<EventListener> getEventListeners() {
        return Collections.unmodifiableList(eventListeners);
    }

    /**
     * Retrieves a tile from the screen.
     *
     * @param x
     *          The x-axis coordinate of the tile to retrieve.
     *
     * @param y
     *          The y-axis coordinate of the tile to retrieve.
     *
     * @return
     *          The tile, or null if the coordinates are outside the bounds
     *          of the component.
     */
    public Tile getTileAt(final int x, final int y) {
        return tiles.getTileAt(x, y);
    }

    /**
     * Retrieves a tile from the screen.
     *
     * @param position
     *          The x/y-axis coordinates of the tile to retrieve.
     *
     * @return
     *          The tile, or null if the coordinates are outside the bounds
     *          of the component.
     */
    public Tile getTileAt(final Point position) {
        return tiles.getTileAt(position);
    }
}
