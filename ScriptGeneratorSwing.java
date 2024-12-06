import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScriptGeneratorSwing {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Script Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create components
        JLabel label = new JLabel("Select location to save the VBS script:");
        JTextField pathTextField = new JTextField();
        pathTextField.setEditable(false);
        JButton browseButton = new JButton("Browse...");
        JButton generateButton = new JButton("Generate Script");

        // File chooser for selecting file location
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Add action listener for Browse button
        browseButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Save VBS File");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("VBS Files", "vbs"));
            int userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getAbsolutePath();
                if (!filePath.endsWith(".vbs")) {
                    filePath += ".vbs";
                }
                pathTextField.setText(filePath);
            }
        });

        // Add action listener for Generate Script button
        generateButton.addActionListener(e -> {
            String filePath = pathTextField.getText();
            if (!filePath.isEmpty()) {
                generateVbsScript(filePath);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a location to save the VBS script.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Layout setup
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(label);
        panel.add(pathTextField);
        panel.add(browseButton);
        panel.add(generateButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void generateVbsScript(String filePath) {
        String vbsScriptContent = """
                Set objFSO = CreateObject("Scripting.FileSystemObject")
                Set objFile = objFSO.CreateTextFile("C:\\Users\\" & CreateObject("WScript.Network").UserName & "\\Desktop\\hello.txt", True)
                objFile.WriteLine("Hello Niranga!")
                objFile.Close
                Set objShell = CreateObject("WScript.Shell")
                objShell.Run "notepad.exe C:\\Users\\" & CreateObject("WScript.Network").UserName & "\\Desktop\\hello.txt"
                """;

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(vbsScriptContent);
            JOptionPane.showMessageDialog(null, "VBS script generated successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while creating the VBS script.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
