import com.morpion.exceptions.TicTacToeInvalidInputException;
import com.morpion.game.TicTacToe;
import com.morpion.game.Player;

import java.util.HashMap;
import java.util.Scanner;

import static com.morpion.game.StringConstants.BLANK;

public class Main {

   public static void main(String[] args) {

      final var game = new TicTacToe();
      var player = Player.FIRST;
      var players = initPlayers();

      while (true) {
         try {
            System.out.println(game);
            System.out.println(players.get(player) + " / Saisissez un chiffre entre 1 et 9 :");
            final var inputUser = getInputUser();

            game.processInput(player, inputUser);
            if (game.checkWin()) {
               System.out.println(game);
               System.out.println(players.get(player) + " a gagné la partie !");
               break;
            }
            if (game.checkDraw()) {
               System.out.println(game);
               System.out.println("Personne n'a gagné la partie.");
               break;
            }
            player = nextPlayer(player);
         } catch (TicTacToeInvalidInputException e) {
            System.out.println(e.getMessage());
         } catch (NumberFormatException e) {
            System.out.println("Vous devez saisir un CHIFFRE ENTRE 1 ET 9 !");
         }
      }
   }

   private static HashMap<Player, String> initPlayers() {
      var players = new HashMap<Player, String>();
      var scanner = new Scanner(System.in);

      do {
         System.out.println("Veuillez entrer le nom du joueur 1 : ");
         players.put(Player.FIRST, scanner.nextLine());
      } while (players.get(Player.FIRST).equals(BLANK));

      do {
         System.out.println("Veuillez entrer le nom du joueur 2 : ");
         players.put(Player.SECOND, scanner.nextLine());
      } while (players.get(Player.SECOND).equals(BLANK));

      return players;
   }

   private static int getInputUser() throws TicTacToeInvalidInputException {
      final var  scanner = new Scanner(System.in);
      var input =  scanner.nextLine();

      if (input.equals("exit") ||input.equals("quit")) {
         System.exit(0);
      }

      var inputInteger = Integer.parseInt(input);
      if (inputInteger < 1 || inputInteger > 9) {
         throw new TicTacToeInvalidInputException("Le chiffre doit  être compris entre 1 et 9 !");
      }
      return inputInteger;
   }

   private static Player nextPlayer(Player player) {
      if (player == Player.FIRST){
         return Player.SECOND;
      } else {
         return Player.FIRST;
      }
   }
}