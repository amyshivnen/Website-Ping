package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Website {
    public static void main(String[] args) {
        // Specify the website to ping
        String website = "www.zara.com"; 

        // Specify the number of pings to be sent
        int numberOfPings = 10;

        // Get the operating system name in lowercase
        String os = System.getProperty("os.name").toLowerCase();

        // Declare a ProcessBuilder to run the ping command
        ProcessBuilder processBuilder = null;

        // Check if the operating system is Windows
        if (os.contains("win")) {
            // For Windows, construct the command using cmd.exe
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "ping", "-n", String.valueOf(numberOfPings), website);
        }

        try {
            // Start the process
            Process process = processBuilder.start();

            // Print the output from both InputStream and ErrorStream
            printOutput(process.getInputStream());
            printOutput(process.getErrorStream());

            // Wait for the process to finish and get the exit code
            int exitCode = process.waitFor();
            
            // Print the exit code of the process
            System.out.println("Exit Code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    // Helper method to print output from InputStream
    private static void printOutput(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            // Print each line of the output
            System.out.println(line);
        }
    }
}
