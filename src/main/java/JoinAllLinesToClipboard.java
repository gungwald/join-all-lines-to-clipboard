import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JoinAllLinesToClipboard {

	/**
	 * Executions start here
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		JoinAllLinesToClipboard j = new JoinAllLinesToClipboard();

		try {
			if (args.length > 0) {
				// Read lines from files named on the command line.
				for (int i = 0; i < args.length; i++) {
					j.joinAllLinesToClipboard(args[i]);
				}
			} else {
				// Read lines from standard input.
				j.joinAllLinesToClipboard(System.in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void joinAllLinesToClipboard(InputStream in) throws IOException {
		copyToClipboard(joinAllLines(in));
	}

	public void joinAllLinesToClipboard(String fileName) throws IOException {
		copyToClipboard(joinAllLines(fileName));
	}

	private String joinAllLines(InputStream in) throws IOException {
		return joinAllLines(new BufferedReader(new InputStreamReader(in)));
	}

	private String joinAllLines(BufferedReader in) throws IOException {
		StringBuilder allLinesOnASingleLine = new StringBuilder();
		try {
			String line;
			while ((line = in.readLine()) != null) {
				allLinesOnASingleLine.append(line.trim());
				allLinesOnASingleLine.append(' ');
			}
		} finally {
			in.close();
		}
		return allLinesOnASingleLine.toString();
	}

	private void copyToClipboard(String text) {
		StringSelection selection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		System.out.println("Sent to clipboard: " + text);
	}

	private String joinAllLines(String fileName) throws IOException {
		return joinAllLines(new BufferedReader(new FileReader(fileName)));
	}

}
