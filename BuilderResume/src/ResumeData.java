import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ResumeData {
    private List<JTextField> personalFields = new ArrayList<>();
    private List<JTextField> contactFields = new ArrayList<>();
    private JTextField objectiveField;
    private JComboBox<String> experienceCombo;
    private List<JTextField> skillFields = new ArrayList<>();
    private JTextField collegeNameField;
    private JTextField programField;
    private JTextField collegeYearField;
    private JTextField shsNameField;
    private JTextField strandField;
    private JTextField shsYearField;
    private JTextField elemNameField;
    private JTextField elemYearField;

    public void addPersonalField(JTextField field) {
        personalFields.add(field);
    }

    public void addContactField(JTextField field) {
        contactFields.add(field);
    }

    public void setObjectiveField(JTextField field) {
        this.objectiveField = field;
    }

    public void setExperienceCombo(JComboBox<String> combo) {
        this.experienceCombo = combo;
    }

    public void addSkillField(JTextField field) {
        skillFields.add(field);
    }

    public void setCollegeNameField(JTextField field) {
        this.collegeNameField = field;
    }

    public void setProgramField(JTextField field) {
        this.programField = field;
    }

    public void setCollegeYearField(JTextField field) {
        this.collegeYearField = field;
    }

    public void setShsNameField(JTextField field) {
        this.shsNameField = field;
    }

    public void setStrandField(JTextField field) {
        this.strandField = field;
    }

    public void setShsYearField(JTextField field) {
        this.shsYearField = field;
    }

    public void setElemNameField(JTextField field) {
        this.elemNameField = field;
    }

    public void setElemYearField(JTextField field) {
        this.elemYearField = field;
    }

    public List<JTextField> getPersonalFields() {
        return personalFields;
    }

    public List<JTextField> getContactFields() {
        return contactFields;
    }

    public JTextField getObjectiveField() {
        return objectiveField;
    }

    public JComboBox<String> getExperienceCombo() {
        return experienceCombo;
    }

    public List<JTextField> getSkillFields() {
        return skillFields;
    }

    public JTextField getCollegeNameField() {
        return collegeNameField;
    }

    public JTextField getProgramField() {
        return programField;
    }

    public JTextField getCollegeYearField() {
        return collegeYearField;
    }

    public JTextField getShsNameField() {
        return shsNameField;
    }

    public JTextField getStrandField() {
        return strandField;
    }

    public JTextField getShsYearField() {
        return shsYearField;
    }

    public JTextField getElemNameField() {
        return elemNameField;
    }

    public JTextField getElemYearField() {
        return elemYearField;
    }
}
