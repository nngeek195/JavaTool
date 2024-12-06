import java.io.FileWriter;
import java.io.IOException;

public class ScriptGenerator {

    // Main method to run the program
    public static void main(String[] args) {
        // Define file path for the .vbs script
        String scriptPath = "D:\\run_me.vbs"; // Replace with your desired file path

        // VBS script content (can be modified as needed)
        String vbsScriptContent = """
                Set objFSO = CreateObject("Scripting.FileSystemObject")
                Set objFile = objFSO.CreateTextFile("C:\\Users\\" & CreateObject("WScript.Network").UserName & "\\Desktop\\hello.txt", True)
                objFile.WriteLine("Hello Niranga!")
                objFile.Close
                Set objShell = CreateObject("WScript.Shell")
                objShell.Run "notepad.exe C:\\Users\\" & CreateObject("WScript.Network").UserName & "\\Desktop\\hello.txt"
                """;

        // Create the .vbs file
        try (FileWriter writer = new FileWriter(scriptPath)) {
            writer.write(vbsScriptContent);
            System.out.println("VBS script generated successfully at: " + scriptPath);
        } catch (IOException e) {
            System.err.println("Error creating VBS script: " + e.getMessage());
        }
    }
}
