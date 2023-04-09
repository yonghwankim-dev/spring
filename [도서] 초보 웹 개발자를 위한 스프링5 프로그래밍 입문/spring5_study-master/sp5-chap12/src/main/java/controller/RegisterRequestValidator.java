package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator{
	private static final String emailRegExp = 
			"[_A-Za-z0-9-\\+] + (\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	
	
	// supports() 메서드는 파라미터로 전달받은 clazz 객체가 RegisterRequest
	// 클래스로 타입 변환이 가능한지 확인한다. 
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	// validate() 메서드는 두 개의 파라미터를 갖는다.
	// target 파라미터는 검사 대상 객체이고 errors 파라미터는 검사 결과 에러 코드를 설정하기 위한 객체이다.
	// validate() 메서드 구현 과정
	// 1. 검사 대상 객체의 특정 프로퍼티나 상태가 올바른지 검사
	// 2. 올바르지 않다면 Errors의 rejectValue() 메서드를 이용해서 에러 코드 저장
	@Override
	public void validate(Object target, Errors errors) {
		RegisterRequest regReq = (RegisterRequest) target;
		
		// email 프로퍼티의 값이 유효한지 검사한다. email 프로터 값이 존재하지 않는 경우 에러코드로 "required" 추가 
		if(regReq.getEmail()==null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}
		else {	// 이메일에 대한 정규표현식이 일치하지 않는 경우 "bad" 에러코드 추가
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
			
		}
	}
}
