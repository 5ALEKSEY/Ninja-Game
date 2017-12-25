package Classes;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bonuse extends Rectangle {
    public static final int BonuseSize = 25;
    private int OffsetX;
    private int OffsetY;
    private DropShadow Shadow;
    private RotateTransition RotTransition;
    private int KindOfBonuse;
    Image HighBonuseImage = new Image(getClass().getResourceAsStream("Pictires/highbonus.png"));
    Image BonuseImage = new Image(getClass().getResourceAsStream("Pictires/bonus.png"));
    Image SpecBonuseImage = new Image(getClass().getResourceAsStream("Pictires/specbonus.png"));

    public Bonuse (int OffsetX, int OffsetY, int KindOfBonuse) {
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

    public int GetKindOfBonuse () {
        return this.KindOfBonuse;
    }

    public void CreateBonuse () {
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

    public void CreateHighBonuse () {
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

    public void CreateSpecBonuse () {
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
