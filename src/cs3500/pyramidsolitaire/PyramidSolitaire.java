package cs3500.pyramidsolitaire;


import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.StringReader;
import java.util.Scanner;

/**
 * The pyramidsolitaire to use MVC to play the whole game.
 */
public final class PyramidSolitaire {
  private static Scanner scan = new Scanner(System.in);

  /**
   * The main method to play the whole game.
   *
   * @param args  command-line arguments.
   */
  public static void main(String[] args) {
    run();
  }

  /**
   * Run the whole PyramidSolitaire game.
   */
  private static void run() {
    PyramidSolitaireModel<Card> model = catchExcpetionForStart();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model, new StringBuilder());
    System.out.println(view.state.toString());
    catchExcpetionForPlayGame(model);
  }

  /**
   * Get a pyramid model according to the given gametype from system.in without throw exception.
   *
   * @return PyramidSolitaireModel The model used for continue game.
   */
  private static PyramidSolitaireModel<Card> catchExcpetionForStart() {
    try {
      PyramidSolitaireModel<Card> model = startGame();
      return model;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return catchExcpetionForStart();
    }
  }

  /**
   * Catch exception from playing process to tell the player what is the problem.
   * and avoid stop game.
   *
   * @param model  The model used to run the game.
   */
  private static void catchExcpetionForPlayGame(PyramidSolitaireModel<Card> model) {
    try {
      play(model);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      catchExcpetionForPlayGame(model);
    }
  }

  /**
   * Choose the model type according to the given game type from System.in.
   *
   * @return The model used to run the game.
   */
  private static PyramidSolitaireModel<Card> startGame() {
    PyramidSolitaireCreator creator = new PyramidSolitaireCreator();
    Scanner firstline = new Scanner(scan.nextLine());
    PyramidSolitaireModel<Card> model =
        creator.create(GameType.valueOf(firstline.next().toUpperCase()));

    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(
        new StringReader(""), new StringBuilder()
    );

    if (firstline.hasNextInt()) {
      int row = firstline.nextInt();
      if (firstline.hasNextInt()) {
        int draw = firstline.nextInt();
        controller.playGame(model, model.getDeck(), true,
            row, draw);
      } else {
        controller.playGame(model, model.getDeck(), true,
            7, 3);
      }
    } else {
      controller.playGame(model, model.getDeck(), true,
          7, 3);
    }


    return model;
  }

  /**
   * Play the game according to the given command.
   *
   * @param model  The model used to play game.
   */
  private static void play(PyramidSolitaireModel<Card> model) {
    PyramidSolitaireTextualController controller =
        new PyramidSolitaireTextualController(
            new StringReader(scan.nextLine()),
            new StringBuilder());
    controller.playGame(model, model.getDeck(), true, 1, 1);
    System.out.println(controller.ap.toString());
    if (!model.isGameOver()
        && !controller.quitState) {
      play(model);
    }
  }
}