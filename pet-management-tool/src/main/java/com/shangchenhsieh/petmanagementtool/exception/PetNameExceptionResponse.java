package com.shangchenhsieh.petmanagementtool.domain;

public class PetNameExceptionResponse {
    private String petsName;

    

    public PetNameExceptionResponse(String pName) {
        this.petsName = pName;
    }

    public String getPetsName() {
        return this.petsName;
    }

    public void setPetsName(String petsName) {
        this.petsName = petsName;
    }
}