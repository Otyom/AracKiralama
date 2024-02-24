package com.example.AracKiralama.exception;

import com.example.AracKiralama.exception.addressExceptions.*;
import com.example.AracKiralama.exception.persons.*;
import com.example.AracKiralama.exception.rentacarExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**********************************ADDRESSSS*****************************************************/
    //not found
    @ExceptionHandler(StreetNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> streetNotFoundException(StreetNotFoundException ex) {
        return new  ResponseEntity<>(
                createMessage(ErrorType.STREET_NOT_FOUND_EXCEPTION),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StreetIsEmptyException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> streeetNotFound(StreetIsEmptyException streetIsEmpty){
        return new ResponseEntity<>(
                createMessage(ErrorType.STREET_IS_EMPTY),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsByStreetNameException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>existsByStreetName(ExistsByStreetNameException existsByStreetName){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_STREET_NAME),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NeighborhoodNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>neighborhoodNotFound(NeighborhoodNotFoundException neighborhoodNotFound){
        return new ResponseEntity<>(createMessage(
                ErrorType.NEIGHBORHOOD_NOT_FOUND_EXCEPTION),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsByNeighborhoodNameException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>existsByNeighborhoodName(ExistsByNeighborhoodNameException existsByNeighborhoodNameException){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_NEIGHBORHOOD_NAME),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DistrictNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>districtNotFoundException(DistrictNotFoundException districtNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.DISTRICT_NOT_FOUND_EXCEPTION), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>cityNotFoundException(CityNotFoundException cityNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.CITY_NOT_FOUND_EXCEPTION),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsByDistrictNameException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>existsByDistrictName(ExistsByDistrictNameException existsByDistrictNameException){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_DISTRICT_NAME),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsByCityNameException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>existByCityName(ExistsByCityNameException existsByCityNameException){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_CITY_NAME),HttpStatus.CONFLICT);
    }



    /**********************************RENTACAR*****************************************************/

    //rentacar notfound
    @ExceptionHandler(RentalOfficeNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> rentalOfficeNotFound(RentalOfficeNotFoundException rentalOfficeNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.RENTAL_OFFICE_NOT_FOUND_EXCEPTION),HttpStatus.BAD_REQUEST);
    }

    //invalid
    @ExceptionHandler(InvalidPhoneNumberException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> invalidPhoneNumber(InvalidPhoneNumberException invalidPhoneNumberException){
        return new ResponseEntity<>(createMessage(ErrorType.INVALID_PHONE_NUMBER),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarClassNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> carClassNotFoundException(CarClassNotFoundException carClassNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.CAR_CLASS_NOT_FOUND_EXCEPTION),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RentalCompanyNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> rentalCompanyNotFoundException(RentalCompanyNotFoundException rentalCompanyNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.RENTAL_COMPANY_NOT_FOUND_EXCEPTION),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MarkNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> markNotFound(MarkNotFoundException markNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.MARK_NOT_FOUND_EXCEPTION),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> modelNotFound(ModelNotFoundException modelNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.MODEL_NOT_FOUND_EXCEPTION),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistsByClasNameException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> existsByClassName(ExistsByClasNameException existsByClasNameException){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_CLASS_NAME),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsByMarkNameException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> existsByMarkName(ExistsByMarkNameException existsByMarkNameException){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_MARK_NAME),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ExistsByModelException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> existsByModelName(ExistsByModelException existsByClasModelException){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_MODEL_NAME),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ExistsByCompanyNameException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> existsByCompanyName(ExistsByCompanyNameException existsByCompanyNameException){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_COMPANY_NAME),HttpStatus.CONFLICT);
    }














    /**********************************PERSONS*****************************************************/
    @ExceptionHandler(ExsistByEmail.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>exsistByEmail(ExsistByEmail exsistByEmail){
        return new ResponseEntity<>(createMessage(ErrorType.EXISTS_BY_EMAIL),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvaildToken.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>invaildToken(InvaildToken invaildToken){
        return new ResponseEntity<>(createMessage(ErrorType.INVALID_TOKEN),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>adminNotFound(AdminNotFoundException adminNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.ADMIN_NOT_FOUND_EXCEPTION),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>emailNotFound(EmailNotFoundException emailNotFoundException){
        return new ResponseEntity<>(createMessage(ErrorType.EMAIL_NOT_FOUND_EXCEPTION),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotMatch.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage>passwordNotMatch(PasswordNotMatch passwordNotMatch){
        return new ResponseEntity(createMessage(ErrorType.PASSWORD_NOT_MATCH),HttpStatus.BAD_REQUEST);
    }


    public ErrorMessage createMessage (ErrorType errorType){
        return  ErrorMessage.builder()
                .message(errorType.message)
                .statusCode(errorType.statusCode)
                .build();
    }
}
