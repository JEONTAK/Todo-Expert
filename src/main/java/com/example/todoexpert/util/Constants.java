package com.example.todoexpert.util;

public abstract class Constants {

    public static final String IS_INCLUDE_SMALL_ALPHABET_NUMBER = "^(?=.*[a-z])(?=.*\\d).+$";

    public static final String EMAIL_SIZE = "이메일은 최대 40자 입니다.";
    public static final String EMAIl_NOT_NULL = "이메일은 필수 값입니다.";
    public static final String EMAIl_TYPE = "이메일 형식에 맞추어야 합니다.";

    public static final String USERNAME_SIZE = "이름은 최대 20자 입니다.";
    public static final String USERNAME_NOT_NULL = "이름은 필수 값입니다.";

    public static final String PASSWORD_SIZE = "비밀번호는 최대 20자 입니다.";
    public static final String PASSWORD_NOT_NULL = "비밀번호는 필수 값입니다.";
    public static final String PASSWORD_REQUIREMENT = "비밀번호에는 소문자, 숫자가 조합되어야 합니다.";

    public static final String TITLE_SIZE = "제목은 최대 20자 입니다.";
    public static final String TITLE_NOT_NULL = "제목은 필수 값입니다.";

    public static final String CONTENTS_SIZE = "내용은 최대 200자 입니다.";
    public static final String CONTENTS_NOT_NULL = "내용은 필수 값입니다.";
}
