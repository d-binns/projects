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
		for(int c = 0; c < puzzle[row].length; c++) {
			if(puzzle[row][c] == guess) {
				return false;
			}
		}

		/* check vertical */
		for(int r = 0; r < puzzle.length; r++) {
			if(puzzle[r][col] == guess) {
				return false;
			}
		}
		
		/* check box */
		int rowOffset = row - (row % 3);
		int colOffset = col - (col % 3);
		
		for (int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				int boxRow = r + rowOffset;
				int boxCol = c + colOffset;
				if (puzzle[boxRow][boxCol]== guess) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static boolean solve(char puzzle[][], char alpha[], int row, int col) {
		outer:
		    for(;row < puzzle.length; row++) {
		    	for(;col<puzzle[row].length; col++) {
		    		if(puzzle[row][col] == '-') {
		    			break outer;
		    		}
		    	}
		    	col = 0;
		    }
			
			if(row >= puzzle.length) {
				return true;
			}
			
			for(int guess = 0; guess < 9; guess++) {
				if(!validGuess(puzzle, row, col, alpha[guess])) {
					continue;
				}
				
				puzzle[row][col] = alpha[guess];
				boolean isSolved = solve(puzzle,alpha, row, col);
				if (isSolved) {
					return true;
				}
				puzzle[row][col] = '-';
			}
			
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
