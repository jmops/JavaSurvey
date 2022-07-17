package com.example.javaSurvey.constant;

public final class Const {
    public static final Integer MINQUESTIONLENGTH = 5;
    public static final Integer MAXQUESTIONLENGTH = 200;
    public static final Integer MINANSWERLENGTH = 2;
    public static final Integer MAXANSWERLENGTH = 200;
    public static final Integer MINUSERNAMELENGTH = 3;
    public static final Integer MAXUSERNAMELENGTH = 20;
    public static final Integer MINPASSWORDLENGTH = 12;
    public static final Integer MAXPASSWORDLENGTH = 40;
    public static final String VALIDCHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#&()–[{}]:;,?/*~$^+=<>";
    public static final String PASSWORDPATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;,?/*~$^+=<>]).{" + MINPASSWORDLENGTH +"," + MAXPASSWORDLENGTH +"}$";
    public static final String USERNAMEPATTERN = "^[a-zA-Z0-9]{" + MINUSERNAMELENGTH +"," + MAXUSERNAMELENGTH +"}$";

    public static final Integer HASHINGLOGROUNDS = 15;

    public static final Integer SESSIONMAXAGESECONDS = 3600;
}
