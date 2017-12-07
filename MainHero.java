package runnerk;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MainHero extends Pane {

    private final int frameLines = 2;
    private final int frameColumns = 4;
    private final int width = 128;
    private final int height = 102;
    private final int drawingPoint = 50;
    private final Image heroImage = new Image
        ( getClass().getResourceAsStream( "textures/MainHero.png" ));
    private final ImageView heroView = new ImageView( heroImage );

    private int jumpHeight;
    private int line;
    private boolean isAlive;
    private Animation drawer;
    private Animation attackDrawer;
    private Animation deathDrawer;

    public MainHero() {
        setSize();
        jumpHeight = 560;
        line = 0;
        isAlive = true;
        heroView.setFitHeight( height * 1.3 );
        heroView.setFitWidth( width * 1.3 );
        heroView.setLayoutX ( drawingPoint );
        heroView.setLayoutY ( jumpHeight );
        drawer = new Drawer( heroView, Duration.INDEFINITE,
            frameLines, frameColumns, 0, height, width, height, 1 );
        drawer.setCycleCount( Animation.INDEFINITE );
        drawer.play();
        RunnerK.root.getChildren().add ( heroView );
    }

    public void run() {
        drawer.stop();
        drawer = new Drawer( heroView, Duration.millis( 400 ),
            frameLines, frameColumns, 0, 0, width, height, 1 );
        drawer.setCycleCount( Animation.INDEFINITE );
        drawer.play();
    }

    public int death( int deathFramesCount ) {
        if ( deathFramesCount == 0 ) {
            deathDrawer = new Drawer ( heroView, Duration.INDEFINITE, 1, 1, width,
            height * 3, width, height, 1 );
            deathDrawer.setCycleCount( Animation.INDEFINITE );
            deathDrawer.play();
        } else if ( deathFramesCount == 11 ) {
            deathDrawer.stop();
            deathDrawer = new Drawer ( heroView, Duration.INDEFINITE, 1, 1, 0,
            height * 3, width, height, 1 );
            deathDrawer.setCycleCount( Animation.INDEFINITE );
            deathDrawer.play();
            heroView.setLayoutY( jumpHeight + 10 );
            return 11;
        }
        return ++deathFramesCount;
    }

    public int attack( int attackFramesCount ) {
        switch ( attackFramesCount ) {
            case 0:
                drawer.stop ();
                drawer = null;
                attackDrawer = new Drawer( heroView, Duration.INDEFINITE, 1, 1, 0,
                    height * 2 , width, height, 1 );
                attackDrawer.setCycleCount ( Animation.INDEFINITE );
                attackDrawer.play();
                break;
            case 12:
                attackDrawer.stop();
                attackDrawer = new Drawer( heroView, Duration.INDEFINITE, 1, 1, width,
                    height * 2 , width, height, 1 );
                attackDrawer.setCycleCount ( Animation.INDEFINITE );
                attackDrawer.play();
                break;
            case 24:
                attackDrawer.stop();
                attackDrawer = new Drawer( heroView, Duration.INDEFINITE, 1, 1, 0,
                    height * 2 , width, height, 1 );
                attackDrawer.setCycleCount ( Animation.INDEFINITE );
                attackDrawer.play();
                break;
            case 36:
                attackDrawer.stop();
                attackDrawer = null;
                drawer = new Drawer( heroView, Duration.millis( 400 ),
                    frameLines, frameColumns, 0, 0, width, height, 1 );
                drawer.setCycleCount( Animation.INDEFINITE );
                drawer.play ();
                break;
            default:
                break;
        }
        return ++attackFramesCount;
    }

    public int jump( int jumpFramesCount, int fallState ) {
        switch ( fallState ) {
            case 1:
                jumpHeight += jumpFramesCount - 19;
                break;
            case 0:
                jumpHeight -= 20 - jumpFramesCount;
                break;
            default:
                break;
        }
        heroView.setLayoutY ( jumpHeight );
        return ++jumpFramesCount;
    }

    public int fall ( int jumpFramesCount ) {
        jumpHeight += jumpFramesCount;
        heroView.setLayoutY ( jumpHeight );
        return ++jumpFramesCount;
    }

    public int getLine() {
        return line;
    }

    public void setLine( int line ) {
        this.line = line;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setIsAlive( boolean isAlive ) {
        this.isAlive = isAlive;
    }

    public void setLayout( int layout ) {
        heroView.setLayoutY( layout );
        jumpHeight = layout;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int getDrawingPoint() {
        return drawingPoint;
    }

    private void setSize() {
        setWidth( width );
        setHeight( height );
    }
}