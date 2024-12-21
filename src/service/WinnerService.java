package src.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.exception.GameDrawnException;
import src.model.Board;
import src.model.Cell;
import src.model.Move;
import src.model.Player;

public class WinnerService {

    List<HashMap<Character, Integer>> rowsMapList;
    List<HashMap<Character, Integer>> colsMapList;
    HashMap<Character, Integer> cornersMap;
    HashMap<Character, Integer> topRightDiagonalMap;
    HashMap<Character, Integer> topLeftDiagonalMap;

    static int invalidMapCount = 0;

    public WinnerService(int size) {
        cornersMap = new HashMap<>();
        topLeftDiagonalMap = new HashMap<>();
        topRightDiagonalMap = new HashMap<>();
        rowsMapList = new ArrayList<>();
        colsMapList = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            rowsMapList.add(new HashMap<>());
            colsMapList.add(new HashMap<>());
        }
    }

    public Player checkWinner(Board board, Move currentMove) {
        char symbol = currentMove.getPlayer().getSymbol();
        Cell currentCell = currentMove.getCell();
        HashMap<Character, Integer> rowMap = rowsMapList.get(currentCell.getRow());
        HashMap<Character, Integer> colMap = colsMapList.get(currentCell.getCol());

        rowMap.put(symbol, rowMap.getOrDefault(symbol, 0) + 1);
        colMap.put(symbol, colMap.getOrDefault(symbol, 0) + 1);

        if(rowMap.size() > 1) {
            invalidMapCount++;   
        }
        if(colMap.size() > 1) {
            invalidMapCount++;
        }

        if(currentCell.getRow() == currentCell.getCol()) {
            topLeftDiagonalMap.put(symbol, topLeftDiagonalMap.getOrDefault(symbol, 0) + 1);
            if(topLeftDiagonalMap.size() > 1) {
                invalidMapCount++;
            }
        }
        
        if(currentCell.getRow() + currentCell.getCol() == (board.getSize() - 1)) {
            topRightDiagonalMap.put(symbol, topRightDiagonalMap.getOrDefault(symbol, 0) + 1);
            if(topRightDiagonalMap.size() > 1) {
                invalidMapCount++;
            }
        }

        if((currentCell.getRow() == 0 || currentCell.getRow() == board.getSize() - 1)
        && (currentCell.getCol() == 0 || currentCell.getCol() == board.getSize() - 1)) {
            cornersMap.put(symbol, cornersMap.getOrDefault(symbol, 0) + 1);
            if(cornersMap.size() > 1) {
                invalidMapCount++;
            }
        }
        
        if(cornersMap.getOrDefault(symbol, 0) == 4
        || topLeftDiagonalMap.getOrDefault(symbol, 0) == board.getSize()
        || topRightDiagonalMap.getOrDefault(symbol, 0) == board.getSize()
        || rowMap.get(symbol) == board.getSize()
        || colMap.get(symbol) == board.getSize()) {
            return currentMove.getPlayer();
        } 

        // if(invalidMapCount == (board.getSize() + 3)) {
        if(checkDraw()) {
            throw new GameDrawnException("No more winner possible.");
        }
        
        return null;
    }

    private boolean checkDraw() {
        for(HashMap<Character, Integer> map : rowsMapList) {
            if(map.size() <= 1)
                return false;
        }

        for(HashMap<Character, Integer> map : colsMapList) {
            if(map.size() <= 1)
                return false;
        }

        if(topLeftDiagonalMap.size() <= 1 
        || topRightDiagonalMap.size() <= 1
        || cornersMap.size() <= 1)
            return false;
            
        return true;
    }
    
}
