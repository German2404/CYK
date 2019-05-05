package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *Class representing a production
 */
public class Production {
	
	/**
	 * Set containing the terminal strings of the production.
	 * Usually implemented with a HashSet
	 */
	private Set<String> terminals;
	/**
	 * Set containing the non terminal strings of the production.
	 * Usually implemented with a HashSet
	 */
	private Set<String> nonTerminals;
	/**
	 * String that represents the name and the variable related to the production
	 */
	private String name;
	
	/**
	 * Constructor method that accepts all the properties as parameters
	 * Not to be used
	 * @param name The name of the production
	 * @param terminals the terminals Set of the production
	 * @param nonTerminals the nonTerminals Set of the production
	 * @return a Production object
	 */
	protected Production( String name,Set<String> terminals, Set<String> nonTerminals) {
		super();
		this.terminals = terminals;
		this.nonTerminals = nonTerminals;
		this.name=name;
	}
	
	/**
	 * This method returns a random terminal or non terminal variable of the production
	 * Used by the random string method in the Grammar class
	 * @return A random production
	 */
	protected String giveRandomProduction() {
		ArrayList<String>production=new ArrayList<>();
		production.addAll(terminals);
		production.addAll(nonTerminals);
		Random rand=new Random();
		return production.get(rand.nextInt(production.size()));
		
	}
	

	

	/**
	 * Constructor method that receives the name of the production as parameter
	 * @param name The name of the production
	 * @return a Production object
	 */
	protected Production(String name) {
		super();
		this.name=name;
		terminals=new HashSet<String>();
		nonTerminals=new HashSet<String>();
	}
	
	/**
	 * Adds a terminal to the terminals Set
	 * @param terminal The terminal to be added
	 */
	protected void addTerminal(String terminal) {
		terminals.add(terminal);
	}
	
	/**
	 * Adds a nonTerminal to the nonTerminals Set
	 * @param variable the nonTerminal to be added
	 */
	protected void addNonTerminal(String variable) {
		nonTerminals.add(variable);
	}
	
	/**
	 * Returns the terminals Set
	 * @return the terminals Set
	 */
	protected Set<String> getTerminals() {
		return terminals;
	}
	
	/**
	 * Sets the terminals Set
	 * @param terminals the terminals Set
	 */
	protected void setTerminals(Set<String> terminals) {
		this.terminals = terminals;
	}
	/**
	 * Returns the nonTerminals Set
	 * @return the nonTerminals Set
	 */
	protected Set<String> getNonTerminals() {
		return nonTerminals;
	}
	/**
	 * Sets the nonTerminals Set
	 * @param the nonTerminals Set
	 */
	protected void setNonTerminals(Set<String> variables) {
		this.nonTerminals = variables;
	}
	/**
	 * Returns the name of the production
	 * @return the name of the production
	 */
	protected String getName() {
		return name;
	}

	/**
	 * Sets the name of the production
	 * @param the name of the production
	 */
	protected void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		String p=name+"->";
		for (String s:nonTerminals) {
			p+=s+" ";
		}
		for (String s:terminals) {
			if(s.equals("")) {
				p+="* ";
			}
			else {
				p+=s+" ";	
			}
		}
		
		return p;
	}
	
	

}
