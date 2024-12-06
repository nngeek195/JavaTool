import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ScriptGeneratorUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScriptGeneratorUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // Main Frame
        JFrame frame = new JFrame("Script Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Load the background image
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("security-break-grim-reaper-hacker.webp");

        // Custom panel with background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Script list panel
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Option 1: Basic Script");
        listModel.addElement("Option 2: Advanced Script");
        listModel.addElement("Option 3: Custom Script");

        JList<String> scriptList = new JList<>(listModel);
        scriptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scriptList.setFont(new Font("Arial", Font.PLAIN, 16));
        scriptList.setBackground(Color.LIGHT_GRAY);

        JScrollPane listScrollPane = new JScrollPane(scriptList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Available Scripts"));

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton selectButton = new JButton("Select");
        JButton detailsButton = new JButton("Details");
        JButton browseButton = new JButton("Browse");
        JButton generateButton = new JButton("Generate");

        buttonPanel.add(selectButton);
        buttonPanel.add(detailsButton);
        buttonPanel.add(browseButton);
        buttonPanel.add(generateButton);

        // Action for Select button
        selectButton.addActionListener(e -> {
            int selectedIndex = scriptList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedScript = listModel.getElementAt(selectedIndex);
                JOptionPane.showMessageDialog(frame, "You selected: " + selectedScript, "Selection",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a script.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        Detail detail = new Detail();
        // Action for Details button
        detailsButton.addActionListener(e -> {
            int selectedIndex = scriptList.getSelectedIndex();
            if (selectedIndex != -1) {
                String details = detail.getDetails(selectedIndex);
                JOptionPane.showMessageDialog(frame, details, "Script Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a script.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // File chooser setup
        JTextField pathTextField = new JTextField();
        pathTextField.setEditable(false);
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
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

        // Action for Generate button
        generateButton.addActionListener(e -> {
            String filePath = pathTextField.getText();
            int selectedIndex = scriptList.getSelectedIndex();

            if (filePath.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select a location to save the VBS script.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a script to generate.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            detail.generateVbsScript(filePath, selectedIndex);
        });

        // Add components to the panel
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
