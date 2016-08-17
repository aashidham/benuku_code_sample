// TODO: comment this file

import java.util.*;
import java.io.*;
import stanford.cs106.audio.*;

public class Melody implements MelodyInterface {

	public Melody(Scanner input) {
		// TODO: implement this constructor
		
	}
	
	public void changeDuration(double ratio) {
		// TODO: implement this method

	}
	
	public String getArtist() {
		// TODO: implement this method
		return "";
	}

	public String getTitle() {
		// TODO: implement this method
		return "";
	}
	
	public double getTotalDuration() {
		// TODO: implement this method
		return 0.0;
	}
	
	public boolean octaveDown() {
		// TODO: implement this method
		return false;
	}
	
	public boolean octaveUp() {
		// TODO: implement this method
		return false;
	}
	
	public void play() {
		// TODO: implement this method
      	Note note = new Note(0.5, false);
		
	}
	
	public void reverse() {
		// TODO: implement this method
		
	}
	
	public String toString() {
		// TODO: implement this method (optional)
		return "";
	}
}
