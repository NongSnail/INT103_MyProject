package Account;

import Account.MemberAccount;
import Account.Account;
import Item.Item;
import Item.ItemStatus;
import static Policy.Policy.FINE_PER_DAY;
import static Policy.Policy.MAX_LENDING_DAYS;
import static Policy.Policy.MAX_OVER_DUE;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public abstract class ItemLending {

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Item lendingItem;
    private Account lendingMember;
    private int fine;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.lendingItem);
        return hash;
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
        final ItemLending other = (ItemLending) obj;
        if (!Objects.equals(this.lendingItem, other.lendingItem)) {
            return false;
        }
        return true;
    }

    public int getFine() {
        long days = ChronoUnit.DAYS.between(dueDate, returnDate);
        int fine = ((int) days - MAX_OVER_DUE) * FINE_PER_DAY;
        return fine < 0 ? 0 : fine;
    }

    public ItemLending checkoutItem(Item borrowItem, MemberAccount lendingMember) {
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(MAX_LENDING_DAYS);
        borrowItem.setItemStaus(ItemStatus.BORROWED);
        this.lendingItem = borrowItem;
        this.lendingMember = lendingMember;
        return this;
    }

    public int returnItem(MemberAccount lendingMember) {
        this.fine = getFine();
        this.returnDate = LocalDate.now();
        this.lendingItem.setItemStaus(ItemStatus.AVAILABLE);
        lendingMember.returnItem(this, fine);
        return getFine();
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Item getLendingItem() {
        return lendingItem;
    }

    public Account getLendingMember() {
        return lendingMember;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setLendingItem(Item lendingItem) {
        this.lendingItem = lendingItem;
    }

    public void setLendingMember(Account lendingMember) {
        this.lendingMember = lendingMember;
        if(lendingMember instanceof MemberAccount)
            ((MemberAccount)lendingMember).checkoutItem(this);
        
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

}
