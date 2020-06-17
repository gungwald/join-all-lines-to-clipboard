package com.alteredmechanism.apps;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LineJoiner {

	/**
	 * Executions start here
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		try {
		    LineJoiner j = new LineJoiner();
            j.joinLinesFromInputSources(args);
		} 
        catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void joinLinesFromInputSources(String[] args) throws IOException {
        if (args.length > 0) {
            // Read lines from files named on the command line.
            for (int i = 0; i < args.length; i++) {
                joinLines(new File(args[i]));
            }
        } 
        else {
            // Read lines from standard input.
            joinLines(System.in);
        }
    }

    public void joinLines(File f) throws IOException {
        joinLines(new BufferedReader(new FileReader(f)));
    }

    public void joinLines(InputStream in) throws IOException {
        joinLines(new BufferedReader(new InputStreamReader(in)));
    }

	public void joinLines(BufferedReader in) throws IOException {
		try {
			StringBuffer joinedLines = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
                joinedLines.append(line.trim()).append(' ');
			}
			// Delete unnecessary trailing space.
			joinedLines.deleteCharAt(joinedLines.length() - 1);
			System.out.println(joinedLines);
			copyToClipboard(joinedLines.toString());
		} 
        finally {
			in.close();
		}
	}

	public void copyToClipboard(String text) {
		StringSelection selection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
}
