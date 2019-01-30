package io.tool.full.stack.ppmtoolfullstack.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author badrikant.soni on 22/01/19
 */
@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationErrorService(BindingResult result) {
        // BindingResult - BindingResult is an interface that basically invokes the validator on an object.This is basically analyzes the object
        // and it determines whether or not there are errors and if there has errors we can return validation(In Project Entity) errors in the response
        if (result.hasErrors()) {
/*                  // found an issue with below lines where getting illegalargumentexception
                    Map<String, String> errorMap = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));  // getting map from the list*/

            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}