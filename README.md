LinkedIn challenge at https://www.workatlinkedin.com/

![WorkAtLinkedIn.com](https://github.com/RaffaeleSgarro/workatlinkedin/blob/master/workatlinkedin.com.png)

This repository contains Java code and Intellij files. Dependencies:
- JDK 8
- Intellij 15

There are shared Intellij launch configurations.

The main class is `workatlinkedin.WorkAtLinkedInSolution` and prints the solution to stdout.

## Program flow description

The set of all possible matching strings is generated for each regex hint. This
is called a `Dictionary` in the source code.

Then all possible combinations of words are tested. To reduce the number of trees walked
while testing words in dictionaries, hints are sorted by the size of their dictionary.
