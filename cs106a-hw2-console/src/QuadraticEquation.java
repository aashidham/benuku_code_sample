// TODO: comment this program

import acm.program.*;

public class QuadraticEquation extends ConsoleProgram {
	public void run() {
		// TODO: finish this program
      println("This program solves a quadratic equation of form ax^2 + bx + c = 0"); 
      double a = readDouble("Enter a here: "); 
      double b = readDouble("Enter b here: "); 
      double c = readDouble("Enter c here: "); 
      double discriminant = (b*b)-4*a*c; 
      double sqrtd = Math.sqrt(discriminant); 
      if (discriminant > 0) {
        double x1 = (-b+sqrtd)/(2*a); 
        double x2 = (-b-sqrtd)/(2*a); 
        println("The solutions are: " + x1 + " and " + x2 + "."); 
      }
      if (discriminant == 0) {
       double x = -b/(2*a); 
        println("The solution is: " + x + "."); 
      }
      if (discriminant < 0) {
        double real = -b/(2*a);
        double imaginary1 = 1 ;
        double imaginary2 = 1 ;
        println("The solution is imaginary."); 
      }

	}
}