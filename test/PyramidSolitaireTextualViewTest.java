import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import org.junit.Test;

/** Tests for {@link PyramidSolitaireTextualView}s. */
public class PyramidSolitaireTextualViewTest {

  PyramidSolitaireModel generator = new BasicPyramidSolitaire();
  PyramidSolitaireModel pyramid = new MultiPyramidSolitaire();
  PyramidSolitaireTextualView text = new PyramidSolitaireTextualView(pyramid);

  protected void initialCondition() {
    pyramid.startGame(this.pyramid.getDeck(), false, 7, 3);

    this.text = new PyramidSolitaireTextualView(pyramid);
  }

  @Test
  public void toStringTest() {
    initialCondition();
    assertEquals(text.toString(), "A♣\nDraw:");
    pyramid.startGame(generator.getDeck(),
        false, 1, 11);
    pyramid.removeUsingDraw(10, 0, 0);
    PyramidSolitaireTextualView text = new PyramidSolitaireTextualView(
        pyramid);
    assertEquals(text.toString(), "You win!");
    pyramid.startGame(generator.getDeck(),
        false, 1, 1);
    for (int i = 0; i < 51; i++) {
      pyramid.discardDraw(0);
    }
    assertEquals(text.toString(), "Game over. Score: 1");
    assertEquals(new PyramidSolitaireTextualView(generator).toString(), "");
  }

  @Test
  public void finalStateTest() {
    initialCondition();
    assertEquals(text.finalState(), "A♣\nDraw:");
    pyramid.startGame(generator.getDeck(),
        false, 1, 11);
    pyramid.removeUsingDraw(10, 0, 0);
    PyramidSolitaireTextualView text = new PyramidSolitaireTextualView(
        pyramid);
    assertEquals(text.finalState(),
        ".\nDraw: 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, K♣");
    pyramid.startGame(generator.getDeck(),
        false, 1, 1);
    for (int i = 0; i < 51; i++) {
      pyramid.discardDraw(0);
    }
    assertEquals(text.finalState(), "A♣\nDraw:");
    assertEquals(new PyramidSolitaireTextualView(generator).finalState(), "");
  }

  @Test
  public void renderTest() throws IOException {
    initialCondition();
    pyramid = new BasicPyramidSolitaire();
    text = new PyramidSolitaireTextualView(pyramid, new StringBuilder(""));
    assertEquals(text.state.toString(), "\n");

    pyramid.startGame(this.pyramid.getDeck(), false, 1, 0);
    text.render();
    assertEquals(text.state.toString(), "\nA♣\nDraw:\n");

    initialCondition();
    pyramid = new BasicPyramidSolitaire();
    text = new PyramidSolitaireTextualView(pyramid, new StringBuilder(""));
    pyramid.startGame(generator.getDeck(),
        false, 1, 11);
    pyramid.removeUsingDraw(10, 0, 0);
    text.render();
    assertEquals(text.state.toString(), "\nYou win!\n");

    initialCondition();
    pyramid = new BasicPyramidSolitaire();
    text = new PyramidSolitaireTextualView(pyramid, new StringBuilder(""));
    pyramid.startGame(generator.getDeck(),
        false, 1, 1);
    for (int i = 0; i < 51; i++) {
      pyramid.discardDraw(0);
    }
    text.render();
    assertEquals(text.state.toString(), "\nGame over. Score: 1\n");

    initialCondition();
    pyramid = new BasicPyramidSolitaire();
    text = new PyramidSolitaireTextualView(pyramid, new StringBuilder(""));
    text.render();
    assertEquals(text.state.toString(), "\n\n");
  }

}
