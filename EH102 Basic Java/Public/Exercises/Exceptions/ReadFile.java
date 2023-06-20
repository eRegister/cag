import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args) {
		
		try {
			readFile();
		} catch(IOException e) {
			System.out.println("Problems with File Reading");
		}
	}
	
	static void readFile() throws IOException {
		
		File file = new File("myFile.txt");
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			br.close();
			System.out.println("File was read.");
			
		} catch(FileNotFoundException fileNotFoundEx) {
			System.out.println("File Not Found");
			
			// Do some fancy recovery stuff
			if(file.createNewFile()) {
				System.out.println("File Created");
			} else {
				throw fileNotFoundEx;
			}
			
		} catch(IOException ioEx) {
			System.out.println("Problems with File Reading");
		}
	}
}
