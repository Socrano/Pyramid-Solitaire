import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.StringReader;
import org.junit.Test;


/** Tests for {@link PyramidSolitaireTextualController}s. */
public class PyramidSolitaireTextualControllerTest {


  //For invalid rm1
  @Test
  public void invalidRm1Test() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm1 1 1"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(),
        "Invalid move. Play again.The attempted remove is invalid\n"
        + "A♣\n"
        + "Draw: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "Score: 1\n");

    p = new PyramidSolitaireTextualController(
        new StringReader("rm1 4 2"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 6, 12);
    assertEquals(p.ap.toString(),
        "Invalid move. Play again.The attempted remove is invalid\n"
        + "          A♣\n"
        + "        2♣  3♣\n"
        + "      4♣  5♣  6♣\n"
        + "    7♣  8♣  9♣  10♣\n"
        + "  J♣  Q♣  K♣  A♦  2♦\n"
        + "3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "Draw: 9♦, 10♦, J♦, Q♦, K♦, A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n"
        + "Score: 127\n");
  }

  //For valid rm1
  @Test
  public void validRm1Test() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm1 5 3"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  .   A♦  2♦\n"
        + "Draw:\n"
        + "Score: 81\n");

    p = new PyramidSolitaireTextualController(
        new StringReader("rm1 7 5"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 7, 0);
    assertEquals(p.ap.toString(),
        "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw:\n"
        + "Score: 172\n");
  }

  //For invalid rm2
  @Test
  public void invalidRm2Test() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm2 1 1 1 1"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(),
        "Invalid move. Play again.The attempted remove is invalid\n"
        + "A♣\n"
        + "Draw: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "Score: 1\n");

    p = new PyramidSolitaireTextualController(
        new StringReader("rm2 4 2 3 3"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 6, 12);
    assertEquals(p.ap.toString(),
        "Invalid move. Play again.The attempted remove is invalid\n"
        + "          A♣\n"
        + "        2♣  3♣\n"
        + "      4♣  5♣  6♣\n"
        + "    7♣  8♣  9♣  10♣\n"
        + "  J♣  Q♣  K♣  A♦  2♦\n"
        + "3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "Draw: 9♦, 10♦, J♦, Q♦, K♦, A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n"
        + "Score: 127\n");
  }

  //For valid rm2
  @Test
  public void validRm2Test() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm2 5 2 5 4"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  .   K♣  .   2♦\n"
        + "Draw:\n"
        + "Score: 81\n");

    p = new PyramidSolitaireTextualController(
        new StringReader("rm2 5 1 5 5"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + ".   Q♣  K♣  A♦  .\n"
        + "Draw:\n"
        + "Score: 81\n");
  }

  //For invalid rmwd
  @Test
  public void invalidRmwdTest() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rmwd 1 1 1"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 1);
    assertEquals(p.ap.toString(),
        "Invalid move. Play again.The attempted remove is invalid\n"
        + "A♣\n"
        + "Draw: 2♣\n"
        + "Score: 1\n");

    p = new PyramidSolitaireTextualController(
        new StringReader("rmwd 5 5 5"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 5);
    assertEquals(p.ap.toString(),
        "Invalid move. Play again.The attempted remove is invalid\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  K♣  A♦  2♦\n"
        + "Draw: 3♦, 4♦, 5♦, 6♦, 7♦\n"
        + "Score: 94\n");
  }

  //For valid rmwd
  @Test
  public void validRmwdTest() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rmwd 11 1 1"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(),  ".\n"
        + "Draw: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, K♣, A♦\n"
        + "Score: 0\n"
        + "You win!\n");

    p = new PyramidSolitaireTextualController(
        new StringReader("rmwd 7 2 2"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 2, 10);
    assertEquals(p.ap.toString(),
        "  A♣\n"
        + "2♣  .\n"
        + "Draw: 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, J♣, Q♣, K♣, A♦\n"
        + "Score: 3\n");
  }

  //For valid dd
  @Test
  public void invalidDdTest() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("dd 1"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 1);
    assertEquals(p.ap.toString(),
        "A♣\n"
        + "Draw: 3♣\n"
        + "Score: 1\n");

    p = new PyramidSolitaireTextualController(
        new StringReader("dd 5"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 7);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  K♣  A♦  2♦\n"
        + "Draw: 3♦, 4♦, 5♦, 6♦, 8♦, 9♦, 10♦\n"
        + "Score: 94\n");
  }

  //Test for there is not enough valid input in readable
  @Test(expected = IllegalStateException.class)
  public void notEnoughValidInputTest() {
    //For rm1
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm1 2 2"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(), "");

    //For rm2
    p = new PyramidSolitaireTextualController(
        new StringReader("rm2 2 2 1 1 10000000"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(), "");

    //For rmwd
    p = new PyramidSolitaireTextualController(
        new StringReader("rmwd 1000 1000 1000"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(), "");

    p = new PyramidSolitaireTextualController(
        new StringReader("rmwd 1 1000 1"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(), "");

    //For dd
    p = new PyramidSolitaireTextualController(
        new StringReader("dd 1000 1000 1000"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 1, 12);
    assertEquals(p.ap.toString(), "");
  }

  //Test for readable can pass those invalid input
  @Test
  public void passInvalidTest() {
    //For rm1
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm1 100 5 3"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  .   A♦  2♦\n"
        + "Draw:\n"
        + "Score: 81\n");

    //For rm2
    p = new PyramidSolitaireTextualController(
        new StringReader("rm2 100 5 aha 1 lol 5 2000 5"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + ".   Q♣  K♣  A♦  .\n"
        + "Draw:\n"
        + "Score: 81\n");

    //For rmwd
    p = new PyramidSolitaireTextualController(
        new StringReader("rmwd Oriented 7 Object 2 design 2"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 2, 10);
    assertEquals(p.ap.toString(),
        "  A♣\n"
        + "2♣  .\n"
        + "Draw: 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, J♣, Q♣, K♣, A♦\n"
        + "Score: 3\n");

    //For dd
    p = new PyramidSolitaireTextualController(
        new StringReader("dd 1000000 dasd wq  5"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 7);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  K♣  A♦  2♦\n"
        + "Draw: 3♦, 4♦, 5♦, 6♦, 8♦, 9♦, 10♦\n"
        + "Score: 94\n");
  }

  //Test for quit
  @Test
  public void quitTest() {
    //For quit at beginning
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("q rm1 100 5 3"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "Game quit!\n"
        + "State of game when quit:\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  K♣  A♦  2♦\n"
        + "Draw:\n"
        + "Score: 94");

    p = new PyramidSolitaireTextualController(
        new StringReader("Q rm1 100 5 3"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "Game quit!\n"
        + "State of game when quit:\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  K♣  A♦  2♦\n"
        + "Draw:\n"
        + "Score: 94");

    //For quit break a command
    p = new PyramidSolitaireTextualController(
        new StringReader(" rm1 q 100 5 3"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "Game quit!\n"
        + "State of game when quit:\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  K♣  A♦  2♦\n"
        + "Draw:\n"
        + "Score: 94");

    p = new PyramidSolitaireTextualController(
        new StringReader(" rm1 Q 100 5 3"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "Game quit!\n"
        + "State of game when quit:\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  K♣  A♦  2♦\n"
        + "Draw:\n"
        + "Score: 94");

    //For quit at the end
    p = new PyramidSolitaireTextualController(
        new StringReader(" rm1  100 5 3 q"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  .   A♦  2♦\n"
        + "Draw:\n"
        + "Score: 81\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  .   A♦  2♦\n"
        + "Draw:\n"
        + "Score: 81");

    p = new PyramidSolitaireTextualController(
        new StringReader(" rm1  100 5 3 Q"), new StringBuilder());
    pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 0);
    assertEquals(p.ap.toString(),
        "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  .   A♦  2♦\n"
        + "Draw:\n"
        + "Score: 81\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  .   A♦  2♦\n"
        + "Draw:\n"
        + "Score: 81");
  }

  //For continue command
  @Test
  public void continueCommandTest() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm1 5 3 rm2 5 1 5 5 rm2 5 2 5 4 rmwd 1 4 4 dd 1"), new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();

    p.playGame(pyramid, pyramid.getDeck(), false, 5, 10);
    assertEquals(p.ap.toString(),  "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + "J♣  Q♣  .   A♦  2♦\n"
        + "Draw: 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
        + "Score: 81\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + ".   Q♣  .   A♦  .\n"
        + "Draw: 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
        + "Score: 68\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  10♣\n"
        + ".   .   .   .   .\n"
        + "Draw: 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
        + "Score: 55\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  .\n"
        + ".   .   .   .   .\n"
        + "Draw: 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "Score: 45\n"
        + "        A♣\n"
        + "      2♣  3♣\n"
        + "    4♣  5♣  6♣\n"
        + "  7♣  8♣  9♣  .\n"
        + ".   .   .   .   .\n"
        + "Draw: 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♥\n"
        + "Score: 45\n");
  }

  //Test for null deck or model
  @Test(expected = IllegalArgumentException.class)
  public void nullDeckOrModelTest() {
    PyramidSolitaireTextualController p = new PyramidSolitaireTextualController(
        new StringReader("rm1 5 3 rm2 5 1 5 5 rm2 5 2 5 4 rmwd 1 4 4 dd 1"),
        new StringBuilder());
    PyramidSolitaireModel pyramid = new BasicPyramidSolitaire();
    p.playGame(null, pyramid.getDeck(), false, 1, 1);
    assertEquals(p.ap, "");
    p.playGame(pyramid, null, false, 1, 1);
    assertEquals(p.ap, "");
  }
}
