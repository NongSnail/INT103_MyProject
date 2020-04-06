
import Account.ItemLending;
import Account.MemberAccount;
import Item.Item;
import Item.ItemStatus;
import static Policy.Policy.FINE_PER_DAY;
import static Policy.Policy.MAX_LENDING_DAYS;
import static Policy.Policy.MAX_OVER_DUE;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class BookLending extends ItemLending {

    @Override
    public ItemLending checkoutItem(Item borrowItem, MemberAccount lendingMember) {
        setBorrowDate(LocalDate.now());
        setDueDate(LocalDate.now().plusDays(MAX_LENDING_DAYS));
        borrowItem.setItemStaus(ItemStatus.BORROWED);
        setLendingItem(borrowItem);
        setLendingMember(lendingMember);
        return this;
    }

    @Override
    public int returnItem(MemberAccount lendingMember) {
        return super.returnItem(lendingMember);
    }

}
