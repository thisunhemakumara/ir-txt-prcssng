package ir.text.processing.file_processor;

import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class HelloWorld {

    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v5.0/spellcheck";

    // NOTE: Replace this example key with a valid subscription key.
    static String key = "7effb064209141f294f4ad77a090d3a9";

    static String mkt = "en-US";
    static String mode = "proof";
    static String text = "Hollo, wrld!";

    public static void check (String sText) throws Exception {
    	
    	try {
			
    		//text=sText;
            String params = "?mkt=" + mkt + "&mode=" + mode;
            //URL url = new URL(host + path + params);
            URL url = new URL("https://api.cognitive.microsoft.com/bing/v5.0/spellcheck?mkt=en-us&mode=proof");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + text.length() + 5);
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes("text=" + text);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    public static void main(String[] args) {
        try {
            check ("Neural network models have shown their promising opportunities for multi-task learning , which focus on learning the shared layers to extract the common and task-invariant features .");
        }
        catch (Exception e) {
            System.out.println (e);
        }
    }
}