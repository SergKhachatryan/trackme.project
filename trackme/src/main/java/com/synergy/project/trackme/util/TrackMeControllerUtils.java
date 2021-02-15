package com.synergy.project.trackme.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TrackMeControllerUtils {

   public static Map<String, String> getErrors (BindingResult bindingResult){
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage);
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
