package com.example.AracKiralama.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorType {
    //NOT
    //--10001-20000 arası sayılar NOTFOUND/ISEMPTY  ----10012 verilen son rakam
    //--20001-30000 arası sayılar geçersız hata kodları ---20002 verilen son rakam
    //--30001-40000 arası sayılar tekrar etme CONFIG ---30006 verilen son rakam


    /**********************************ADDRESSSS*****************************************************/
     //NotFound
    STREET_NOT_FOUND_EXCEPTION(10001,"Sokak bulunamadı!"),
    NEIGHBORHOOD_NOT_FOUND_EXCEPTION(10007,"Mahalle bulunamadı!"),
    DISTRICT_NOT_FOUND_EXCEPTION(10008,"İlçe bulunamadı!"),
    CITY_NOT_FOUND_EXCEPTION(10009,"İlçe bulunamadı!"),

    //IsEmpty
    STREET_IS_EMPTY(10002,"Sokak boş bırakılamaz! "),

    //Is exists control
    EXISTS_BY_STREET_NAME(30001,"Bu mahallede aynı sokaktan mevcut. Kayıt edilemez."),
    EXISTS_BY_NEIGHBORHOOD_NAME(30003,"Bu ilçede aynı mahalleden mevcut. Kayıt edilemez."),
    EXISTS_BY_DISTRICT_NAME(30004,"Bu ilde aynı ilçeden mevcut. Kayıt edilemez."),
    EXISTS_BY_CITY_NAME(30005,"Bu ilde aynı ilçeden mevcut. Kayıt edilemez."),












    /**********************************RENTACAR*****************************************************/

    //rentacar notFoundException
    RENTAL_COMPANY_NOT_FOUND_EXCEPTION(10003,"Araç kiralama şirketi bulunamadı!"),
    RENTAL_OFFICE_NOT_FOUND_EXCEPTION(10004,"Araç kiralama ofisi bulunamadı!"),
    CAR_CLASS_NOT_FOUND_EXCEPTION(10010,"Araç sınıfı bulunamadı!"),
    MARK_NOT_FOUND_EXCEPTION(10011,"Araç markası bulunmadı"),
    MODEL_NOT_FOUND_EXCEPTION(10012,"Araç markası bulunmadı"),
    //ınvalid
    INVALID_PHONE_NUMBER(20001,"Geçersiz telefon numarası!"),

    //exists
    EXISTS_BY_CLASS_NAME(30006,"Aynı araba sınıfından mevcut"),
    EXISTS_BY_MARK_NAME(30007,"Aynı araba markasından mevcut"),
    EXISTS_BY_MODEL_NAME(30008,"Aynı araba modelinden mevcut"),
    EXISTS_BY_COMPANY_NAME(30008,"Aynı Şirketten mevcut"),



    /**********************************PERSONS*****************************************************/
    //NotFound
    ADMIN_NOT_FOUND_EXCEPTION(10005,"Admin bulunamadı"),
    EMAIL_NOT_FOUND_EXCEPTION(10006,"E_mail bulunamadı"),
    //Is exists control
    EXISTS_BY_EMAIL(30002,"Aynı e-mailden mevcut"),

    //invield
    INVALID_TOKEN(20002,"Geçersiz token"),
    PASSWORD_NOT_MATCH(20003,"Yanlış şifre girdiniz");






    int statusCode;
    String message;

}
