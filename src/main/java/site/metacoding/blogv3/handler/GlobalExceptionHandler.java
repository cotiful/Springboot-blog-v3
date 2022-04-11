package site.metacoding.blogv3.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.metacoding.blogv3.handler.ex.CustomApiException;
import site.metacoding.blogv3.handler.ex.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 데이터 관련 exception
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(Exception e) { // fetch 요청시 발동
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Json 으로 리턴 완료 !!
    }

    // 페이지 관련 Exception
    @ExceptionHandler(CustomException.class)
    public String htmlException(Exception e) { // 일반적인 요청 Get(a 태그). Post(form 태그) 요청
        StringBuilder sb = new StringBuilder(); // 데이터를 받아도 페이지로 하기 위해서 append 사용
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("histroy.back();");
        sb.append("</script>");
        return sb.toString();
    }
}
