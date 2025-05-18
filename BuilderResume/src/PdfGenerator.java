import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class PdfGenerator {
    private static final String[] PERSONAL_FIELDS = {
        "FULL NAME:", "AGE:", "SEX:", "DATE OF BIRTH:", 
        "PLACE OF BIRTH:", "CITIZENSHIP:", "HEIGHT:", "WEIGHT:",
        "RELIGION:", "LANGUAGES:"
    };

    private static final String[] CONTACT_FIELDS = {
        "CONTACT NUMBER:", "EMAIL ADDRESS:", "ADDRESS:"
    };

    public void generatePDF(JFrame frame, ResumeData data, String template) {
        try {
            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Resume as PDF");
            int userSelection = fileChooser.showSaveDialog(frame);

            if (userSelection != JFileChooser.APPROVE_OPTION) {
                return;
            }

            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf";
            }

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            if ("Two-Column".equals(template)) {
                generateTwoColumnTemplate(document, data, frame);
            } else {
                generateDefaultTemplate(document, data, frame);
            }

            document.close();
            JOptionPane.showMessageDialog(frame, "Your resume generated successfully into PDF file!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error generating PDF: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void generatePDFToFile(JFrame frame, ResumeData data, String template, File outputFile) {
        try {
            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();

            if ("Two-Column".equals(template)) {
                generateTwoColumnTemplate(document, data, frame);
            } else {
                generateDefaultTemplate(document, data, frame, true);
            }

            document.close();
        } catch (Exception ex) {
            throw new RuntimeException("Error generating PDF to file: " + ex.getMessage(), ex);
        }
    }

    private void generateDefaultTemplate(Document document, ResumeData data, JFrame frame) throws Exception {
        generateDefaultTemplate(document, data, frame, false);
    }

    private void generateDefaultTemplate(Document document, ResumeData data, JFrame frame, boolean isPreview) throws Exception {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font textFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
        Font subTextFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        // Header with Name and Optional Photo
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setWidths(new float[]{3, 1});

        // Name and Contact Info
        PdfPCell nameCell = new PdfPCell();
        nameCell.setBorder(Rectangle.NO_BORDER);
        Paragraph namePara = new Paragraph(data.getPersonalFields().get(0).getText(), titleFont);
        nameCell.addElement(namePara);
        for (int i = 0; i < CONTACT_FIELDS.length; i++) {
            nameCell.addElement(new Paragraph(data.getContactFields().get(i).getText(), subTextFont));
        }
        headerTable.addCell(nameCell);

        // Photo (skipped in preview mode)
        PdfPCell photoCell = new PdfPCell();
        photoCell.setBorder(Rectangle.NO_BORDER);
        photoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        if (!isPreview) {
            JFileChooser imageChooser = new JFileChooser();
            imageChooser.setDialogTitle("Select Profile Picture (Optional)");
            imageChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png"));
            int imageSelection = imageChooser.showOpenDialog(frame);
            if (imageSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    Image photo = Image.getInstance(imageChooser.getSelectedFile().getAbsolutePath());
                    photo.scaleToFit(80, 80);
                    photoCell.addElement(photo);
                } catch (Exception e) {
                    System.err.println("Failed to load image in default template: " + e.getMessage());
                }
            }
        }
        headerTable.addCell(photoCell);

        document.add(headerTable);
        document.add(new Paragraph("\n"));

        // Divider Line
        PdfPTable divider = new PdfPTable(1);
        divider.setWidthPercentage(100);
        PdfPCell dividerCell = new PdfPCell();
        dividerCell.setBorder(Rectangle.BOTTOM);
        dividerCell.setBorderColor(BaseColor.BLACK);
        dividerCell.setMinimumHeight(2);
        divider.addCell(dividerCell);
        document.add(divider);

        document.add(new Paragraph("\n"));

        // Objective
        document.add(new Paragraph("OBJECTIVE", sectionFont));
        String objectiveText = data.getObjectiveArea().getText();
        if (objectiveText == null || objectiveText.trim().isEmpty()) {
            objectiveText = "None";
        }
        Paragraph objectivePara = new Paragraph(objectiveText, textFont);
        // Removed setFirstLineIndent(10) to align all lines consistently
        document.add(objectivePara);
        document.add(new Paragraph("\n"));

        // Personal Information
        document.add(new Paragraph("PERSONAL INFORMATION", sectionFont));
        PdfPTable personalTable = new PdfPTable(2);
        personalTable.setWidthPercentage(100);
        personalTable.setWidths(new float[]{1, 2});
        for (int i = 1; i < PERSONAL_FIELDS.length; i++) {
            PdfPCell labelCell = new PdfPCell(new Phrase(PERSONAL_FIELDS[i], textFont));
            labelCell.setBorder(Rectangle.NO_BORDER);
            PdfPCell valueCell = new PdfPCell(new Phrase(data.getPersonalFields().get(i).getText(), textFont));
            valueCell.setBorder(Rectangle.NO_BORDER);
            personalTable.addCell(labelCell);
            personalTable.addCell(valueCell);
        }
        document.add(personalTable);
        document.add(new Paragraph("\n"));

        // Work Experience
        document.add(new Paragraph("WORK EXPERIENCE", sectionFont));
        String experienceText = data.getExperienceArea().getText();
        if (experienceText == null || experienceText.trim().isEmpty()) {
            experienceText = "None";
        }
        Paragraph experiencePara = new Paragraph(experienceText, textFont);
        document.add(experiencePara);
        document.add(new Paragraph("\n"));

        // Skills
        document.add(new Paragraph("SKILLS", sectionFont));
        String skillsText = data.getSkillsArea().getText();
        if (skillsText == null || skillsText.trim().isEmpty()) {
            skillsText = "None";
        }
        List<String> skillsList = Arrays.asList(skillsText.split("\n"));
        for (String skill : skillsList) {
            if (!skill.trim().isEmpty()) {
                document.add(new Paragraph("• " + skill.trim(), textFont));
            }
        }
        // Handle single skill without newline
        if (skillsList.isEmpty() && !skillsText.trim().isEmpty()) {
            document.add(new Paragraph("• " + skillsText.trim(), textFont));
        }
        document.add(new Paragraph("\n"));

        // Education
        document.add(new Paragraph("EDUCATION", sectionFont));
        PdfPTable educationTable = new PdfPTable(2);
        educationTable.setWidthPercentage(100);
        educationTable.setWidths(new float[]{1, 3});

        // College
        PdfPCell collegeLabel = new PdfPCell(new Phrase("College:", textFont));
        collegeLabel.setBorder(Rectangle.NO_BORDER);
        PdfPCell collegeValue = new PdfPCell(new Phrase(
            data.getCollegeNameField().getText() + " - " + data.getProgramField().getText() + 
            " (" + data.getCollegeYearField().getText() + ")", textFont));
        collegeValue.setBorder(Rectangle.NO_BORDER);
        educationTable.addCell(collegeLabel);
        educationTable.addCell(collegeValue);

        // Senior High
        PdfPCell shsLabel = new PdfPCell(new Phrase("Senior High School:", textFont));
        shsLabel.setBorder(Rectangle.NO_BORDER);
        PdfPCell shsValue = new PdfPCell(new Phrase(
            data.getShsNameField().getText() + " - " + data.getStrandField().getText() + 
            " (" + data.getShsYearField().getText() + ")", textFont));
        shsValue.setBorder(Rectangle.NO_BORDER);
        educationTable.addCell(shsLabel);
        educationTable.addCell(shsValue);

        // Junior High
        PdfPCell jhsLabel = new PdfPCell(new Phrase("Junior High School:", textFont));
        jhsLabel.setBorder(Rectangle.NO_BORDER);
        PdfPCell jhsValue = new PdfPCell(new Phrase(
            data.getJhsNameField().getText() + " (" + data.getJhsYearField().getText() + ")", textFont));
        jhsValue.setBorder(Rectangle.NO_BORDER);
        educationTable.addCell(jhsLabel);
        educationTable.addCell(jhsValue);

        document.add(educationTable);
    }

    private void generateTwoColumnTemplate(Document document, ResumeData data, JFrame frame) throws Exception {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font textFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
        Font subTextFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

        // Name Header (Full Width)
        String nameText = data.getPersonalFields().get(0).getText();
        if (nameText == null || nameText.trim().isEmpty()) nameText = "N/A";
        Paragraph namePara = new Paragraph(nameText, titleFont);
        namePara.setAlignment(Element.ALIGN_CENTER);
        document.add(namePara);
        document.add(new Paragraph("\n"));

        // Divider Line
        PdfPTable divider = new PdfPTable(1);
        divider.setWidthPercentage(100);
        PdfPCell dividerCell = new PdfPCell();
        dividerCell.setBorder(Rectangle.BOTTOM);
        dividerCell.setBorderColor(BaseColor.BLACK);
        dividerCell.setMinimumHeight(2);
        divider.addCell(dividerCell);
        document.add(divider);
        document.add(new Paragraph("\n"));

        // Two-column layout
        PdfPTable mainTable = new PdfPTable(2);
        mainTable.setWidthPercentage(100);
        mainTable.setWidths(new float[]{1, 2});

        // Sidebar (Left Column)
        PdfPCell sidebarCell = new PdfPCell();
        sidebarCell.setBorder(Rectangle.NO_BORDER);
        sidebarCell.setPadding(5);

        // Contact Information
        Paragraph contactHeader = new Paragraph("CONTACT", sectionFont);
        sidebarCell.addElement(contactHeader);
        for (int i = 0; i < CONTACT_FIELDS.length; i++) {
            String text = data.getContactFields().get(i).getText();
            if (text == null || text.trim().isEmpty()) text = "N/A";
            sidebarCell.addElement(new Paragraph(text, subTextFont));
        }
        // Divider
        PdfPTable contactDivider = new PdfPTable(1);
        contactDivider.setWidthPercentage(100);
        PdfPCell contactDividerCell = new PdfPCell();
        contactDividerCell.setBorder(Rectangle.BOTTOM);
        contactDividerCell.setBorderColor(BaseColor.GRAY);
        contactDividerCell.setMinimumHeight(1);
        contactDivider.addCell(contactDividerCell);
        sidebarCell.addElement(contactDivider);
        sidebarCell.addElement(new Paragraph("\n"));

        // Skills
        Paragraph skillsHeader = new Paragraph("SKILLS", sectionFont);
        sidebarCell.addElement(skillsHeader);
        String skillsText = data.getSkillsArea().getText();
        if (skillsText == null || skillsText.trim().isEmpty()) {
            skillsText = "None";
        }
        List<String> skillsList = Arrays.asList(skillsText.split("\n"));
        for (String skill : skillsList) {
            if (!skill.trim().isEmpty()) {
                sidebarCell.addElement(new Paragraph("• " + skill.trim(), textFont));
            }
        }
        // Handle single skill without newline
        if (skillsList.isEmpty() && !skillsText.trim().isEmpty()) {
            sidebarCell.addElement(new Paragraph("• " + skillsText.trim(), textFont));
        }
        // Divider
        PdfPTable skillsDivider = new PdfPTable(1);
        skillsDivider.setWidthPercentage(100);
        PdfPCell skillsDividerCell = new PdfPCell();
        skillsDividerCell.setBorder(Rectangle.BOTTOM);
        skillsDividerCell.setBorderColor(BaseColor.GRAY);
        skillsDividerCell.setMinimumHeight(1);
        skillsDivider.addCell(skillsDividerCell);
        sidebarCell.addElement(skillsDivider);
        sidebarCell.addElement(new Paragraph("\n"));

        // Personal Information
        Paragraph personalHeader = new Paragraph("PERSONAL", sectionFont);
        sidebarCell.addElement(personalHeader);
        for (int i = 1; i < PERSONAL_FIELDS.length; i++) {
            String text = data.getPersonalFields().get(i).getText();
            if (text == null || text.trim().isEmpty()) text = "N/A";
            sidebarCell.addElement(new Paragraph(PERSONAL_FIELDS[i] + " " + text, subTextFont));
        }

        mainTable.addCell(sidebarCell);

        // Main Content (Right Column)
        PdfPCell mainCell = new PdfPCell();
        mainCell.setBorder(Rectangle.NO_BORDER);
        mainCell.setPadding(5);

        // Objective
        Paragraph objectiveHeader = new Paragraph("OBJECTIVE", sectionFont);
        mainCell.addElement(objectiveHeader);
        String objectiveText = data.getObjectiveArea().getText();
        if (objectiveText == null || objectiveText.trim().isEmpty()) {
            objectiveText = "N/A";
        }
        Paragraph objectivePara = new Paragraph(objectiveText, textFont);
        // Removed setFirstLineIndent(10) to align all lines consistently
        mainCell.addElement(objectivePara);
        // Divider
        PdfPTable objectiveDivider = new PdfPTable(1);
        objectiveDivider.setWidthPercentage(100);
        PdfPCell objectiveDividerCell = new PdfPCell();
        objectiveDividerCell.setBorder(Rectangle.BOTTOM);
        objectiveDividerCell.setBorderColor(BaseColor.GRAY);
        objectiveDividerCell.setMinimumHeight(1);
        objectiveDivider.addCell(objectiveDividerCell);
        mainCell.addElement(objectiveDivider);
        mainCell.addElement(new Paragraph("\n"));

        // Work Experience
        Paragraph experienceHeader = new Paragraph("WORK EXPERIENCE", sectionFont);
        mainCell.addElement(experienceHeader);
        String experienceText = data.getExperienceArea().getText();
        if (experienceText == null || experienceText.trim().isEmpty()) {
            experienceText = "N/A";
        }
        Paragraph experiencePara = new Paragraph(experienceText, textFont);
        mainCell.addElement(experiencePara);
        // Divider
        PdfPTable experienceDivider = new PdfPTable(1);
        experienceDivider.setWidthPercentage(100);
        PdfPCell experienceDividerCell = new PdfPCell();
        experienceDividerCell.setBorder(Rectangle.BOTTOM);
        experienceDividerCell.setBorderColor(BaseColor.GRAY);
        experienceDividerCell.setMinimumHeight(1);
        experienceDivider.addCell(experienceDividerCell);
        mainCell.addElement(experienceDivider);
        mainCell.addElement(new Paragraph("\n"));

        // Education
        Paragraph educationHeader = new Paragraph("EDUCATION", sectionFont);
        mainCell.addElement(educationHeader);
        String collegeText = data.getCollegeNameField().getText() + " - " + data.getProgramField().getText() + 
            " (" + data.getCollegeYearField().getText() + ")";
        if (collegeText == null || collegeText.trim().isEmpty()) collegeText = "N/A";
        mainCell.addElement(new Paragraph(collegeText, textFont));
        String shsText = data.getShsNameField().getText() + " - " + data.getStrandField().getText() + 
            " (" + data.getShsYearField().getText() + ")";
        if (shsText == null || shsText.trim().isEmpty()) shsText = "N/A";
        mainCell.addElement(new Paragraph(shsText, textFont));
        String jhsText = data.getJhsNameField().getText() + " (" + data.getJhsYearField().getText() + ")";
        if (jhsText == null || jhsText.trim().isEmpty()) jhsText = "N/A";
        mainCell.addElement(new Paragraph(jhsText, textFont));

        mainTable.addCell(mainCell);

        document.add(mainTable);
    }
}