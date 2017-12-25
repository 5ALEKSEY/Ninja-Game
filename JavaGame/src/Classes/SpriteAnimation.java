package Classes;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition{
    private final ImageView img;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;
    SpriteAnimation(Duration curation, ImageView img, int count, int colunms, int offsetX, int offsetY, int width, int height) {
        this.img = img;
        this.columns = colunms;
        this.count = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(curation);
        setInterpolator(Interpolator.LINEAR);
    }
    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int)Math.floor(frac * count), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        img.setViewport(new Rectangle2D(x, y, width, height));
    }
    public void setOffsetY(int y) {
        this.offsetY = y;
    }
    public void setOffsetX(int x) {
        this.offsetX = x;
    }
}
