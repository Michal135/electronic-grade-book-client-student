package com.example.electronic_grade_book_client_student;

//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentJsonProperty {
    @JsonProperty("id")
    private int id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("role")
    private String role;
    @JsonProperty("studentClass")
    private String studentClass;
}
