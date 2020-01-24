package com.example.recyclerview;

public class Person {
    private String name;
    private String surname;
    private String number;

    public Person(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }
}
