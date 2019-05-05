Autors: German Carvajal and Juan David Carvajal
Java implementation of the CYK Algorithm

The instructions for using the program are pretty simple
On the Grammar TextArea, the user must enter a Grammar represented as a String using the following format.

S YB XA *
E YB XA
A a YE XC
B b XE YZ
C AA
X b
Y a
Z BB

Notice that each production of the grammar is separeted by a line jump,
Also, each production is divided in the next format:

The first Upper Case letter is the name of the production.
Next, a space follows, denoting the start of the terminals and nonTerminals of the production.
Each terminal must be a single lower case letter, using the character * instead of lambda.
Each nonTerminal must be two upper case letters.
Terminals and nonTerminals are separeted by a space.
There is no extra space at the end of each line. This is, after the last term of each production, there is a line jump.
There should be no extra line jumps at the end of the last production.

Next, enter the string you want to check for the grammar to be able to produce
This string should be of length 0 to n, contain only lower case letters, and have no special characters.

At last, hit the Run CYK button. A prompt will be shown indicating if the word can be producen by the grammar.