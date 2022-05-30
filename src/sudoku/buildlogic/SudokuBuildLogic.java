package src.sudoku.buildlogic;

import java.io.IOException;

import src.sudoku.computationlogic.GameLogic;
import src.sudoku.persistence.LocalStorageImpl;
import src.sudoku.problemdomain.IStorage;
import src.sudoku.problemdomain.SudokuGame;
import src.sudoku.userinterface.IUserInterfaceContract;
import src.sudoku.userinterface.logic.ControlLogic;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
