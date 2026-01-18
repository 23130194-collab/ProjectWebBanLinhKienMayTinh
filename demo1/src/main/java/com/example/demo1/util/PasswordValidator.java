package com.example.demo1.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    // ^                 : Bắt đầu chuỗi
    // (?=.*[0-9])       : Phải chứa ít nhất một chữ số
    // (?=.*[a-z])       : Phải chứa ít nhất một chữ thường
    // (?=.*[A-Z])       : Phải chứa ít nhất một chữ hoa
    // (?=.*[@#$%^&+=!]) : Phải chứa ít nhất một ký tự đặc biệt trong danh sách
    // (?=\\S+$)         : Không có khoảng trắng
    // .{8,}             : Độ dài ít nhất 8 ký tự
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
