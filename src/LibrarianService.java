
import Account.Account;
import Account.LibrarianAccount;
import Item.Item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public interface LibrarianService {
    public boolean addItem(LibrarianAccount librarian,Item item);
    public boolean addMember(LibrarianAccount librarian,Account member);
}
