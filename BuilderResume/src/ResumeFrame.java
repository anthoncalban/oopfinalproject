import java.awt.*;
import javax.swing.*;

public class ResumeFrame extends JFrame {
    private ResumeData resumeData;
    private PdfGenerator pdfGenerator;

    public ResumeFrame() {
        super("RESUME BUILDER");
        setSize(1250, 900);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resumeData = new ResumeData();
        pdfGenerator = new PdfGenerator();
        setupUI();
    }

    private void setupUI() {
        // Title
        JLabel title = new JLabel("RESUME BUILDER");
        title.setBounds(550, 10, 200, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(title);

        // Personal Information
        JLabel personalInfoLabel = new JLabel("PERSONAL INFORMATION:");
        personalInfoLabel.setBounds(30, 50, 200, 25);
        personalInfoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(personalInfoLabel);

        String[] personalFields = {
            "FULL NAME:", "AGE:", "SEX:", "DATE OF BIRTH:", 
            "PLACE OF BIRTH:", "CITIZENSHIP:", "HEIGHT:", "WEIGHT:",
            "RELIGION:", "LANGUAGES:"
        };

        for (int i = 0; i < personalFields.length; i++) {
            JLabel label = new JLabel(personalFields[i]);
            label.setBounds(30, 80 + i * 30, 150, 25);
            add(label);
            
            JTextField textField = new JTextField();
            textField.setBounds(180, 80 + i * 30, 200, 25);
            resumeData.addPersonalField(textField);
            add(textField);
        }

        // Contact Information
        JLabel contactInfoLabel = new JLabel("CONTACT INFORMATION:");
        contactInfoLabel.setBounds(400, 50, 200, 25);
        contactInfoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(contactInfoLabel);

        String[] contactFields = {
            "CONTACT NUMBER:", "EMAIL ADDRESS:", "ADDRESS:"
        };

        for (int i = 0; i < contactFields.length; i++) {
            JLabel label = new JLabel(contactFields[i]);
            label.setBounds(390, 80 + i * 30, 150, 25);
            add(label);
            
            JTextField textField = new JTextField();
            textField.setBounds(520, 80 + i * 30, 200, 25);
            resumeData.addContactField(textField);
            add(textField);
        }

        // Objective
        JLabel objectiveLabel = new JLabel("OBJECTIVE:");
        objectiveLabel.setBounds(400, 190, 200, 25);
        objectiveLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(objectiveLabel);

        JTextField objectiveText = new JTextField();
        objectiveText.setBounds(400, 230, 320, 100);
        resumeData.setObjectiveField(objectiveText);
        add(objectiveText);

        // Experience
        JLabel experienceLabel = new JLabel("EXPERIENCE: (IF YOU HAVE)");
        experienceLabel.setBounds(30, 390, 200, 25);
        experienceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(experienceLabel);

        JLabel workExpLabel = new JLabel("WORK EXPERIENCE:");
        workExpLabel.setBounds(30, 420, 150, 25);
        String[] experienceOptions = {"<1 yrs", "1-3 yrs", "3-5 yrs", "5+ yrs"};
        JComboBox<String> experienceCombo = new JComboBox<>(experienceOptions);
        experienceCombo.setBounds(180, 420, 100, 25);
        resumeData.setExperienceCombo(experienceCombo);
        add(workExpLabel);
        add(experienceCombo);

        // Skills
        JLabel skillsLabel = new JLabel("SKILLS:");
        skillsLabel.setBounds(30, 470, 100, 25);
        skillsLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(skillsLabel);

        JTextField skillField1 = new JTextField();
        skillField1.setBounds(30, 500, 150, 25);
        resumeData.addSkillField(skillField1);
        add(skillField1);

        JTextField skillField2 = new JTextField();
        skillField2.setBounds(30, 530, 150, 25);
        resumeData.addSkillField(skillField2);
        add(skillField2);

        JTextField skillField3 = new JTextField();
        skillField3.setBounds(210, 500, 150, 25);
        resumeData.addSkillField(skillField3);
        add(skillField3);

        JTextField skillField4 = new JTextField();
        skillField4.setBounds(210, 530, 150, 25);
        resumeData.addSkillField(skillField4);
        add(skillField4);

        // Education
        setupEducationSection();

        // Generate Button
        JButton generateBtn = new JButton("GENERATE RESUME!");
        generateBtn.setBounds(480, 520, 200, 30);
        generateBtn.addActionListener(e -> pdfGenerator.generatePDF(this, resumeData));
        add(generateBtn);
    }

    private void setupEducationSection() {
        JLabel educationLabel = new JLabel("EDUCATION:");
        educationLabel.setBounds(800, 50, 200, 25);
        educationLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(educationLabel);

        // College
        JLabel collegeLabel = new JLabel("COLLEGE SCHOOL:");
        collegeLabel.setBounds(800, 80, 150, 25);
        add(collegeLabel);
        
        JLabel collegeNameLabel = new JLabel("UNIVERSITY NAME:");
        collegeNameLabel.setBounds(800, 110, 150, 25);
        add(collegeNameLabel);

        JTextField collegeNameField = new JTextField();
        collegeNameField.setBounds(930, 110, 300, 25);
        resumeData.setCollegeNameField(collegeNameField);
        add(collegeNameField);
        
        JLabel programLabel = new JLabel("NAME OF PROGRAM:");
        programLabel.setBounds(800, 140, 150, 25);
        add(programLabel);
        
        JTextField programField = new JTextField();
        programField.setBounds(930, 140, 300, 25);
        resumeData.setProgramField(programField);
        add(programField);
        
        JLabel collegeYearLabel = new JLabel("ACADEMIC YEAR:");
        collegeYearLabel.setBounds(800, 170, 150, 25);
        add(collegeYearLabel);
        
        JTextField collegeYearField = new JTextField();
        collegeYearField.setBounds(930, 170, 300, 25);
        resumeData.setCollegeYearField(collegeYearField);
        add(collegeYearField);

        // Senior High School
        JLabel shsLabel = new JLabel("SENIOR HIGH SCHOOL:");
        shsLabel.setBounds(800, 220, 150, 25);
        add(shsLabel);

        JLabel shsNameLabel = new JLabel("SCHOOL NAME:");
        shsNameLabel.setBounds(800, 250, 150, 25);
        add(shsNameLabel);
        
        JTextField shsNameField = new JTextField();
        shsNameField.setBounds(930, 250, 300, 25);
        resumeData.setShsNameField(shsNameField);
        add(shsNameField);
        
        JLabel strandLabel = new JLabel("NAME OF STRAND:");
        strandLabel.setBounds(800, 280, 150, 25);
        add(strandLabel);
        
        JTextField strandField = new JTextField();
        strandField.setBounds(930, 280, 300, 25);
        resumeData.setStrandField(strandField);
        add(strandField);
        
        JLabel shsYearLabel = new JLabel("ACADEMIC YEAR:");
        shsYearLabel.setBounds(800, 310, 150, 25);
        add(shsYearLabel);
        
        JTextField shsYearField = new JTextField();
        shsYearField.setBounds(930, 310, 300, 25);
        resumeData.setShsYearField(shsYearField);
        add(shsYearField);

        // Junior High School
        JLabel jhsLabel = new JLabel("JUNIOR HIGH SCHOOL:");
        jhsLabel.setBounds(800, 360, 150, 25);
        add(jhsLabel);

        JLabel jhsNameLabel = new JLabel("SCHOOL NAME:");
        jhsNameLabel.setBounds(800, 390, 150, 25);
        add(jhsNameLabel);
        
        JTextField jhsNameField = new JTextField();
        jhsNameField.setBounds(930, 390, 300, 25);
        resumeData.setJhsNameField(jhsNameField);
        add(jhsNameField);
        
        JLabel jhsYearLabel = new JLabel("ACADEMIC YEAR:");
        jhsYearLabel.setBounds(800, 420, 150, 25);
        add(jhsYearLabel);
        
        JTextField jhsYearField = new JTextField();
        jhsYearField.setBounds(930, 420, 300, 25);
        resumeData.setJhsYearField(jhsYearField);
        add(jhsYearField);

        // Elementary School
        JLabel elemLabel = new JLabel("ELEMENTARY SCHOOL:");
        elemLabel.setBounds(800, 470, 150, 25);
        add(elemLabel);

        JLabel elemNameLabel = new JLabel("SCHOOL NAME:");
        elemNameLabel.setBounds(800, 500, 150, 25);
        add(elemNameLabel);
        
        JTextField elemNameField = new JTextField();
        elemNameField.setBounds(930, 500, 300, 25);
        resumeData.setElemNameField(elemNameField);
        add(elemNameField);
        
        JLabel elemYearLabel = new JLabel("ACADEMIC YEAR:");
        elemYearLabel.setBounds(800, 530, 150, 25);
        add(elemYearLabel);
        
        JTextField elemYearField = new JTextField();
        elemYearField.setBounds(930, 530, 300, 25);
        resumeData.setElemYearField(elemYearField);
        add(elemYearField);
    }
}
