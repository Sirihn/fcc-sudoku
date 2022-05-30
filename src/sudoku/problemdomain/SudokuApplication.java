package src.sudoku.problemdomain;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import src.sudoku.buildlogic.SudokuBuildLogic;
import src.sudoku.userinterface.IUserInterfaceContract;
import src.sudoku.userinterface.UserInterfaceImpl;

public class SudokuApplication extends Application {
    private IUserInterfaceContract.View uiImpl;

    @Override
    public void start(Stage primaryStage) throws Exception {
        uiImpl = new UserInterfaceImpl(primaryStage);
        try {
            SudokuBuildLogic.build(uiImpl);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
