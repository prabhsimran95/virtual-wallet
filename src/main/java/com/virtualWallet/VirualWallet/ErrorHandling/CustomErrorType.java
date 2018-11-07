package com.virtualWallet.VirualWallet.ErrorHandling;

/**
 * @author Prabhsimran
 * This a Custom error class used for provding error messages in JSON format when used 
 * with Response Entities.
 */

public class CustomErrorType {


    private String errorMessage;
    private String errorCode;
    
 
    public CustomErrorType(String errorMessage,  String code){
        this.errorMessage = errorMessage;
        this.errorCode=code;
        
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
    public String getErrorCode() {
        return errorCode;
    }
    
    
}
