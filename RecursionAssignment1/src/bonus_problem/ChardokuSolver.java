package bonus_problem;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChardokuSolver {

	private static File getFile() {
		JFileChooser jchooser = new JFileChooser(".");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
		jchooser.setFileFilter(filter);
		int returnVal = jchooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return jchooser.getSelectedFile();
		}
		return null;
	}

	private static char[][] readPuzzle(Scanner fileIn) {
		char puzzle[][] = new char[9][9];

		int row = 0, col = 0;
		while (fileIn.hasNextLine()) {
			String line[] = fileIn.nextLine().trim().split("");
			for (String s : line) {
				char alpha = s.charAt(0);
				puzzle[row][col] = alpha;
				col += 1;
			}
			row += 1;
			col = 0;
		}
		return puzzle;
	}

	private static void printPuzzle(char puzzle[][]) {
		String rowDashes = new String(new char[25]).replace("\0", "-");
		for (int row = 0; row < puzzle.length; row++) {
			if (row % 3 == 0) {
				System.out.println(rowDashes);
			}
			for (int col = 0; col < puzzle[row].length; col++) {
				if (col % 3 == 0) {
					System.out.print("| ");
				}
				System.out.print(puzzle[row][col] + " ");

			}
			System.out.println("|");
		}
		System.out.println(rowDashes);
	}

	private static boolean validGuess(char puzzle[][], int row, int col, char guess) {
		/* check horizontal */

		/* check vertical */

		/* check box */

		return true;
	}

	private static boolean solve(char puzzle[][], char alpha[], int row, int col) {
		return false;
	}

	public static void main(String[] args) throws IOException {
		File sudokuFile = getFile();
		if (sudokuFile != null) {
			Scanner fileIn = new Scanner(sudokuFile);
			char alpha[] = fileIn.nextLine().trim().toCharArray();
			char puzzle[][] = readPuzzle(fileIn);
			fileIn.close();
			System.out.println("Characters: " + Arrays.toString(alpha));
			printPuzzle(puzzle);
			solve(puzzle, alpha, 0, 0);
			System.out.println();
			printPuzzle(puzzle);
		} else {
			System.out.println("No file selected.");
		}

	}

}
