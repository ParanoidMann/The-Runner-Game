package runnerk;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class JumperEnemy extends Opponent {

    public JumperEnemy( int line ) {
        enemyImage = new Image
        ( getClass().getResourceAsStream( "textures/JumperEnemy.png" ));
        enemyView = new ImageView( enemyImage );
        frameLines = 3;
        frameColumns = 3;
        width = 138;
        height = 128;
        initSize();
        this.line = line;
        enemyView.setFitHeight( height );
        enemyView.setFitWidth( width );
        drawer = new Drawer( enemyView, Duration.millis( 550 ),
            frameColumns, frameLines, 0, 0, width, height, 0 );
        drawer.setCycleCount( Animation.INDEFINITE );
        drawer.play();
        RunnerK.root.getChildren().add ( enemyView );

        switch ( line ) {
            case 0: layout =  680 - height;
                break;
            case 1: layout =  510 - height;
                break;
            case 2: layout =  340 - height;
                break;
            case 3: layout =  170 - height;
        }
        enemyOffset = 1280 + ( width * 2 );
        enemyView.setLayoutY ( layout );
        enemyView.setLayoutX ( enemyOffset );
    }

    private void initSize() {
        setSize();
    }

    public void clear() {
        drawer.stop ();
        RunnerK.root.getChildren().remove( enemyView );
        enemyView = null;
        enemyImage = null;
        drawer = null;
    }
}