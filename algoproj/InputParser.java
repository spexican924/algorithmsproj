/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author jack
 */
public class InputParser {

    public static InitGraph mainGraph;
    public static double startTime;
    public static double endTime;

    public static InitGraph createMatrix(String fileName) {

	try {

	    File in = new File("/Users/jack/Box Sync/UMKC3/Algorithms/algoproj/input/" + fileName);
	    BufferedReader br = new BufferedReader(new FileReader(in));
	    Boolean readBool = true;

	    mainGraph = new InitGraph();
	    mainGraph.startParse = System.nanoTime();
	    String lineInString = br.readLine();
	    String[] currLine = lineInString.split(",");
	    if (currLine.length == 3) {
		mainGraph.size = Integer.parseInt(currLine[0].trim());
		mainGraph.start = (Integer.parseInt(currLine[1].trim()) - 1);
		mainGraph.end = (Integer.parseInt(currLine[2].trim()) - 1);
	    }
	    else {
		throw new Exception("Weird first line");
	    }
	    mainGraph.edgeGraph = new Integer[mainGraph.size][mainGraph.size];
	    mainGraph.flowGraph = new Integer[mainGraph.size][mainGraph.size];

	    for (Integer i = 0; i < mainGraph.size; i++) {
		for (Integer j = 0; j < mainGraph.size; j++) {
		    mainGraph.edgeGraph[i][j] = 0;
		    mainGraph.flowGraph[i][j] = 0;
		}
	    }
	    Integer start, end, weight;
	    String type = null;
	    while (readBool) {
		lineInString = br.readLine();
		if (lineInString == null) {
		    readBool = false;
		    break;
		}
		else if (lineInString.equals("")) {

		}
		else {
		    currLine = lineInString.split(",");
		    if (currLine.length == 4) {
			type = currLine[0].trim();
			start = Integer.parseInt(currLine[1].trim()) - 1;
			end = Integer.parseInt(currLine[2].trim()) - 1;
			weight = Integer.parseInt(currLine[3].trim());
			if (type.equals("E")) {
			    mainGraph.edgeGraph[start][end] = weight;
			}
			else if (type.equals("F")) {
			    mainGraph.flowList.add(new Path(start, end, weight));
			    mainGraph.flowGraph[start][end] = weight;

			}
			else {
			    System.out.println(lineInString);
			}
		    }
		}
	    }
	    mainGraph.adjacent = new Integer[mainGraph.size][mainGraph.size];
	    for (Integer i = 0; i < mainGraph.size; i++) {
		for (Integer j = 0; j < mainGraph.size; j++) {
		    if (mainGraph.edgeGraph[i][j] != 0) {
			mainGraph.adjacent[i][j] = 1;
		    }
		    else {
			mainGraph.adjacent[i][j] = 0;
		    }
		}
	    }
	    br.close();
	    mainGraph.endParse = System.nanoTime();
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
	return mainGraph;

    }
}
