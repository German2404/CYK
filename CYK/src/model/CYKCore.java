package model;

import java.util.HashSet;

/**
 * Class containing the CYK algorithm and its auxiliary methods
 */
public class CYKCore {
	
	/**
	 * Implementation of the CYK Algorithm.
	 * Receives a grammar and a string to check if such string can be produced by that grammar.
	 * Pre condition: Grammar must be in CNF
	 * @param grammar	The Grammar used for checking
	 * @param string	The String to be checked
	 * @return true if the word can be produced, false if not.
	 */
	public static boolean CYK(Grammar grammar, String string) {
		boolean produces=false;
		@SuppressWarnings("unchecked")
		//A matrix of Sets if generated and then initialized. The size is set to the length of the
		//input String to be checked plus one, to ease implementation following the pseudocode, 
		//since in it the matrix starts at (1,1)
		HashSet<String>[][] matrix=(HashSet<String>[][])new HashSet[string.length()+1][string.length()+1];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j]=new HashSet<String>();
			}
		}
		
		//For each char of the input string, the code checks if each given terminal is contained in
		//each one of the productions of the grammar. If it is, it adds the production name to the
		//specified Set
		for (int i = 1; i < matrix.length; i++) {
			String terminal=string.charAt(i-1)+"";
			for(Production p: grammar.getProductions()) {
				if(p.getTerminals().contains(terminal)) {
					matrix[i][1].add(p.getName());
				}
			}
		}
		
		//This is the core of the CYK algorithm. The code starts to run thru the matrix in a triangular form,
		//Performing each cross product of the matrix[i][k] and matrix[i+k][j-k] Sets, and the corresponding
		//k-Unions required by the algorithm, then checking if the resulting nonTerminals in the Set are
		//contained in any of the productions of the grammar. It assigns the resulting productions to the
		//corresponding matrix[i][j] position.
		for (int j = 2; j < matrix.length; j++) {
			for (int i = 1; i < matrix.length-j+1; i++) {
				
				HashSet<String> look=new HashSet<String>();
				for (int k = 1; k <= j-1; k++) {
					look.addAll(stringHashSetCrossProduct(matrix[i][k], matrix[i+k][j-k]));
				}
				for(String s:look) {
					for(Production p:grammar.getProductions()) {
						if(p.getNonTerminals().contains(s)) {
							matrix[i][j].add(p.getName());
						}
					}
				}
			}
		}
		
		//Since this CYK implementation uses Chars as terminals, the code has to check for the existence of lambda
		//productions in the starting production if the entry word is empty.
		if(grammar.getInitial().getTerminals().contains("")&&string.equals("")) {
			produces=true;
		}
		//If its not empty, it has to check for the starting production in the final position of the matrix
		else if(matrix[1][matrix.length-1].contains(grammar.getInitial().getName())) {
			
			produces=true;
		}
		
		return produces;
	}
	
	/**
	 * @param a	The first term of the cross product operation 
	 * @param b	The second term of the cross product operation
	 * @return A HashSet containing the cross product of a and b
	 * This method returns a HashSet containing the cross product of two input HashSets.
	 */
	private static HashSet<String> stringHashSetCrossProduct(HashSet<String> a, HashSet<String> b) {
		//This code is a simple implementation of a cross product operation for HashSets of Strings
		//It runs thru each Set and concats their Strings
		HashSet<String> answer=new HashSet<String>();
		for(String s:a) {
			for(String t: b) {
				answer.add(s+t);
			}
		}
		return answer;
		
	}

}
