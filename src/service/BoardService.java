package src.service;

import java.util.List;

import src.model.Board;
import src.model.Cell;

public class BoardService {
    
    public void printBoard(Board board) {
        List<List<Cell>> cells = board.getCells();
        for(int i = 0; i < board.getSize(); i++) {
            List<Cell> row = cells.get(i);

            for(Cell cell : row) {
                if(cell.getPlayer() == null) {
                    System.out.print("|-| ");    
                } else {
                    System.out.print("|" + cell.getPlayer().getSymbol() + "| ");
                }
            }
            System.out.println();
        }
    }
}
