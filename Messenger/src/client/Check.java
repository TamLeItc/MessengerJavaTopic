/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author itcde
 */

//Check format for information
public class Check {
    public Check() {
    }
    
    public static boolean checkName(String str) {
        if (str == null || str.length() == 0) {
            return false;
        } else {
            String strPattern = "[^a-z0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(str);
            return !m.find();
        }
    }

    public static boolean checkScore(String n) {
        if (n == null || n.length() > 2 || n.length() < 1) {
            return false;
        } else {
            String strPattern = "[^0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();
        }
    }

    public static boolean checkSpace(String n) {
        String strPattern = "[^ ]";
        Pattern p;
        Matcher m;
        int flag = Pattern.CASE_INSENSITIVE;
        p = Pattern.compile(strPattern, flag);
        m = p.matcher(n);
        return m.find();

    }

    public static boolean checkEmail(String n) {
        if (n == null) {
            return false;
        } else {
            String strPattern = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return m.find();
        }
    }

    public static boolean checkNumber(String n) {
        if (n == null || n.length() > 10 || n.length() < 1) {
            return false;
        } else {
            String strPattern = "[^0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();
        }
    }
    
    public static boolean checkFloatNumber(String n){
        if (n == null || n.length() > 10 || n.length() < 1) {
            return false;
        } else {
            String strPattern = "[^0-9][^.]{1}[^0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();
        }
    }

    public static boolean check(String n) {
        return !(n == null || n.length() == 0);
    }

    public static boolean checkID(String n) {
        if (n == null || n.length() > 10 || n.length() < 1) {
            return false;
        } else {
            String strPattern = "[^a-z0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();

        }
    }

    public static boolean checkDate(String str) {
        
        boolean found = false;
        
        if (str == null || str.length() > 10 || str.length() < 2) {
            return false;
        } else {
            String strPattern = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
            Pattern p;
            Matcher m;
            p = Pattern.compile(strPattern);
            m = p.matcher(str); 
            found = m.matches();
        }
        return found;
    }
    
    public static boolean checkYear(String str){
        if (str == null || str.length() > 10 || str.length() < 2) {
            return false;
        } else {
            String strPattern = "[^(19|20)\\d\\d]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(str);
            return !m.find();
        }
    }
}
