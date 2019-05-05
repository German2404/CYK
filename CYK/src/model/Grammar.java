package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *Class representing a Grammar
 */
public class Grammar {
	
	/**
	 * The initial production of the grammar. It is also included in the productions Set
	 */
	private Production initial;
	/**
	 * Set with all the productions of the grammar, including the initial.
	 * Usually implemented as a HashSet
	 */
	private Set<Production> productions;
	
	/**
	 * Constructor of the Grammar class that accepts all properties as parameters.
	 * Not to be used
	 * @param initial The initial production of the Grammar
	 * @param productions The productions Set of the Grammar
	 * @return a Grammar object
	 */
	private Grammar(Production initial, Set<Production> productions) {
		super();
		this.initial = initial;
		this.productions = productions;
	}
	
	/**
	 * Constructor of the class grammar. Initializes the properties with no productions nor initial production.
	 * @return a Grammar object
	 */
	private Grammar() {
		super();
		this.initial=null;
		this.productions=new HashSet<Production>();
	}
	
	/**
	 * Adds a Production to the productions Set
	 * @param production the production to be added
	 */
	protected void addProduction(Production production) {
		productions.add(production);
	}
	
	/**
	 * Returns the initial Production of the grammar
	 * @return the initial production of the grammar
	 */
	protected Production getInitial() {
		return initial;
	}
	/**
	 * Sets the initial Production of the grammar
	 * @param initial the initial production to set
	 */
	protected void setInitial(Production initial) {
		this.initial = initial;
	}
	/**
	 * Returns the productions Set
	 * @return the productions Set of the Grammar
	 */
	protected Set<Production> getProductions() {
		return productions;
	}
	/**
	 * Sets the productions Set
	 * @param productions the productions Set to be set
	 */
	protected void setProductions(Set<Production> productions) {
		this.productions = productions;
	}
	
	/**
	 * Generates and returns a random string that can be produced by the grammar.
	 * @return a String that can be produced by the grammar
	 */
	public String getRandomString(){
		ArrayList<String> string=new ArrayList<>();
		Production currentProduction=this.initial;
		String currentString=currentProduction.giveRandomProduction();
		
		for (int i = 0; i < currentString.length(); i++) {
			string.add(currentString.charAt(i)+"");
		}

		
		
		for (int i = 0; i < string.size(); ) {
			if(isVariable(string.get(i))) {
				currentProduction=this.findProductionByName(string.get(i));
				currentString=currentProduction.giveRandomProduction();
				string.remove(i);
				for (int j = 0; j < currentString.length(); j++) {
					string.add(i+j,currentString.charAt(j)+"");
				}
			}else {
				i++;
			}
		}
	
		
		String generated="";
		for (int i = 0; i < string.size(); i++) {
			if(!string.get(i).equals("*")) {
				generated+=string.get(i);
			}
		}
		return generated;
	}
	
	/**
	 * Determines if a string can be classified as a variables by checking if it is an upper case letter
	 * @param var the string to be classified
	 * @return true if its variable, false if not
	 */
	private boolean isVariable(String var) {
		return(var.charAt(0)>=65 && var.charAt(0)<=90);
	}
	
	/**
	 * This method receives a multiple line String that represents the Grammar and returns a Grammar according to such input.
	 * Pre condition: Grammar must be in CNF and must follow the format given in the readme.txt file
	 * @param stringGrammar the Grammar in String form
	 * @return a Grammar object
	 * @throws Exception if the stringGrammar is not entered according to format
	 */
	public static Grammar grammarParser(String stringGrammar) throws Exception {
		Grammar anwser=new Grammar();
		String[] lines=stringGrammar.split("\n");
		for (int i = 0; i < lines.length; i++) {
			String production[]=lines[i].split(" ");
			Production p=new Production(production[0]);
			for (int j = 1; j < production.length; j++) {
				if(production[j].length()==1) {
					if(production[j].equals("*")) {
						production[j]="";
					}
					p.addTerminal(production[j]);
				}
				else if(production[j].length()==2) {
					p.addNonTerminal(production[j]);
				}
				else {
					throw new Exception("Parse error");
				}
			}
			if(i==0) {
				anwser.setInitial(p);
			}
			anwser.addProduction(p);
			
		}
		return anwser;
	}
	
	/**
	 * Finds and returns the production with the given name.
	 * Returns null if it does not exist.
	 * @param name the name of the production to look
	 * @return the Production looked, false if not found
	 */
	private Production findProductionByName(String name){
		Production toLook=null;
		for(Production p: this.productions) {
			if(p.getName().equals(name)) {
				toLook=p;
				break;
			}
		}
		return toLook;
	}

	@Override
	public String toString() {
		String s=initial.toString()+"\n";
		for(Production p: productions) {
			if(!p.getName().equals(initial.getName())) {
				
				s+=p.toString()+"\n";
			}
		}
		return s;
	}
	
	
	
	
	
	
	
	

}
