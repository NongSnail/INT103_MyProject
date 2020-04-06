package Account;

import Account.Account;
import static Policy.Policy.FINE_PER_DAY;
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
public class MemberAccount extends Account {

    private ItemLending myLendingItem[];
    private int totalItemsCheckedout;
    private int overdueCount;

    public boolean checkoutItem(ItemLending borrowItem) {
        if (checkItem(borrowItem)) {
            myLendingItem[totalItemsCheckedout++] = borrowItem;
            return true;
        }
        return false;
    }

    public ItemLending[] getMyLendingList() {
        return myLendingItem;
    }

    public void returnItem(ItemLending lendingItem, int fine) {
        for (int i = 0; i < myLendingItem.length; i++) {
            if (myLendingItem[i].getLendingItem() == lendingItem.getLendingItem()) {
                if (checkForFine(lendingItem) <= 0) {
                    overdueCount++;
                }
                myLendingItem[i] = null;
                int delta = myLendingItem.length - 1 - i;
                for (int j = 0; j < delta; j++) {
                    ItemLending temp = myLendingItem[i];
                    myLendingItem[i] = myLendingItem[i + 1];
                    myLendingItem[i + 1] = temp;
                }
                this.totalItemsCheckedout--;

            }
        }
    }

    public int checkForFine(ItemLending borrowItem) {
        LocalDate d1 = borrowItem.getDueDate();
        LocalDate d2 = borrowItem.getReturnDate();
        long days = ChronoUnit.DAYS.between(d1, d2);
        int fine = ((int) days - MAX_OVER_DUE) * FINE_PER_DAY;
        return fine < 0 ? 0 : fine;
    }

    boolean checkItem(ItemLending borrowItem) {
        for (int i = 0; i < myLendingItem.length; i++) {
            if (myLendingItem[i].getLendingItem() == borrowItem.getLendingItem()) {
                return false;
            }
        }
        return true;
    }

    public int getTotalItemsCheckedout() {
        return totalItemsCheckedout;
    }
}
