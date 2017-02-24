import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JoinAllLinesToClipboard {

	/**
	 * Executions start here
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		JoinAllLinesToClipboard j = new JoinAllLinesToClipboard();
		for (String arg : args) {
			try {
				j.joinAllLinesToClipboard(arg);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void joinAllLinesToClipboard(String fileName) throws IOException {
		copyToClipboard(joinAllLines(fileName));
	}

	private void copyToClipboard(String text) {
		StringSelection selection = new StringSelection(text);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	    System.out.printf("Sent to clipboard: %s%n", text);
	}

	private String joinAllLines(String fileName) throws IOException {
		StringBuilder allLinesOnASingleLine = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		try {
			String line;
			while ((line = in.readLine()) != null) {
				allLinesOnASingleLine.append(line.trim());
				allLinesOnASingleLine.append(' ');
			}
		}
		finally {
			in.close();
		}
		return allLinesOnASingleLine.toString();
	}

}
