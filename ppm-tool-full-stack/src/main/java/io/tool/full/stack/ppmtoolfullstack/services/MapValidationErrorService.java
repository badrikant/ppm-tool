package io.tool.full.stack.ppmtoolfullstack.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author badrikant.soni on 22/01/19
 */
@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationErrorService(BindingResult result) {
        // BindingResult - BindingResult is an interface that basically invokes the validator on an object.This is basically analyzes the object
        // and it determines whether or not there are errors and if there has errors we can return invalid objects error message in response
        if (result.hasErrors()) {
            Map<String, String> errorMap = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));  // getting map from the list
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}