/*
 * CS 106A NameSurfer
 *
 ****************************************************************************
 *                              PLEASE READ!                                *
 * If you want to add some more constants to your program, that's great.    *
 * But do not add them here to this file.                                   *
 * Go add them to your own files, like NameSurfer.java.                     *
 * When you turn in your program, we will wipe out NameSurferConstants.java *
 * and grade you using our default version.                                 *
 ****************************************************************************
 *
 * This instructor-provided file declares several constants that you should use
 * in your code files throughout this program.
 * 
 * Author : Marty Stepp
 * Version: Tue 2016/05/22
 * - version for 16sp; 5-year window and other small changes
 * Version: Tue 2015/05/26
 * - slight changes for 15sp; added _SMALL and _TINY ranks filename constants
 * Version: Tue 2014/05/22
 * - original version for 14sp
 *
 * Your program should work properly with an UNMODIFIED version of this file.
 * If you want to modify this file for testing or for fun, that is your choice,
 * but when we grade your program we will do so with the original unmodified
 * version of this file, so your code must still work properly with that code.
 * 
 * This file and its contents are copyright (C) Stanford University and Marty Stepp,
 * licensed under Creative Commons Attribution 2.5 License.  All rights reserved.
 */

import java.awt.*;

public interface NameSurferConstants {
	/* filename from which to read name ranking data */
	public static final String RANKS_FILENAME = "res/ranks.txt";
	public static final String RANKS_SMALL_FILENAME = "res/ranks-small.txt";
	public static final String RANKS_TINY_FILENAME = "res/ranks-tiny.txt";
	
	/* filename from which to read name meaning data */
	public static final String MEANINGS_FILENAME = "res/meanings.txt";
	
	/* starting year for which there is ranking data */
	public static final int MIN_YEAR = 1880;
	
	/* gap between years in ranking data */
	public static final int MAX_YEAR = 2015;
	
	/* gap between years in ranking data */
	public static final int YEAR_GAP = 5;
	
	/* length of the range between max/min years inclusive */
	public static final int INTERVALS_OF_DATA = (int) Math.ceil((MAX_YEAR - MIN_YEAR) / YEAR_GAP) + 1;
	
	/* the largest rank value that should be displayed in the graph */
	public static final int MAX_RANK_TO_DISPLAY = 2000;


	// BEGIN CONSTANTS THAT ARE NO LONGER USED //
	
	/* the color in which to display multiple names on the graph */
	public static final Color[] NAME_COLORS = new Color[] {
		Color.BLUE,
		Color.RED.darker(),
		Color.GREEN.darker(),
		Color.MAGENTA,
		Color.ORANGE,
		Color.YELLOW.darker(),
		Color.DARK_GRAY,
		new Color(255, 128, 128),   // pink
		Color.CYAN.darker(),
		new Color(100, 100, 0)      // brown
	};
}
