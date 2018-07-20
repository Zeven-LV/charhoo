package com.charhoo.springlearn.bean;

public class User {

    private Person person;
    private Company company;

    public User(Person person, Company company){
        this.person = person;
        this.company = company;
    }

    public void getAndDoTask(){
        String task = person.getName();
        if(task == null){
            return;
        }
        for(int i = 1; i<3;i++){
            String doTasker = company.getCompanyName();
            System.out.println(doTasker + " dotask:"+i);
        }
    }
}
