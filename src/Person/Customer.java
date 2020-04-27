/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Person;

/**
 *
 * @author User
 */
public class Customer {
   private long idCard;
   private String name;
   private long phoneNumber;

   public Customer(long idCard, String name, long phoneNumber){
       this.idCard = idCard;
       this.name = name;
       this.phoneNumber = phoneNumber;
   }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        return this.idCard == other.idCard;
    }
}
