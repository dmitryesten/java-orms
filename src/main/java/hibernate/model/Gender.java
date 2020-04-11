package hibernate.model;

import org.omg.CORBA.PUBLIC_MEMBER;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private String gender;

    Gender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }

}
