// TODO: comment this program

import acm.program.*;

public class ExamScores extends ConsoleProgram {
	public void run() {
	int sum = 0;
int n = readInt("Type a number: ");
while (n != 10) {
sum += n;
println("Sum is " + sum);
n = readInt("Type a number: ");
}
println("2Sum is " + sum);	
	}
}
