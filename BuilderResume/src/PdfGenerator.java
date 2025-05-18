import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.io.FileOutputStream;

public class PdfGenerator {
    private static final String[] PERSONAL_FIELDS = {
        "FULL NAME:", "AGE:", "SEX:", "DATE OF BIRTH:", 
        "PLACE OF BIRTH:", "CITIZENSHIP:", "HEIGHT:", "WEIGHT:",
        "RELIGION:", "LANGUAGES:"
    };

    private static final String[] CONTACT_FIELDS = {
        "CONTACT NUMBER:", "EMAIL ADDRESS:", "ADDRESS:"
    };

    public void generatePDF(JFrame frame, ResumeData data) {
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

                Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
                Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                Font textFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

                // Title
                Paragraph titlePara = new Paragraph(data.getPersonalFields().get(0).getText(), titleFont);
                titlePara.setAlignment(Element.ALIGN_CENTER);
                document.add(titlePara);
                document.add(Chunk.NEWLINE);

                // Objective
                document.add(new Paragraph("OBJECTIVE", sectionFont));
                document.add(new Paragraph(data.getObjectiveField().getText(), textFont));
                document.add(Chunk.NEWLINE);

                // Personal Information
                document.add(new Paragraph("PERSONAL INFORMATION", sectionFont));
                for (int i = 0; i < PERSONAL_FIELDS.length; i++) {
                    document.add(new Paragraph(PERSONAL_FIELDS[i] + " " + 
                        data.getPersonalFields().get(i).getText(), textFont));
                }
                document.add(Chunk.NEWLINE);

                // Contact Information
                document.add(new Paragraph("CONTACT INFORMATION", sectionFont));
                for (int i = 0; i < CONTACT_FIELDS.length; i++) {
                    document.add(new Paragraph(CONTACT_FIELDS[i] + " " + 
                        data.getContactFields().get(i).getText(), textFont));
                }
                document.add(Chunk.NEWLINE);

                // Work Experience
                document.add(new Paragraph("WORK EXPERIENCE: " + 
                    data.getExperienceCombo().getSelectedItem(), sectionFont));
                document.add(Chunk.NEWLINE);

                // Skills
                document.add(new Paragraph("SKILLS:", sectionFont));
                for (JTextField skillField : data.getSkillFields()) {
                    document.add(new Paragraph("- " + skillField.getText(), textFont));
                }
                document.add(Chunk.NEWLINE);

                // Education
                document.add(new Paragraph("EDUCATION", sectionFont));
                document.add(new Paragraph("COLLEGE: " + data.getCollegeNameField().getText() + 
                    " - " + data.getProgramField().getText() + " (" + 
                    data.getCollegeYearField().getText() + ")", textFont));
                document.add(new Paragraph("SENIOR HIGH: " + data.getShsNameField().getText() + 
                    " - " + data.getStrandField().getText() + " (" + 
                    data.getShsYearField().getText() + ")", textFont));
                document.add(new Paragraph("JUNIOR HIGH: " + data.getJhsNameField().getText() + 
                    " (" + data.getJhsYearField().getText() + ")", textFont));
                document.add(new Paragraph("ELEMENTARY: " + data.getElemNameField().getText() + 
                    " (" + data.getElemYearField().getText() + ")", textFont));

                document.close();
                JOptionPane.showMessageDialog(frame, "Your resume generated successfully into PDF file!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error generating PDF: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}