package runnerk;

import javafx.scene.image.ImageView;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class Drawer extends Transition {

    private final ImageView imageView;
    private final int columns;
    private final int lines;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;
    private final int minus;

    public Drawer( ImageView imageView,
            Duration duration,
            int lines, int columns,
            int offsetX, int offsetY,
            int width, int height,
            int minus ) {

        this.imageView = imageView;
        this.columns = columns;
        this.lines = lines;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.minus = minus;

        setCycleDuration( duration );
    }

    @Override
    protected void interpolate( double frac ) {
        int index = Math.min(( int ) Math.floor( frac * lines ), lines - minus );
        int x = ( index % columns ) * width + offsetX;
        int y = ( index / columns ) * height + offsetY;

        imageView.setViewport( new Rectangle2D( x, y, width, height ));
    }
}