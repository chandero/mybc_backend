package commons;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Aplicador {

    public static List<String> aplicar() {
        String command = "sudo /etc/asterisk/aplicar.sh";
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
		List<String> output = new ArrayList<String>();		
		if (OS.indexOf("win") >= 0){
			try {
				Runtime runtime = Runtime.getRuntime();
				String[] cmd = { "cmd", "/c"," "+command };
				Process child = runtime.exec(cmd);
				InputStreamReader irs = new InputStreamReader(child.getInputStream());
				BufferedReader br = new BufferedReader(irs);
				String line;
				while ((line = br.readLine()) != null) {
					output.add(line);
				}
				  irs.close();
	              br.close();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		} else {
		//TODO Quitar comentario o agregar para modo desarrollo
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] cmd = { "sudo", "", command };
			Process child = runtime.exec(cmd);
			InputStreamReader irs = new InputStreamReader(child.getInputStream());
			BufferedReader br = new BufferedReader(irs);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("line >> "+line);
				output.add(line);
			}
			  irs.close();
              br.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		}
		return output;
    }
}