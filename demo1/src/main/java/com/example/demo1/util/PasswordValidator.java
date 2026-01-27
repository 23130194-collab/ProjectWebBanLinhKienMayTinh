package com.example.demo1.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    // ^                 : Bắt đầu chuỗi
    // (?=.*[0-9])       : Phải có ít nhất một chữ số
    // (?=.*[a-z])       : Phải có ít nhất một chữ viết thường
    // (?=.*[A-Z])       : Phải có ít nhất một chữ in hoa
    // (?=.*[@#$%^&+=!]) : Phải có ít nhất một ký tự đặc biệt
    // (?=\\S+$)         : Không được có khoảng trắng
    // .{8,}             : Có độ dài ít nhất phải là 8 ký tự
    // $                 : Kết thúc chuỗi
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
