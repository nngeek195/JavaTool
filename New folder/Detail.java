import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Detail {
  
    public static String getDetails(int selectedIndex) {
        return switch (selectedIndex) {
            case 0 -> "Option 1: This is a basic script that generates a simple VBS file.";
            case 1 -> "Option 2: This script includes advanced functionality for automation.";
            case 2 -> "Option 3: This allows customization of the generated script.";
            default -> "No details available.";
        };
    }



    public static void generateVbsScript(String filePath, int scriptOption) {
        String vbsScriptContent = switch (scriptOption) {
            case 0 ->
                """
                        Set objFSO = CreateObject("Scripting.FileSystemObject")
                        Set objFile = objFSO.CreateTextFile("C:\\Users\\" & CreateObject("WScript.Network").UserName & "\\Desktop\\hello.txt", True)
                        objFile.WriteLine("Hello from Basic Script!")
                        objFile.Close
                        Set objShell = CreateObject("WScript.Shell")
                        objShell.Run "notepad.exe C:\\Users\\" & CreateObject("WScript.Network").UserName & "\\Desktop\\hello.txt"
                        """;
            case 1 -> """
                    MsgBox "Advanced script is under construction!"
                    """;
            case 2 -> """
                    MsgBox "Custom script functionality to be implemented."
                    """;
            default -> "";
        };

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(vbsScriptContent);
            JOptionPane.showMessageDialog(null, "VBS script generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while creating the VBS script.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
}
