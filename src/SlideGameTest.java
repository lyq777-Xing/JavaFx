import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


/**
 * @author lyq
 * @time 2023/7/29 1:05
 */
public class SlideGameTest {
    SlideGame slideGame = new SlideGame();

    @Test
    public void isSlide(){
//        Create a new panel
        JFXPanel fxPanel = new JFXPanel();
//        Place the code in Platform. runLater() to run on JavaFX application threads
        Platform.runLater(() -> {
            slideGame.start(new Stage());
            Assert.assertTrue("Determine if the palace grid can still move",slideGame.isSlide());
        });
    }

    @Test
    public void isFull(){
        Assert.assertFalse("Determine if the palace grid is full",slideGame.isFull());
    }

    @Test
    public void isLeft(){
        JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(() -> {
            slideGame.start(new Stage());
            Assert.assertTrue("Determine if it is possible to move left",slideGame.isLeft());
        });

    }

    @Test
    public void isRight(){
        JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(() -> {
            slideGame.start(new Stage());
            Assert.assertTrue("Determine if it is possible to move left",slideGame.isRight());
        });

    }

    @Test
    public void isDown(){
        JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(() -> {
            slideGame.start(new Stage());
            Assert.assertTrue("Determine if it is possible to move left",slideGame.isDown());
        });

    }

    @Test
    public void isUp(){
        JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(() -> {
            slideGame.start(new Stage());
            Assert.assertTrue("Determine if it is possible to move left",slideGame.isUp());
        });

    }

}
