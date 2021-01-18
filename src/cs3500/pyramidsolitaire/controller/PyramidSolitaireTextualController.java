package cs3500.pyramidsolitaire.controller;


import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The controller to take in input from player and get result from model.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  /**
   * Represents the basic attributes of PyramidSolitaireTextualController.
   */
  private final Readable rd;
  public final Appendable ap;
  public boolean quitState;


  /**
   * constructs a PyramidSolitaireTextualController.
   *
   * @param rd The readable that is a input from player.
   * @param ap The appendable that shows the result from model.
   * @throws IllegalArgumentException if rd or ap is null.
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable cannot be null");
    }
    this.rd = rd;
    this.ap = ap;
  }


  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
      int numRows, int numDraw) {

    if (model == null
        || deck == null) {
      throw new IllegalArgumentException("The model and deck cannot be null.");
    }


    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("Game cannot start");
    }


    Scanner s = new Scanner(rd);



    model = read(s, model);
  }

  /**
   * Run the model and return the result of model.
   *
   * @param s   A scanner to read the readable.
   * @param model   A model to play game.
   * @param <K>   The type of cards used by the model.
   * @return    A model that has run the command from player.
   */
  private <K> PyramidSolitaireModel<K> read(Scanner s, PyramidSolitaireModel model) {

    if (s.hasNext()) {
      String next = s.next();
      switch (next) {
        case "rm1":
          Object rm11 = this.nextValid(s, model.getNumRows());
          if (rm11.equals("q")) {
            quitUpdate(model);
            return model;
          }

          Object rm12 = this.nextValid(s, (int) rm11);
          if (rm12.equals("q")) {
            quitUpdate(model);
            return model;
          }

          try {
            model.remove((int) rm11 - 1, (int) rm12 - 1);
          } catch (IllegalArgumentException e) {
            try {
              this.ap.append("Invalid move. Play again." + e.getMessage() + "\n");
            } catch (IOException ioException) {
              throw new IllegalStateException("Unable to transmit");
            }
          }

          if (model.isGameOver()) {
            return endRead(model);
          } else {
            update(model);
            return read(s, model);
          }

        case "rm2":
          Object rm21 = this.nextValid(s, model.getNumRows());
          if (rm21.equals("q")) {
            quitUpdate(model);
            return model;
          }

          Object rm22 = this.nextValid(s, (int) rm21);
          if (rm22.equals("q")) {
            quitUpdate(model);
            return model;
          }

          Object rm23 = this.nextValid(s, model.getNumRows());
          if (rm23.equals("q")) {
            quitUpdate(model);
            return model;
          }

          Object rm24 = this.nextValid(s, (int) rm23);
          if (rm24.equals("q")) {
            quitUpdate(model);
            return model;
          }


          try {
            model.remove((int) rm21 - 1, (int) rm22 - 1, (int) rm23 - 1, (int) rm24 - 1);
          } catch (Exception e) {
            try {
              this.ap.append("Invalid move. Play again." + e.getMessage() + "\n");
            } catch (IOException ioException) {
              throw new IllegalStateException("Unable to transmit");
            }
          }

          if (model.isGameOver()) {
            return endRead(model);
          } else {
            update(model);
            return read(s, model);
          }

        case "rmwd":
          Object rmwd1 = this.nextValid(s, model.getNumDraw());
          if (rmwd1.equals("q")) {
            quitUpdate(model);
            return model;
          }

          Object rmwd2 = this.nextValid(s, model.getNumRows());
          if (rmwd2.equals("q")) {
            quitUpdate(model);
            return model;
          }

          Object rmwd3 = this.nextValid(s, (int) rmwd2);
          if (rmwd3.equals("q")) {
            quitUpdate(model);
            return model;
          }

          try {
            model.removeUsingDraw((int) rmwd1 - 1, (int) rmwd2 - 1, (int) rmwd3 - 1);
          } catch (Exception e) {
            try {
              this.ap.append("Invalid move. Play again." + e.getMessage() + "\n");
            } catch (IOException ioException) {
              throw new IllegalStateException("Unable to transmit");
            }
          }

          if (model.isGameOver()) {
            return endRead(model);
          } else {
            update(model);
            return read(s, model);
          }

        case "dd":
          Object dd1 = this.nextValid(s, model.getNumDraw());
          if (dd1.equals("q")) {
            quitUpdate(model);
            return model;
          }

          try {
            model.discardDraw((int) dd1 - 1);
          } catch (Exception e) {
            try {
              this.ap.append("Invalid move. Play again." + e.getMessage() + "\n");
            } catch (IOException ioException) {
              throw new IllegalStateException("Unable to transmit");
            }
          }

          if (model.isGameOver()) {
            return endRead(model);
          } else {
            update(model);
            return read(s, model);
          }
        case "q":
        case "Q":
          quitUpdate(model);
          return model;
        default:
          return read(s, model);
      }
    }
    return model;
  }

  /**
   * To run the readable when it need to end and return the final state of model.
   *
   * @param model   The model to run the command.
   * @param <K>    The type of Card.
   * @return   The final state of model after run.
   */
  private <K> PyramidSolitaireModel<K> endRead(PyramidSolitaireModel<K> model) {
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model, new StringBuilder());

    try {
      this.ap.append(view.finalState() + "\n");
      int score = model.getScore();
      this.ap.append("Score: " + score + "\n");
    } catch (Exception e) {
      throw new IllegalStateException("Unable to transmit");
    }
    end(model);
    return model;
  }


  /**
   * Get the next valid value, "q" or "Q" or an int.
   *
   * @param s   A scanner to read the readable.
   * @param limit  The limitation of valid int.
   * @return the next valid value, "q" or "Q" or an int.
   */
  private Object nextValid(Scanner s, int limit) {
    if (s.hasNext()) {
      if (s.hasNextInt()) {
        int result = s.nextInt();
        if (result > 0 && result <= limit) {
          return result;
        } else {
          return nextValid(s, limit);
        }
      } else {
        String result = s.next();
        if (result.equals("q") || result.equals("Q")) {
          return "q";
        } else {
          return nextValid(s, limit);
        }
      }
    } else {
      throw new IllegalStateException("There is not enough valid readable");
    }
  }


  /**
   * To update the appendable with the render from view.
   *
   * @param model  The model to run the readable.
   * @param <K>   The type of card.
   */
  private <K> void update(PyramidSolitaireModel<K>  model) {
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model, new StringBuilder());

    try {
      if (model.isGameOver()) {
        this.ap.append(view.state.toString() + "\n");
      } else {
        this.ap.append(view.state.toString());
        int score = model.getScore();
        this.ap.append("Score: " + score + "\n");
      }
    } catch (Exception e) {
      throw new IllegalStateException("Unable to transmit");
    }
  }

  /**
   * To update the appendable when the game need end but the isGameOver is false.
   *
   * @param model  The model to run the readable.
   * @param <K>   The type of card.
   */
  private <K> void end(PyramidSolitaireModel<K> model) {
    try {
      if (model.getScore() == 0) {
        this.ap.append("You win!" + "\n");
      } else {
        int score = model.getScore();
        this.ap.append("Game over. Score: " + score + "\n");
      }
    } catch (Exception e) {
      throw new IllegalStateException("Unable to transmit");
    }
  }

  /**
   *  To update the appendable when get "q" or "Q" from player.
   *
   * @param model  The model to run the readable.
   * @param <K>   The type of card.
   */
  private <K> void quitUpdate(PyramidSolitaireModel<K>  model) {
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model, new StringBuilder());
    quitState = true;
    try {
      this.ap.append("Game quit!" + "\n");
      this.ap.append("State of game when quit:" + "\n");
      this.ap.append(view.state.toString());
      int score = model.getScore();
      this.ap.append("Score: " + score);
    } catch (Exception e) {
      throw new IllegalStateException("Unable to transmit");
    }
  }
}
