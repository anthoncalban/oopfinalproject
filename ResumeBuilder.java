import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class ResumeBuilder {

    public static void main(String[] args) {
        JFrame frame = new JFrame("CV Builder");
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("CV BUILDER");
        title.setBounds(280, 10, 200, 30);
        title.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 20));
        frame.add(title);

        // Personal Information
        JLabel personalInfoLabel = new JLabel("PERSONAL INFORMATION");
        personalInfoLabel.setBounds(30, 40, 200, 25);
        frame.add(personalInfoLabel);

        JLabel nameLabel = new JLabel("FULL NAME:");
        nameLabel.setBounds(30, 70, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(130, 70, 150, 25);
        frame.add(nameLabel);
        frame.add(nameField);

        JLabel addressLabel = new JLabel("ADDRESS:");
        addressLabel.setBounds(30, 100, 100, 25);
        JTextField addressField = new JTextField();
        addressField.setBounds(130, 100, 150, 25);
        frame.add(addressLabel);
        frame.add(addressField);

        JLabel contactLabel = new JLabel("CONTACT:");
        contactLabel.setBounds(30, 130, 100, 25);
        JTextField contactField = new JTextField();
        contactField.setBounds(130, 130, 150, 25);
        frame.add(contactLabel);
        frame.add(contactField);

        JLabel emailLabel = new JLabel("EMAIL:");
        emailLabel.setBounds(30, 160, 100, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(130, 160, 150, 25);
        frame.add(emailLabel);
        frame.add(emailField);

        JButton imageButton = new JButton("SELECT IMAGE:");
        imageButton.setBounds(30, 190, 130, 25);
        JTextField imageField = new JTextField();
        imageField.setBounds(170, 190, 110, 25);
        frame.add(imageButton);
        frame.add(imageField);

        // Skills Section
        JLabel skillsLabel = new JLabel("SKILLS");
        skillsLabel.setBounds(400, 40, 100, 25);
        JTextField skill1 = new JTextField();
        skill1.setBounds(400, 70, 120, 25);
        JTextField skill2 = new JTextField();
        skill2.setBounds(400, 100, 120, 25);
        JTextField skill3 = new JTextField();
        skill3.setBounds(530, 70, 120, 25);
        frame.add(skillsLabel);
        frame.add(skill1);
        frame.add(skill2);
        frame.add(skill3);

        // Work Experience
        JLabel workExpLabel = new JLabel("WORK EXPERIENCE:");
        workExpLabel.setBounds(400, 130, 150, 25);
        String[] options = {"<1 yrs", "1-3 yrs", "3-5 yrs", "5+ yrs"};
        JComboBox<String> experienceBox = new JComboBox<>(options);
        experienceBox.setBounds(530, 130, 120, 25);
        frame.add(workExpLabel);
        frame.add(experienceBox);

        // Qualifications Section
        JLabel qualificationsLabel = new JLabel("QUALIFICATIONS");
        qualificationsLabel.setBounds(400, 160, 150, 25);
        JLabel collegeLabel = new JLabel("COLLEGE/UNIVERSITY:");
        collegeLabel.setBounds(300, 190, 150, 25);
        JTextField collegeField = new JTextField();
        collegeField.setBounds(450, 190, 200, 25);

        JLabel qualATitle = new JLabel("TITLE OF QUALIFICATION A:");
        qualATitle.setBounds(300, 220, 200, 25);
        JTextField qualAField = new JTextField();
        qualAField.setBounds(500, 220, 150, 25);

        JLabel qualBTitle = new JLabel("TITLE OF QUALIFICATION B:");
        qualBTitle.setBounds(300, 250, 200, 25);
        JTextField qualBField = new JTextField();
        qualBField.setBounds(500, 250, 150, 25);

        frame.add(qualificationsLabel);
        frame.add(collegeLabel);
        frame.add(collegeField);
        frame.add(qualATitle);
        frame.add(qualAField);
        frame.add(qualBTitle);
        frame.add(qualBField);

        // Generate Resume Button
        JButton generateBtn = new JButton("GENERATE RESUME");
        generateBtn.setBounds(260, 320, 180, 30);
        frame.add(generateBtn);

        // Action to Export PDF
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

                    // Fonts
                    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
                    Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
                    Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

                    // Title
                    Paragraph titlePara = new Paragraph("CV - " + nameField.getText(), titleFont);
                    titlePara.setAlignment(Element.ALIGN_CENTER);
                    document.add(titlePara);
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Personal Information", sectionFont));
                    document.add(new Paragraph("Full Name: " + nameField.getText(), textFont));
                    document.add(new Paragraph("Address: " + addressField.getText(), textFont));
                    document.add(new Paragraph("Contact: " + contactField.getText(), textFont));
                    document.add(new Paragraph("Email: " + emailField.getText(), textFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Skills", sectionFont));
                    document.add(new Paragraph(skill1.getText() + ", " + skill2.getText() + ", " + skill3.getText(), textFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Work Experience: " + experienceBox.getSelectedItem(), sectionFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Qualifications", sectionFont));
                    document.add(new Paragraph("College/University: " + collegeField.getText(), textFont));
                    document.add(new Paragraph("Qualification A: " + qualAField.getText(), textFont));
                    document.add(new Paragraph("Qualification B: " + qualBField.getText(), textFont));

                    document.close();
                    JOptionPane.showMessageDialog(frame, "PDF generated successfully!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}
