import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;

public class SlideGame extends Application {

    //stores the array of buttons that represents the screen
    private Button[][] buttons;
    private int[][] board;
    private Random random;

    // Constructor
    public SlideGame() {
        this.buttons = new Button[4][4];
        this.board = new int[4][4];
        this.random = new Random();
    }


    public void start(Stage primaryStage) {
//        Set Title
        primaryStage.setTitle("2048Game");

//        Grid Layout
        GridPane grid = new GridPane();

        //initializes every button as a blank button
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new Button("  ");
//                Button click event
                buttons[i][j].setOnAction(new SlideEventHandler());
//                Set button size
                buttons[i][j].setMinSize(100,100);
                buttons[i][j].setStyle("-fx-background-color: white;-fx-border-color: Gainsboro");
//                Add a button in row j and column i
                grid.add(buttons[i][j], j, i);
            }
        }

        //Randomly generate two numbers less than 4 to determine the button for randomly generating 1
        int x = random.nextInt(4);
        int y = random.nextInt(4);
        board[x][y] = 1;
        buttons[x][y].setText("1");

        //new scene for the grid And set the size
        Scene scene = new Scene(grid,400,400);
//        Add the scene to the stage
        primaryStage.setScene(scene);
//        Display stage
        primaryStage.show();
    }

    private class SlideEventHandler implements EventHandler<ActionEvent> {
        Alert alert = new Alert(Alert.AlertType.NONE);

        @Override
        public void handle(ActionEvent event) {
            Button btn = (Button) event.getSource();
            int row = GridPane.getRowIndex(btn);
            int col = GridPane.getColumnIndex(btn);
//            First, determine whether the palace grid is full. If it is full, there is a possibility of movement. If there is no possibility of movement, the game ends
            if(isFull()){
                if(isSlide()){
                    if (col == 0 && (row == 1 || row == 2)) {
                        if(isLeft()){
                            Left();
                            updateButtons();
                            insertRandomTile();
                        }
                        else {
                            alert.setContentText("There's still a chance, come on!");
                            alert.show();
                        }
                    }
                    else if (col == 3 && (row == 1 || row == 2)) {
                        if(isRight()){
                            Right();
                            updateButtons();
                            insertRandomTile();
                        }
                        else {
                            alert.setContentText("There's still a chance, come on!");
                            alert.show();
                        }
                    }

                    else if (row == 0 && (col == 1 || col == 2)) {
                        if(isUp()){
                            Up();
                            updateButtons();
                            insertRandomTile();
                        }
                        else {
                            alert.setContentText("There's still a chance, come on!");
                            alert.show();
                        }
                    }
                    else if (row == 3 && (col == 1 || col == 2)) {
                        if(isDown()){
                            Down();
                            updateButtons();
                            insertRandomTile();
                        }
                        else{
                            alert.setContentText("There's still a chance, come on!");
                            alert.show();
                        }
                    }
                    else {
                        alert.setContentText("There's still a chance, come on!");
                        alert.show();
                    }
                }
                else {
                    alert.setContentText("Game over！");
                    alert.show();
//                    System.out.println("Game over");
                }
            }else {
//              If the two buttons in the middle of the first row are clicked, the event of sliding up is called
                if (row == 0 && (col == 1 || col == 2)) {
                    Up();
                }
//              If the two buttons in the middle of the third row are clicked, the event of sliding down is called
                else if (row == 3 && (col == 1 || col == 2)) {
                    Down();
                }
//              If the two buttons in the middle of the left side are clicked, the event of sliding left is called
                else if (col == 0 && (row == 1 || row == 2)) {
                    Left();
                }
//              If the two buttons in the middle on the right are clicked, the event of sliding to the right is called
                else if (col == 3 && (row == 1 || row == 2)) {
                    Right();
                }
//               Update the button labels and add a new tile
                updateButtons();
                insertRandomTile();
            }


        }

        /**
         * SlideLeft
         */
        private void Left() {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
//                    If the button is not equal to 0
                    if (board[i][j] != 0) {
                        int k = j - 1;
//                        Board [i] [k] is the button to the left of the looped button
                        while (k >= 0 && board[i][k] == 0) {
                            k--;
                        }
//                        If there is a button to the left of the looped button that is not equal to 0 and is equal to the looped button, merge it; otherwise, move it
                        if (k >= 0 && board[i][k] == board[i][j]) {
                            // Merge the tiles
                            board[i][k]++;
                            board[i][j] = 0;
                        } else {
                            // Move the tile
                            board[i][k + 1] = board[i][j];
                            if (k + 1 != j) {
                                board[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        /**
         * SlideRight
         */
        private void Right() {
            // Implement slide and merge logic for right direction
            for (int i = 0; i < 4; i++) {
                for (int j = 2; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        int k = j + 1;
                        while (k < 4 && board[i][k] == 0) {
                            k++;
                        }
                        if (k < 4 && board[i][k] == board[i][j]) {
                            // Merge the tiles
                            board[i][k]++;
                            board[i][j] = 0;
                        } 
                        else {
                            // Move the tile
                            board[i][k - 1] = board[i][j];
                            if (k - 1 != j) {
                                board[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        /**
         * SlideUp
         */
        private void Up() {
            // Implement slide and merge logic for up direction
            for (int j = 0; j < 4; j++) {
                for (int i = 1; i < 4; i++) {
                    if (board[i][j] != 0) {
                        int k = i - 1;
                        while (k >= 0 && board[k][j] == 0) {
                            k--;
                        }
                        if (k >= 0 && board[k][j] == board[i][j]) {
                            // Merge the tiles
                            board[k][j]++;
                            board[i][j] = 0;
                        } 
                        else {
                            // Move the tile
                            board[k + 1][j] = board[i][j];
                            if (k + 1 != i) {
                                board[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        /**
         * SlideDown
         */
        private void Down() {
            // Implement slide and merge logic for down direction
            for (int j = 0; j < 4; j++) {
                for (int i = 2; i >= 0; i--) {
                    if (board[i][j] != 0) {
                        int k = i + 1;
                        while (k < 4 && board[k][j] == 0) {
                            k++;
                        }
                        if (k < 4 && board[k][j] == board[i][j]) {
                            // Merge the tiles
                            board[k][j]++;
                            board[i][j] = 0;
                        }
                        else {
                            // Move the tile
                            board[k - 1][j] = board[i][j];
                            if (k - 1 != i) {
                                board[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * updateButtons
     */
    private void updateButtons() {
        // Update the button labels to reflect the board values
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    buttons[i][j].setText("  ");
                    buttons[i][j].setStyle("-fx-background-color: white;-fx-border-color: Gainsboro");
                }
                else {
                    buttons[i][j].setText(Integer.toString((int) Math.pow(2, board[i][j])));
                    if(buttons[i][j].getText().equals("1")){
                        buttons[i][j].setStyle("-fx-background-color: OldLace;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("2")){
                        buttons[i][j].setStyle("-fx-background-color: Cornsilk;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("4")){
                        buttons[i][j].setStyle("-fx-background-color: Bisque;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("8")){
                        buttons[i][j].setStyle("-fx-background-color: PeachPuff;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("16")){
                        buttons[i][j].setStyle("-fx-background-color: Burlywood;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("32")){
                        buttons[i][j].setStyle("-fx-background-color: SandyBrown;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("64")){
                        buttons[i][j].setStyle("-fx-background-color: Tan;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("128")){
                        buttons[i][j].setStyle("-fx-background-color: DarkSalmon;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("256")){
                        buttons[i][j].setStyle("-fx-background-color: DarkOrange;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("512")){
                        buttons[i][j].setStyle("-fx-background-color: LightSteelBlue;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("1024")){
                        buttons[i][j].setStyle("-fx-background-color: LightSkyBlue;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("2048")){
                        buttons[i][j].setStyle("-fx-background-color: DeepSkyBlue;-fx-border-color: Gainsboro");
                    }
                    else if(buttons[i][j].getText().equals("4096")){
                        buttons[i][j].setStyle("-fx-background-color: SteelBlue;-fx-border-color: Gainsboro");
                    }
                    else {
                        buttons[i][j].setStyle("-fx-background-color: IndianRed;-fx-border-color: Gainsboro");

                    }
                }
            }
        }
    }

    /**
     * Set a random button to 1
     */
    public void insertRandomTile() {
        // Insert a new 1 tile to a random blank cell on the board
        while (true) {
            int x = random.nextInt(4);
            int y = random.nextInt(4);
//            If the randomly selected button is empty, it will be converted to 1. If it is not 0, it will continue to be randomly selected
            if (board[x][y] == 0) {
                board[x][y] = 1;
                buttons[x][y].setText("1");
                buttons[x][y].setStyle("-fx-background-color: OldLace;-fx-border-color: Gainsboro");
                break;
            }
        }
    }

    /**
     * Determine if the palace grid is full
     * @return
     */
    public boolean isFull(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determine if there is still a chance to move
     * @return
     */
    public boolean isSlide(){
        if(isLeft()){
            return true;
        }
        if(isRight()){
            return true;
        }
        if (isUp()){
            return true;
        }
        if (isDown()){
            return true;
        }
        return false;
    }

    /**
     * Determine if it is possible to move left
     * @return
     */
    public boolean isLeft(){
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (board[i][j] != 0) {
                    int k = j - 1;
                    while (k >= 0 && board[i][k] == 0) {
                        k--;
                    }
                    if (k >= 0 && board[i][k] == board[i][j]) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determine if it is possible to move to the right
     * @return
     */
    public boolean isRight(){
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int k = j + 1;
                    while (k < 4 && board[i][k] == 0) {
                        k++;
                    }
                    if (k < 4 && board[i][k] == board[i][j]) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determine if it is possible to move up
     * @return
     */
    public boolean isUp(){
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if (board[i][j] != 0) {
                    int k = i - 1;
                    while (k >= 0 && board[k][j] == 0) {
                        k--;
                    }
                    if (k >= 0 && board[k][j] == board[i][j]) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determine if it is possible to move down
     * @return
     */
    public boolean isDown(){
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if (board[i][j] != 0) {
                    int k = i + 1;
                    while (k < 4 && board[k][j] == 0) {
                        k++;
                    }
                    if (k < 4 && board[k][j] == board[i][j]) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        SlideGame.launch(args);
    }
}
