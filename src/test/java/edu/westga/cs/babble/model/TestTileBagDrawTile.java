/**
 * 
 */
package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Blair Pattison
 *
 */
public class TestTileBagDrawTile {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void canDrawAllTiles() throws EmptyTileBagException {
		TileBag testTileBag = new TileBag();

		assertFalse(testTileBag.isEmpty());

		for (int i = 0; i < 98; i++) {
			testTileBag.drawTile();
		}
		assertTrue(testTileBag.isEmpty());
	}

	@Test
	public void canNotDrawTooManyTiles() throws EmptyTileBagException {
		TileBag testTileBag = new TileBag();
		for (int i = 0; i < 98; i++) {
			testTileBag.drawTile();
		}
		assertThrows(EmptyTileBagException.class, () -> {
			testTileBag.drawTile();
		});

	}

	@Test
	public void hasProperTileDistribution() throws EmptyTileBagException {
		TileBag testTileBag = new TileBag();
		int E = 0;
		int A = 0;
		int I = 0;
		int O = 0;
		int N = 0;
		int R = 0;
		int T = 0;
		int L = 0;
		int S = 0;
		int U = 0;
		int D = 0;
		int G = 0;
		int B = 0;
		int C = 0;
		int M = 0;
		int P = 0;
		int F = 0;
		int H = 0;
		int V = 0;
		int W = 0;
		int Y = 0;
		int K = 0;
		int J = 0;
		int X = 0;
		int Q = 0;
		int Z = 0;

		for (int i = 0; i < 98; i++) {
			Tile testTile = testTileBag.drawTile();

			String tileLetter = String.valueOf(testTile.getLetter());
			if (tileLetter.equals("E")) {
				E++;
			}
			if (tileLetter.equals("A")) {
				A++;
			}
			if (tileLetter.equals("I")) {
				I++;
			}
			if (tileLetter.equals("O")) {
				O++;
			}
			if (tileLetter.equals("N")) {
				N++;
			}
			if (tileLetter.equals("R")) {
				R++;
			}
			if (tileLetter.equals("T")) {
				T++;
			}
			if (tileLetter.equals("L")) {
				L++;
			}
			if (tileLetter.equals("S")) {
				S++;
			}
			if (tileLetter.equals("U")) {
				U++;
			}
			if (tileLetter.equals("D")) {
				D++;
			}
			if (tileLetter.equals("G")) {
				G++;
			}
			if (tileLetter.equals("B")) {
				B++;
			}
			if (tileLetter.equals("C")) {
				C++;
			}
			if (tileLetter.equals("M")) {
				M++;
			}
			if (tileLetter.equals("P")) {
				P++;
			}
			if (tileLetter.equals("F")) {
				F++;
			}
			if (tileLetter.equals("H")) {
				H++;
			}
			if (tileLetter.equals("V")) {
				V++;
			}
			if (tileLetter.equals("W")) {
				W++;
			}
			if (tileLetter.equals("Y")) {
				Y++;
			}
			if (tileLetter.equals("K")) {
				K++;
			}
			if (tileLetter.equals("J")) {
				J++;
			}
			if (tileLetter.equals("X")) {
				X++;
			}
			if (tileLetter.equals("Q")) {
				Q++;
			}
			if (tileLetter.equals("Z")) {
				Z++;
			}
		}

		assertTrue(testTileBag.isEmpty());
		assertEquals(E, 12);
		assertEquals(A, 9);
		assertEquals(I, 9);
		assertEquals(O, 8);
		assertEquals(N, 6);
		assertEquals(R, 6);
		assertEquals(T, 6);
		assertEquals(L, 4);
		assertEquals(S, 4);
		assertEquals(U, 4);
		assertEquals(D, 4);
		assertEquals(G, 3);
		assertEquals(B, 2);
		assertEquals(C, 2);
		assertEquals(M, 2);
		assertEquals(P, 2);
		assertEquals(F, 2);
		assertEquals(H, 2);
		assertEquals(V, 2);
		assertEquals(W, 2);
		assertEquals(Y, 2);
		assertEquals(K, 1);
		assertEquals(J, 1);
		assertEquals(X, 1);
		assertEquals(Q, 1);
		assertEquals(Z, 1);
	}
}
