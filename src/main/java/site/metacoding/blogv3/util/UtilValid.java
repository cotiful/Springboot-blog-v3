package site.metacoding.blogv3.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import site.metacoding.blogv3.handler.ex.CustomException;

public class UtilValid {

    public static void 요청에러처리(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 키와 밸류값을 같이 넣기엔 map 이 좋다.
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {

                // System.out.println(fe.getField());
                // System.out.println(fe.getDefaultMessage());

                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            // 이 부분에서 data 리턴인지 html 리턴인지 이것만 구분해서 터트려라
            throw new CustomException(errorMap.toString());
        }
        // 핵심 로직
    }

}
