package com.example.electronic_grade_book_client_student.config;


//trzeba bedzie zrobic singleton w przyszlosci
public class ConfigClass {

    public static String user;

    public static String password;

    public ConfigClass(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        ConfigClass.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConfigClass.password = password;
    }
}
