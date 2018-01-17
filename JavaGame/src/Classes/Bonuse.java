import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This class is necessary to create a bonus on the game board
 */
public class Bonuse extends Rectangle {
    public static final int BonuseSize = 25;
    private int OffsetX;
    private int OffsetY;
    private DropShadow Shadow;
    private RotateTransition RotTransition;
    private int KindOfBonuse;
    Image HighBonuseImage = new Image(getClass().getResourceAsStream("highbonus.png"));
    Image BonuseImage = new Image(getClass().getResourceAsStream("bonus.png"));
    Image SpecBonuseImage = new Image(getClass().getResourceAsStream("specbonus.png"));

    /**
     * Bonuse class constructor
     * @param OffsetX Coordinate X on the playing field
     * @param OffsetY Coordinate Y on the playing field
     * @param KindOfBonuse Bonus type (1 or 2 points)
     */
    public Bonuse(int OffsetX, int OffsetY, int KindOfBonuse) {
        this.OffsetX = OffsetX;
        this.OffsetY = OffsetY;
        this.KindOfBonuse = KindOfBonuse;
        if (KindOfBonuse == 1)
            CreateBonuse();
        else if (KindOfBonuse == 2)
            CreateHighBonuse();
        else
            CreateSpecBonuse();
    }

    /**
     * The method is necessary for determining the type of bonus
     * @return Kind of bonus
     */
    public int GetKindOfBonuse() {
        return this.KindOfBonuse;
    }

    /**
     * The method creates a simple bonus
     */
    public void CreateBonuse() {
        setWidth(BonuseSize);
        setHeight(BonuseSize);
        setFill(new ImagePattern(BonuseImage));
        setTranslateX(this.OffsetX);
        setTranslateY(this.OffsetY);
        this.RotTransition = new RotateTransition(Duration.seconds(1), this);
        this.RotTransition.setByAngle(1000);
        this.RotTransition.setAutoReverse(true);
        this.RotTransition.setCycleCount(Animation.INDEFINITE);
        this.RotTransition.play();
        this.Shadow = new DropShadow(1, Color.RED);
        setEffect(this.Shadow);
    }

    /**
     * The method creates a high bonus
     */
    public void CreateHighBonuse() {
        setWidth(BonuseSize);
        setHeight(BonuseSize);
        setFill(new ImagePattern(HighBonuseImage));
        setTranslateX(this.OffsetX);
        setTranslateY(this.OffsetY);
        this.RotTransition = new RotateTransition(Duration.seconds(3), this);
        this.RotTransition.setByAngle(150);
        this.RotTransition.setAutoReverse(true);
        this.RotTransition.setCycleCount(Animation.INDEFINITE);
        this.RotTransition.play();
        this.Shadow = new DropShadow(1, Color.GOLD);
        setEffect(this.Shadow);
    }

    /**
     * The method creates a special bonus
     */
    public void CreateSpecBonuse() {
        setWidth(BonuseSize);
        setHeight(BonuseSize);
        setFill(new ImagePattern(SpecBonuseImage));
        setTranslateX(this.OffsetX);
        setTranslateY(this.OffsetY);
        this.RotTransition = new RotateTransition(Duration.seconds(3), this);
        this.RotTransition.setByAngle(150);
        this.RotTransition.setAutoReverse(true);
        this.RotTransition.setCycleCount(Animation.INDEFINITE);
        this.RotTransition.play();
        this.Shadow = new DropShadow(1, Color.RED);
        setEffect(this.Shadow);
    }
}