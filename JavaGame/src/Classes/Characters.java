import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Class is needed to create characters on the playing board
 */
public class Characters extends Pane{
    boolean SpecialReception;
    ImageView imgview;
    int count = 4;
    int columns = 4;
    int offsetX = 0;
    int offsetY = 0;
    int width = 45;
    int height = 64;
    int score = 0;
    SpriteAnimation animation;
    Bonuse DellBonuse = null;

    /**
     * Characters class constructor
     * @param imgview sprite image of the character
     */
    public Characters(ImageView imgview) {
        this.SpecialReception = false;
        this.imgview = imgview;
        this.imgview.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(Duration.millis(500),imgview, count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imgview);
    }

    /**
     * The method is necessary to move the character on the playing field along the coordinate X
     * @param x Indent from current coordinate X
     */
    public void moveX(int x) {
        boolean right = x > 0 ? true : false;
        for(int i = 0; i < Math.abs(x); i++) {
            if(this.getTranslateX() > 570)
                this.setTranslateX(this.getTranslateX() - 1);
            if(this.getTranslateX() < -5)
                this.setTranslateX(this.getTranslateX() + 1);
            if(right)
                this.setTranslateX(this.getTranslateX() + 1);
            else
                this.setTranslateX(this.getTranslateX() - 1);
            isBonuseEat();
        }
    }

    /**
     * The method is necessary to move the character on the playing field along the coordinate Y
     * @param y Indent from current coordinate Y
     */
    public void moveY(int y) {
        boolean down = y > 0 ? true : false;
        for(int i = 0; i < Math.abs(y); i++) {
            if(this.getTranslateY() > 350)
                this.setTranslateY(this.getTranslateY() - 1);
            if(this.getTranslateY() < 30)
                this.setTranslateY(this.getTranslateY() + 1);
            if(down)
                this.setTranslateY(this.getTranslateY() + 1);
            else
                this.setTranslateY(this.getTranslateY() - 1);
            isBonuseEat();
        }
    }

    /**
     * The method is necessary for checking the match of the character's coordinates and the bonus
     */
    public void isBonuseEat() {
        GameProcess.bonuses.forEach((bonuse -> {
            if (this.getBoundsInParent().intersects(bonuse.getBoundsInParent())) {
                this.DellBonuse = bonuse;
                if (this.DellBonuse.GetKindOfBonuse() == 3)
                    SetSpecialReception(true);
                else
                    score += this.DellBonuse.GetKindOfBonuse();
            }
        }));
        GameProcess.bonuses.remove(this.DellBonuse);
        GameProcess.root.getChildren().remove(this.DellBonuse);
    }

    /**
     * The method for installing a special reception after collecting special bonuses
     * @param SpecialReception Boolean variable of the possibility of special reception
     */
    public void SetSpecialReception (boolean SpecialReception) {
        this.SpecialReception = SpecialReception;
    }

    /**
     * The method is necessary for checking for the possibility of using a special reception
     * @return Boolean variable of the possibility of special reception
     */
    public boolean GetSpecialReception () {
        return this.SpecialReception;
    }

    /**
     * The method is required to install a game score
     * @param OffsetScore Change of the game score in relation to the current
     */
    public void SetScore(int OffsetScore) {
        this.score += OffsetScore;
    }
}