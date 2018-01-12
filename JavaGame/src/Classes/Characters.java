import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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
    public Characters(ImageView imgview) {
        this.SpecialReception = false;
        this.imgview = imgview;
        this.imgview.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(Duration.millis(500),imgview, count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(imgview);
    }
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

    public void SetSpecialReception (boolean SpecialReception) {
        this.SpecialReception = SpecialReception;
    }

    public boolean GetSpecialReception () {
        return this.SpecialReception;
    }

    public void SetScore(int OffsetScore) {
        this.score += OffsetScore;
    }
}