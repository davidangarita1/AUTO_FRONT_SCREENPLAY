package com.turnos.models;

public class RegistrationData {

    private final String fullName;
    private final String cedula;

    public RegistrationData(String fullName, String cedula) {
        this.fullName = fullName;
        this.cedula = cedula;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCedula() {
        return cedula;
    }
}
