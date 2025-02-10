package com.example.todoexpert.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ALREADY_EXIST_USER(400, "해당 이메일을 사용하는 유저가 이미 존재합니다."),
    ALREADY_LOGIN(401, "이미 로그인한 상태입니다."),
    DATA_BAD_REQUEST(400, ""),
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 잠시 후 다시 시도해 주세요."),
    INVALID_TODO_UPDATE_COMMENT(401, "할일에 존재하는 댓글만 수정 가능합니다."),
    INVALID_USER_DELETE_TODO(401, "할일은 작성자만 삭제 가능합니다."),
    INVALID_USER_DELETE_USER(401, "유저 정보는 본인만 삭제 가능합니다."),
    INVALID_USER_UPDATE_COMMENT(401, "댓글은 작성자만 수정 가능합니다."),
    INVALID_USER_UPDATE_TODO(401, "할일은 작성자만 수정 가능합니다."),
    INVALID_USER_UPDATE_USER(401, "유저 정보는 본인만 수정 가능합니다."),
    NOT_FOUND_COMMENT(404, "댓글이 존재하지 않습니다."),
    NOT_FOUND_TODO(404, "할일이 존재하지 않습니다."),
    NOT_FOUND_USER(404, "유저가 존재하지 않습니다."),
    NOT_LOGIN(401, "로그인 상태가 아닙니다."),
    NOT_MATCH_PASSWORD(401, "비밀번호가 일치하지 않습니다."),
    PASSWORD_NOT_CORRESPOND_REQUIREMENT(400, "비밀번호가 요구 조건에 부합하지 않습니다.");

    private final int status;
    private final String message;

}
