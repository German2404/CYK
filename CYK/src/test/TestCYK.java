package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import model.CYKCore;
import model.Grammar;

class TestCYK {

	@Test
	void testGrammar2() {

		String grammar2 = "S BA AC\n" + "A CC b\n" + "B AB a\n" + "C BA a\n";

		Grammar g;
		try {
			g = Grammar.grammarParser(grammar2);
			System.out.println(g.toString());

				for (int i = 0; i < 5; i++) {
					String string=g.getRandomString();
					System.out.println(string);
					assertTrue(CYKCore.CYK(g, string));
				}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	
	@Test
	void testGrammar1() {

		String grammar=
				"S YB XA *\n" + 
				"E YB XA\n" + 
				"A a YE XC\n" + 
				"B b XE YZ\n" + 
				"C AA\n" + 
				"X b\n" + 
				"Y a\n" + 
				"Z BB";

		Grammar g;
		try {
			g = Grammar.grammarParser(grammar);
			System.out.println(g.toString());

				for (int i = 0; i < 5; i++) {
					String string=g.getRandomString();
						System.out.println(string);
						assertTrue(CYKCore.CYK(g, string));
						

				}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	
	@Test
	void testGrammar3() {

		String grammar=
				"S c YB ZB\n" + 
				"X ZB c\n" + 
				"A a\n" + 
				"B b\n" + 
				"C c\n" + 
				"Y AC\n" + 
				"Z AX";

		Grammar g;
		try {
			g = Grammar.grammarParser(grammar);
			System.out.println(g.toString());

				for (int i = 0; i < 5; ) {
					String string=g.getRandomString();
					if(!string.equals("")) {
						
						System.out.println(string);
						assertTrue(CYKCore.CYK(g, string));
						i++;
					}

				}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
