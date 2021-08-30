/**
 * 
 */
package edu.westga.cs.babble.model;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Blair Pattison
 *
 */
public class TestPlayedWordMatches {
	PlayedWord testPlayedWord;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.testPlayedWord = new PlayedWord();
	}

	@Test
	public void hasTilesForAMultipleLetterWord() {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');
		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileTwo);
		this.testPlayedWord.append(testTileThree);
		this.testPlayedWord.append(testTileFour);

		assertTrue(this.testPlayedWord.matches("TEST"));
	}

	@Test
	public void hasTilesForASingleLetterWord() {
		Tile testTileOne = new Tile('A');

		this.testPlayedWord.append(testTileOne);

		assertTrue(this.testPlayedWord.matches("A"));
	}

	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');
		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileThree);
		this.testPlayedWord.append(testTileTwo);
		this.testPlayedWord.append(testTileFour);

		assertFalse(this.testPlayedWord.matches("TEST"));
		assertEquals(this.testPlayedWord.getHand(), "TSET");
	}

	@Test
	public void cannotMatchWordIfInsufficientTiles() {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');

		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileTwo);
		this.testPlayedWord.append(testTileThree);

		assertFalse(this.testPlayedWord.matches("TEST"));
		assertEquals(this.testPlayedWord.getHand(), "TES");
	}

	@Test
	public void canMatchWordWithDuplicateLetters() {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');
		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileTwo);
		this.testPlayedWord.append(testTileThree);
		this.testPlayedWord.append(testTileFour);

		assertTrue(this.testPlayedWord.matches("TEST"));

	}

	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');
		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileThree);
		this.testPlayedWord.append(testTileTwo);
		this.testPlayedWord.append(testTileFour);

		assertFalse(this.testPlayedWord.matches(" "));

	}

	@Test
	public void emptyWordShouldNotMatchEmptyText() {
		assertFalse(this.testPlayedWord.matches(" "));
	}

	@Test
	public void shouldNotAllowNull() {
		assertEquals(this.testPlayedWord.getHand(), "");
		assertThrows(IllegalArgumentException.class, () -> {
			this.testPlayedWord.matches(null);
		});
		assertFalse(this.testPlayedWord.matches(""));
	}
}
