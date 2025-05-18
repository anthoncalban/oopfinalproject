import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.util.ArrayList;
import java.util.List;

public class ResumeFrame extends JFrame {
    private ResumeData data;
    private PdfGenerator pdfGenerator;

    public ResumeFrame() {
        setTitle("Resume Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        data = new ResumeData();
        pdfGenerator = new PdfGenerator();

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("RESUME BUILDER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        mainPanel.add(titleLabel, gbc);

        // Left Column: Personal Information, Work Experience, Skills
        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.insets = new Insets(5, 5, 5, 5);
        leftGbc.fill = GridBagConstraints.HORIZONTAL;

        // Personal Information Section
        JLabel personalLabel = new JLabel("PERSONAL INFORMATION:");
        personalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftGbc.gridx = 0;
        leftGbc.gridy = 0;
        leftGbc.gridwidth = 2;
        leftPanel.add(personalLabel, leftGbc);

        String[] personalFields = {
            "FULL NAME:", "AGE:", "SEX:", "DATE OF BIRTH:", 
            "PLACE OF BIRTH:", "CITIZENSHIP:", "HEIGHT:", "WEIGHT:",
            "RELIGION:", "LANGUAGES:"
        };

        for (int i = 0; i < personalFields.length; i++) {
            leftGbc.gridx = 0;
            leftGbc.gridy = i + 1;
            leftGbc.gridwidth = 1;
            leftGbc.anchor = GridBagConstraints.LINE_END;
            leftPanel.add(new JLabel(personalFields[i]), leftGbc);

            leftGbc.gridx = 1;
            leftGbc.anchor = GridBagConstraints.LINE_START;
            JTextField textField = new JTextField(15);
            data.getPersonalFields().add(textField);
            leftPanel.add(textField, leftGbc);
        }

        // Work Experience Section
        JLabel experienceLabel = new JLabel("WORK EXPERIENCE: (IF YOU HAVE)");
        experienceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftGbc.gridx = 0;
        leftGbc.gridy = personalFields.length + 1;
        leftGbc.gridwidth = 2;
        leftGbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(experienceLabel, leftGbc);

        leftGbc.gridx = 0;
        leftGbc.gridy = personalFields.length + 2;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.BOTH;
        JTextArea experienceArea = new JTextArea(5, 25);
        experienceArea.setLineWrap(true);
        experienceArea.setWrapStyleWord(true);
        JScrollPane experienceScroll = new JScrollPane(experienceArea);
        data.setExperienceArea(experienceArea);
        leftPanel.add(experienceScroll, leftGbc);

        // Skills Section
        JLabel skillsLabel = new JLabel("SKILLS:");
        skillsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftGbc.gridx = 0;
        leftGbc.gridy = personalFields.length + 3;
        leftGbc.gridwidth = 2;
        leftGbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(skillsLabel, leftGbc);

        leftGbc.gridx = 0;
        leftGbc.gridy = personalFields.length + 4;
        leftGbc.gridwidth = 2;
        leftGbc.fill = GridBagConstraints.BOTH;
        JTextArea skillsArea = new JTextArea(5, 25);
        skillsArea.setLineWrap(true);
        skillsArea.setWrapStyleWord(true);
        JScrollPane skillsScroll = new JScrollPane(skillsArea);
        data.setSkillsArea(skillsArea);
        leftPanel.add(skillsScroll, leftGbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.33;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(leftPanel, gbc);

        // Middle Column: Contact Information, Objective
        JPanel middlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints midGbc = new GridBagConstraints();
        midGbc.insets = new Insets(5, 5, 5, 5);
        midGbc.fill = GridBagConstraints.HORIZONTAL;

        // Contact Information Section
        JLabel contactLabel = new JLabel("CONTACT INFORMATION:");
        contactLabel.setFont(new Font("Arial", Font.BOLD, 16));
        midGbc.gridx = 0;
        midGbc.gridy = 0;
        midGbc.gridwidth = 2;
        middlePanel.add(contactLabel, midGbc);

        String[] contactFields = {"CONTACT NUMBER:", "EMAIL ADDRESS:", "ADDRESS:"};
        for (int i = 0; i < contactFields.length; i++) {
            midGbc.gridx = 0;
            midGbc.gridy = i + 1;
            midGbc.gridwidth = 1;
            midGbc.anchor = GridBagConstraints.LINE_END;
            middlePanel.add(new JLabel(contactFields[i]), midGbc);

            midGbc.gridx = 1;
            midGbc.anchor = GridBagConstraints.LINE_START;
            JTextField textField = new JTextField(15);
            data.getContactFields().add(textField);
            middlePanel.add(textField, midGbc);
        }

        // Objective Section
        JLabel objectiveLabel = new JLabel("OBJECTIVE:");
        objectiveLabel.setFont(new Font("Arial", Font.BOLD, 16));
        midGbc.gridx = 0;
        midGbc.gridy = contactFields.length + 1;
        midGbc.gridwidth = 2;
        midGbc.anchor = GridBagConstraints.CENTER;
        middlePanel.add(objectiveLabel, midGbc);

        midGbc.gridx = 0;
        midGbc.gridy = contactFields.length + 2;
        midGbc.gridwidth = 2;
        midGbc.fill = GridBagConstraints.BOTH;
        JTextArea objectiveArea = new JTextArea(5, 25);
        objectiveArea.setLineWrap(true);
        objectiveArea.setWrapStyleWord(true);
        JScrollPane objectiveScroll = new JScrollPane(objectiveArea);
        data.setObjectiveArea(objectiveArea);
        middlePanel.add(objectiveScroll, midGbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.33;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(middlePanel, gbc);

        // Right Column: Education
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.insets = new Insets(5, 5, 5, 5);
        rightGbc.fill = GridBagConstraints.HORIZONTAL;

        // Education Section
        JLabel educationLabel = new JLabel("EDUCATION:");
        educationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        rightGbc.gridx = 0;
        rightGbc.gridy = 0;
        rightGbc.gridwidth = 2;
        rightPanel.add(educationLabel, rightGbc);

        // College
        rightGbc.gridx = 0;
        rightGbc.gridy = 1;
        rightGbc.gridwidth = 1;
        rightGbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("COLLEGE SCHOOL:"), rightGbc);
        rightGbc.gridx = 1;
        rightGbc.anchor = GridBagConstraints.LINE_START;
        JTextField collegeNameField = new JTextField(15);
        data.setCollegeNameField(collegeNameField);
        rightPanel.add(collegeNameField, rightGbc);

        rightGbc.gridx = 0;
        rightGbc.gridy = 2;
        rightPanel.add(new JLabel("NAME OF PROGRAM:"), rightGbc);
        rightGbc.gridx = 1;
        JTextField programField = new JTextField(15);
        data.setProgramField(programField);
        rightPanel.add(programField, rightGbc);

        rightGbc.gridx = 0;
        rightGbc.gridy = 3;
        rightPanel.add(new JLabel("ACADEMIC YEAR:"), rightGbc);
        rightGbc.gridx = 1;
        JTextField collegeYearField = new JTextField(15);
        data.setCollegeYearField(collegeYearField);
        rightPanel.add(collegeYearField, rightGbc);

        // Senior High School
        rightGbc.gridx = 0;
        rightGbc.gridy = 4;
        rightPanel.add(new JLabel("SENIOR HIGH SCHOOL:"), rightGbc);
        rightGbc.gridx = 1;
        JTextField shsNameField = new JTextField(15);
        data.setShsNameField(shsNameField);
        rightPanel.add(shsNameField, rightGbc);

        rightGbc.gridx = 0;
        rightGbc.gridy = 5;
        rightPanel.add(new JLabel("NAME OF STRAND:"), rightGbc);
        rightGbc.gridx = 1;
        JTextField strandField = new JTextField(15);
        data.setStrandField(strandField);
        rightPanel.add(strandField, rightGbc);

        rightGbc.gridx = 0;
        rightGbc.gridy = 6;
        rightPanel.add(new JLabel("ACADEMIC YEAR:"), rightGbc);
        rightGbc.gridx = 1;
        JTextField shsYearField = new JTextField(15);
        data.setShsYearField(shsYearField);
        rightPanel.add(shsYearField, rightGbc);

        // Junior High School
        rightGbc.gridx = 0;
        rightGbc.gridy = 7;
        rightPanel.add(new JLabel("JUNIOR HIGH SCHOOL:"), rightGbc);
        rightGbc.gridx = 1;
        JTextField jhsNameField = new JTextField(15);
        data.setJhsNameField(jhsNameField);
        rightPanel.add(jhsNameField, rightGbc);

        rightGbc.gridx = 0;
        rightGbc.gridy = 8;
        rightPanel.add(new JLabel("ACADEMIC YEAR:"), rightGbc);
        rightGbc.gridx = 1;
        JTextField jhsYearField = new JTextField(15);
        data.setJhsYearField(jhsYearField);
        rightPanel.add(jhsYearField, rightGbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.33;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(rightPanel, gbc);

        // Bottom Section: Template Selection and Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        String[] templateOptions = {"Default", "Two-Column"};
        JComboBox<String> templateCombo = new JComboBox<>(templateOptions);
        bottomPanel.add(new JLabel("SELECT TEMPLATE:"));
        bottomPanel.add(templateCombo);

        JButton previewButton = new JButton("Preview");
        previewButton.addActionListener(e -> {
            try {
                data.validateData();
                String selectedTemplate = (String) templateCombo.getSelectedItem();
                previewPDF(selectedTemplate);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        bottomPanel.add(previewButton);

        JButton generateButton = new JButton("GENERATE RESUME!");
        generateButton.addActionListener(e -> {
            try {
                data.validateData();
                String selectedTemplate = (String) templateCombo.getSelectedItem();
                pdfGenerator.generatePDF(this, data, selectedTemplate);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
        bottomPanel.add(generateButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(bottomPanel, gbc);

        add(mainPanel);
        setVisible(true);
    }

    private void previewPDF(String template) {
        try {
            Path tempFile = Files.createTempFile("resume_preview_", ".pdf");
            File tempPDF = tempFile.toFile();
            tempPDF.deleteOnExit();

            pdfGenerator.generatePDFToFile(this, data, template, tempPDF);

            try (PDDocument document = Loader.loadPDF(tempPDF)) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300);

                int maxWidth = 600;
                int maxHeight = 800;
                double scale = Math.min((double) maxWidth / image.getWidth(), (double) maxHeight / image.getHeight());
                int scaledWidth = (int) (image.getWidth() * scale);
                int scaledHeight = (int) (image.getHeight() * scale);
                Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);

                JDialog previewDialog = new JDialog(this, "Resume Preview", true);
                previewDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                previewDialog.setLayout(new BorderLayout());

                JLabel pdfLabel = new JLabel(icon);
                JScrollPane scrollPane = new JScrollPane(pdfLabel);
                previewDialog.add(scrollPane, BorderLayout.CENTER);

                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(e -> previewDialog.dispose());
                previewDialog.add(closeButton, BorderLayout.SOUTH);

                previewDialog.setSize(scaledWidth + 20, scaledHeight + 80);
                previewDialog.setLocationRelativeTo(this);
                previewDialog.setVisible(true);

                document.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating preview: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResumeFrame::new);
    }
}