import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ResumeData {
    private List<JTextField> personalFields = new ArrayList<>();
    private List<JTextField> contactFields = new ArrayList<>();
    private JTextArea objectiveArea;
    private JTextArea experienceArea;
    private JTextArea skillsArea;
    private JTextField collegeNameField;
    private JTextField programField;
    private JTextField collegeYearField;
    private JTextField shsNameField;
    private JTextField strandField;
    private JTextField shsYearField;
    private JTextField jhsNameField;
    private JTextField jhsYearField;

    // Validation methods for specific fields
    private void validateEmail(String email) throws IllegalArgumentException {
        if (email == null || !email.contains("@") || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email address, please input a valid email address");
        }
    }

    private void validateContactNumber(String number) throws IllegalArgumentException {
        if (number == null || !number.matches("\\d{11}")) {
            throw new IllegalArgumentException("Invalid contact number, please input 11 digits number");
        }
    }

    private void validateSex(String sex) throws IllegalArgumentException {
        if (sex == null || !(sex.trim().equalsIgnoreCase("male") || sex.trim().equalsIgnoreCase("female"))) {
            throw new IllegalArgumentException("Invalid sex, please input either male or female");
        }
    }

    private void validateHeight(String height) throws IllegalArgumentException {
        if (height == null || height.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid height, please input height by cm or feet/inches");
        }
        // Check for cm (e.g., "175cm") or feet/inches (e.g., "5'10" or "5ft 10in")
        if (!height.matches("\\d+\\s*cm") && !height.matches("\\d+'\\d+\"?") && !height.matches("\\d+\\s*ft\\s*\\d+\\s*in")) {
            throw new IllegalArgumentException("Invalid height, please input height by cm or feet/inches");
        }
    }

    // Check for completeness of required fields
    private void validateCompleteness() throws IllegalArgumentException {
        // Check personal fields
        for (JTextField field : personalFields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Please fill up all of the information to build your resume");
            }
        }

        // Check contact fields
        for (JTextField field : contactFields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Please fill up all of the information to build your resume");
            }
        }

        // Check education fields
        JTextField[] educationFields = {
            collegeNameField, programField, collegeYearField,
            shsNameField, strandField, shsYearField,
            jhsNameField, jhsYearField
        };
        for (JTextField field : educationFields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Please fill up all of the information to build your resume");
            }
        }
    }

    // Validate all required fields
    public void validateData() throws IllegalArgumentException {
        // First, check for completeness
        validateCompleteness();

        // Then, validate specific fields
        validateEmail(contactFields.get(1).getText()); // Email is the second contact field
        validateContactNumber(contactFields.get(0).getText()); // Contact number is the first contact field
        validateSex(personalFields.get(2).getText()); // Sex is the third personal field
        validateHeight(personalFields.get(6).getText()); // Height is the seventh personal field
    }

    public List<JTextField> getPersonalFields() {
        return personalFields;
    }

    public void setPersonalFields(List<JTextField> personalFields) {
        this.personalFields = personalFields;
    }

    public List<JTextField> getContactFields() {
        return contactFields;
    }

    public void setContactFields(List<JTextField> contactFields) {
        this.contactFields = contactFields;
    }

    public JTextArea getObjectiveArea() {
        return objectiveArea;
    }

    public void setObjectiveArea(JTextArea objectiveArea) {
        this.objectiveArea = objectiveArea;
    }

    public JTextArea getExperienceArea() {
        return experienceArea;
    }

    public void setExperienceArea(JTextArea experienceArea) {
        this.experienceArea = experienceArea;
    }

    public JTextArea getSkillsArea() {
        return skillsArea;
    }

    public void setSkillsArea(JTextArea skillsArea) {
        this.skillsArea = skillsArea;
    }

    public JTextField getCollegeNameField() {
        return collegeNameField;
    }

    public void setCollegeNameField(JTextField collegeNameField) {
        this.collegeNameField = collegeNameField;
    }

    public JTextField getProgramField() {
        return programField;
    }

    public void setProgramField(JTextField programField) {
        this.programField = programField;
    }

    public JTextField getCollegeYearField() {
        return collegeYearField;
    }

    public void setCollegeYearField(JTextField collegeYearField) {
        this.collegeYearField = collegeYearField;
    }

    public JTextField getShsNameField() {
        return shsNameField;
    }

    public void setShsNameField(JTextField shsNameField) {
        this.shsNameField = shsNameField;
    }

    public JTextField getStrandField() {
        return strandField;
    }

    public void setStrandField(JTextField strandField) {
        this.strandField = strandField;
    }

    public JTextField getShsYearField() {
        return shsYearField;
    }

    public void setShsYearField(JTextField shsYearField) {
        this.shsYearField = shsYearField;
    }

    public JTextField getJhsNameField() {
        return jhsNameField;
    }

    public void setJhsNameField(JTextField jhsNameField) {
        this.jhsNameField = jhsNameField;
    }

    public JTextField getJhsYearField() {
        return jhsYearField;
    }

    public void setJhsYearField(JTextField jhsYearField) {
        this.jhsYearField = jhsYearField;
    }
}