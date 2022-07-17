package com.example.javaSurvey.user;

import com.example.javaSurvey.constant.Const;
import com.example.javaSurvey.survey.Survey;
import com.example.javaSurvey.surveyAnswer.SurveyAnswer;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "surveyOwner")
    private List<Survey> surveys;

    @OneToMany(mappedBy = "answerUser")
    private List<SurveyAnswer> answers;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public List<SurveyAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SurveyAnswer> answers) {
        this.answers = answers;
    }

    /**
     * Check if the set password is valid/complex enough.
     * @return true if it is valid, false if not
     */
    public boolean isPasswordComplexEnough(){
        Pattern pattern = Pattern.compile(Const.PASSWORDPATTERN);
        Matcher matcher;
        if(this.password != null){
            matcher = pattern.matcher(this.password);
            return matcher.matches();
        } else return false;
    }

    /**
     * Check if the set username is valid.
     * @return true if it is valid, false if not
     */
    public boolean isUsernameValid(){
        Pattern pattern = Pattern.compile(Const.USERNAMEPATTERN);
        Matcher matcher;
        if(this.name != null){
            matcher = pattern.matcher(this.name);
            return matcher.matches();
        } else return false;
    }

    /**
     * Check if the set email is valid.
     * @return true if it is valid, false if not
     */
    public boolean isEmailValid(){
        System.out.println(this.email);
        return EmailValidator.getInstance().isValid(this.email);

    }

    /**
     * Hash the user password.
     */
    public void hashPassword(){
        var salt = BCrypt.gensalt(Const.HASHINGLOGROUNDS); // Create salt
        this.password = BCrypt.hashpw(this.password, salt); // Hash the password with the salt
    }

    /**
     * Checkk if a given password is the correct one for this user.
     * @param password Possible password
     * @return True if it's a match, false if not
     */
    public boolean checkPassword(String password){
        return BCrypt.checkpw(password, this.password);
    }
}
