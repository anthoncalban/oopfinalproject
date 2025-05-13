import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class ResumeBuilder {

    public static void main(String[] args) {
        JFrame frame = new JFrame("RESUME BUILDER");
        frame.setSize(700, 900);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("RESUME BUILDER");
        title.setBounds(550, 10, 200, 30);
        title.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 20));
        frame.add(title);

        JLabel personalInfoLabel = new JLabel("PERSONAL INFORMATION:");
        personalInfoLabel.setBounds(30, 50, 200, 25);
        personalInfoLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        frame.add(personalInfoLabel);

        String[] personalFields = {
            "FULL NAME:", "AGE:", "SEX:", "DATE OF BIRTH:", 
            "PLACE OF BIRTH:", "CITIZENSHIP:", "HEIGHT:", "WEIGHT:",
            "RELIGION:", "LANGUAGES:"
        };
        
        JTextField[] personalTextFields = new JTextField[personalFields.length];
        for (int i = 0; i < personalFields.length; i++) {
            JLabel label = new JLabel(personalFields[i]);
            label.setBounds(30, 80 + i * 30, 150, 25);
            frame.add(label);
            
            personalTextFields[i] = new JTextField();
            personalTextFields[i].setBounds(180, 80 + i * 30, 200, 25);
            frame.add(personalTextFields[i]);
        }
        JLabel contactInfoLabel = new JLabel("CONTACT INFORMATION:");
        contactInfoLabel.setBounds(400, 50, 200, 25);
        contactInfoLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        frame.add(contactInfoLabel);

        String[] contactFields = {
            "CONTACT NUMBER:", "EMAIL ADDRESS:", "ADDRESS:"
        };
        
        JTextField[] contactTextFields = new JTextField[contactFields.length];
        for (int i = 0; i < contactFields.length; i++) {
            JLabel label = new JLabel(contactFields[i]);
            label.setBounds(390, 80 + i * 30, 150, 25);
            frame.add(label);
            
            contactTextFields[i] = new JTextField();
            contactTextFields[i].setBounds(520, 80 + i * 30, 200, 25);
            frame.add(contactTextFields[i]);
        }

        JLabel objectiveLabel = new JLabel("OBJECTIVE:");
        objectiveLabel.setBounds(400, 190, 200, 25);
        objectiveLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        frame.add(objectiveLabel);

        JTextField objectiveText = new JTextField();
        objectiveText.setBounds(400, 230, 320, 100);
        frame.add(objectiveText);

        JLabel experienceLabel = new JLabel("EXPERIENCE: (IF YOU HAVE)");
        experienceLabel.setBounds(30, 390, 200, 25);
        experienceLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        frame.add(experienceLabel);

        JLabel workExpLabel = new JLabel("WORK EXPERIENCE:");
        workExpLabel.setBounds(30, 420, 150, 25);
        String[] experienceOptions = {"<1 yrs", "1-3 yrs", "3-5 yrs", "5+ yrs"};
        JComboBox<String> experienceCombo = new JComboBox<>(experienceOptions);
        experienceCombo.setBounds(180, 420, 100, 25);
        frame.add(workExpLabel);
        frame.add(experienceCombo);

        JLabel skillsLabel = new JLabel("SKILLS:");
        skillsLabel.setBounds(30, 470, 100, 25);
        skillsLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        frame.add(skillsLabel);
        
        JTextField skillField1 = new JTextField();
        skillField1.setBounds(30, 500, 150, 25);
        frame.add(skillField1);

        JTextField skillField2 = new JTextField();
        skillField2.setBounds(30, 530, 150, 25);
        frame.add(skillField2);

        JTextField skillField3 = new JTextField();
        skillField3.setBounds(210, 500, 150, 25);
        frame.add(skillField3);

        JTextField skillField4 = new JTextField();
        skillField4.setBounds(210, 530, 150, 25);
        frame.add(skillField4);

        JLabel educationLabel = new JLabel("EDUCATION:");
        educationLabel.setBounds(800, 50, 200, 25);
        educationLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        frame.add(educationLabel);

        JLabel collegeLabel = new JLabel("COLLEGE SCHOOL:");
        collegeLabel.setBounds(800, 80, 150, 25);
        frame.add(collegeLabel);
        
        JLabel collegeNameLabel = new JLabel("UNIVERSITY NAME:");
        collegeNameLabel.setBounds(800, 110, 150, 25);
        frame.add(collegeNameLabel);

        JTextField collegeNameField = new JTextField();
        collegeNameField.setBounds(930, 110, 300, 25);
        frame.add(collegeNameField);
        
        JLabel programLabel = new JLabel("NAME OF PROGRAM:");
        programLabel.setBounds(800, 140, 150, 25);
        frame.add(programLabel);
        
        JTextField programField = new JTextField();
        programField.setBounds(930, 140, 300, 25);
        frame.add(programField);
        
        JLabel collegeYearLabel = new JLabel("ACADEMIC YEAR:");
        collegeYearLabel.setBounds(800, 170, 150, 25);
        frame.add(collegeYearLabel);
        
        JTextField collegeYearField = new JTextField();
        collegeYearField.setBounds(930, 170, 300, 25);
        frame.add(collegeYearField);

        JLabel shsLabel = new JLabel("SENIOR HIGH SCHOOL:");
        shsLabel.setBounds(800, 220, 150, 25);
        frame.add(shsLabel);

        JLabel shsNameLabel = new JLabel("SCHOOL NAME:");
        shsNameLabel.setBounds(800, 250, 150, 25);
        frame.add(shsNameLabel);
        
        JTextField shsNameField = new JTextField();
        shsNameField.setBounds(930, 250, 300, 25);
        frame.add(shsNameField);
        
        JLabel strandLabel = new JLabel("NAME OF STRAND:");
        strandLabel.setBounds(800, 280, 150, 25);
        frame.add(strandLabel);
        
        JTextField strandField = new JTextField();
        strandField.setBounds(930, 280, 300, 25);
        frame.add(strandField);
        
        JLabel shsYearLabel = new JLabel("ACADEMIC YEAR:");
        shsYearLabel.setBounds(800, 310, 150, 25);
        frame.add(shsYearLabel);
        
        JTextField shsYearField = new JTextField();
        shsYearField.setBounds(930, 310, 300, 25);
        frame.add(shsYearField);

        JLabel elemLabel = new JLabel("ELEMENTARY SCHOOL:");
        elemLabel.setBounds(800, 360, 150, 25);
        frame.add(elemLabel);

        JLabel elemNameLabel = new JLabel("SCHOOL NAME:");
        elemNameLabel.setBounds(800, 390, 150, 25);
        frame.add(elemNameLabel);
        
        JTextField elemNameField = new JTextField();
        elemNameField.setBounds(930, 390, 300, 25);
        frame.add(elemNameField);
        
        JLabel elemYearLabel = new JLabel("ACADEMIC YEAR:");
        elemYearLabel.setBounds(800, 420, 150, 25);
        frame.add(elemYearLabel);
        
        JTextField elemYearField = new JTextField();
        elemYearField.setBounds(930, 420, 300, 25);
        frame.add(elemYearField);

        JButton generateBtn = new JButton("GENERATE RESUME!");
        generateBtn.setBounds(800, 500, 200, 30);
        frame.add(generateBtn);

        generateBtn.addActionListener(e -> {
            try {
                Document document = new Document();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Resume as PDF");
                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!filePath.endsWith(".pdf")) {
                        filePath += ".pdf";
                    }
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();

                    com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(
                        com.itextpdf.text.Font.FontFamily.HELVETICA, 
                        20, 
                        com.itextpdf.text.Font.BOLD
                    );
                    
                    com.itextpdf.text.Font sectionFont = new com.itextpdf.text.Font(
                        com.itextpdf.text.Font.FontFamily.HELVETICA, 
                        14, 
                        com.itextpdf.text.Font.BOLD
                    );
                    
                    com.itextpdf.text.Font textFont = new com.itextpdf.text.Font(
                        com.itextpdf.text.Font.FontFamily.HELVETICA, 
                        12, 
                        com.itextpdf.text.Font.NORMAL
                    );
                    Paragraph titlePara = new Paragraph(personalTextFields[0].getText(), titleFont);
                    titlePara.setAlignment(Element.ALIGN_CENTER);
                    document.add(titlePara);
                    document.add(Chunk.NEWLINE);

                    // Fixed Objective Section
                    document.add(new Paragraph("OBJECTIVE", sectionFont));
                    document.add(new Paragraph(objectiveText.getText(), textFont));
                    document.add(Chunk.NEWLINE);

                    document.add(new Paragraph("PERSONAL INFORMATION", sectionFont));
                    for (int i = 0; i < personalFields.length; i++) {
                        document.add(new Paragraph(personalFields[i] + " " + personalTextFields[i].getText(), textFont));
                    }
                    document.add(Chunk.NEWLINE);

                    document.add(new Paragraph("CONTACT INFORMATION", sectionFont));
                    for (int i = 0; i < contactFields.length; i++) {
                        document.add(new Paragraph(contactFields[i] + " " + contactTextFields[i].getText(), textFont));
                    }
                    document.add(Chunk.NEWLINE);

                    document.add(new Paragraph("WORK EXPERIENCE: " + experienceCombo.getSelectedItem(), sectionFont));
                    document.add(Chunk.NEWLINE);

                    document.add(new Paragraph("SKILLS: " + skillField1.getText(), sectionFont));
                    document.add(Chunk.NEWLINE);

                    document.add(new Paragraph("EDUCATION", sectionFont));
                    document.add(new Paragraph("COLLEGE: " + collegeNameField.getText() + 
                        " - " + programField.getText() + " (" + collegeYearField.getText() + ")", textFont));
                    document.add(new Paragraph("SENIOR HIGH: " + shsNameField.getText() + 
                        " - " + strandField.getText() + " (" + shsYearField.getText() + ")", textFont));
                    document.add(new Paragraph("ELEMENTARY: " + elemNameField.getText() + 
                        " (" + elemYearField.getText() + ")", textFont));

                    document.close();
                    JOptionPane.showMessageDialog(frame, "Your resume generated successfully into PDF file!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error generating PDF: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        frame.setVisible(true);
    }
}