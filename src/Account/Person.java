package Account;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Person {
    private String name;
    private String email;
    private String phone;
    
    public Person(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPhone(){
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
   
    @Override
    public String toString(){
        return "Person { Name : " + name + " Email : " + email + " Phone : " + phone + " }" ;
    }
    
}
