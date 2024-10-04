package com.morpion.game;
import com.morpion.exceptions.TicTacToeInvalidInputException;
import static com.morpion.game.StringConstants.LINE_SEPARATOR;
import static com.morpion.game.StringConstants.SPACE;

public class TicTacToe {

   private char[][] grid = new char[][] {
           {'.', '.', '.'},
           {'.', '.', '.'},
           {'.', '.', '.'},
   };

   public void processInput(Player player, int inputUser) throws TicTacToeInvalidInputException {
      var row = (inputUser - 1) / 3;
      var column = (inputUser - 1) % 3;
      if (grid[row][column] == '.') {
         if (player.equals(Player.FIRST)){
            grid[row][column] = 'X';
         } else {
            grid[row][column] = 'O';
         }
      } else {
         throw new TicTacToeInvalidInputException("La case est déjà occupée... Veuillez en saisir une autre.");
      }
   }

   public boolean checkWin() {

      for (int i = 0; i < 3; i++) {
         var checkWinLine = grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][2] != '.';
         var checkWinColumn = grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[2][i] != '.';
         if (checkWinLine || checkWinColumn) {
            return true;
         }
      }
      var checkWinDiagonal1 = grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != '.';
      var checkWinDiagonal2 = grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] != '.';
      if (checkWinDiagonal1 || checkWinDiagonal2) {
         return true;
      }
      return false;
   }

   public boolean checkDraw() {
      for (char[] row : grid) {
         for (char cell : row) {
            if (cell == '.') {
               return false;
            }
         }
      }
      return true;
   }

   @Override
   public String toString() {
      final var builder = new StringBuilder();
      builder.append("Grille du Morpion : ").append(LINE_SEPARATOR);
      for (char[] lines : grid){
         for (char cell : lines) {
            builder.append(SPACE).append(cell).append(SPACE);
         }
         builder.append(LINE_SEPARATOR);
      }
      return builder.toString();
   }
}
