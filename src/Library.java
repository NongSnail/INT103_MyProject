
import Account.ItemLending;
import Account.Account;
import Account.LibrarianAccount;
import Account.MemberAccount;
import Item.Book;
import Item.Item;
import Item.ItemStatus;
import Policy.Policy;
import static Policy.Policy.FINE_PER_DAY;
import static Policy.Policy.MAX_OVER_DUE;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Library implements MemberService, LibrarianService, Policy, Iterable<Item> {
    private String libraryName;
    private MemberAccount members[];
    private LibrarianAccount librarian;
    private Item libraryItem[];
    private ItemLending libraryLendingItems[];
    private int itemCounter;
    private int memberCounter;

    public Library(String libraryName, LibrarianAccount librarian, int maxItem, int maxMember, int maxLendingItem) {
        this.libraryName = libraryName;
        this.librarian = librarian;
        MemberAccount members[] = new MemberAccount[maxMember];
        Item libraryItem[] = new Item[maxItem];
        ItemLending libraryLendingItems[] = new ItemLending[maxLendingItem];
    }

    @Override
    public ItemLending checkoutItem(MemberAccount member, Item borrowItem) {
        if (checkMember(member) && member.getTotalItemsCheckedout() < Policy.MAX_LENDING_BOOK 
                && borrowItem.getItemStaus().equals(ItemStatus.AVAILABLE)) {
           ItemLending il = null;
           BookLending bl = new BookLending();
           if(borrowItem instanceof Book){
               il = bl.checkoutItem(borrowItem, member);
           } else {
               il = (ItemLending) bl; 
               il = il.checkoutItem(borrowItem, member);
           }
            libraryLendingItems[itemCounter++] = il;
            return il;
        }
        return null;        
    }

    @Override
    public int checkForFine(MemberAccount member, ItemLending borrowItem) {
        if (checkMember(member)) {
          return borrowItem.getFine();
        }
        return 0;
    }


    @Override
    public boolean returnItem(MemberAccount member, ItemLending returnItem) {
        if (checkMember(member)) {
            for (int i = 0; i < libraryLendingItems.length; i++) {
                if (libraryLendingItems[i].getLendingItem() == returnItem.getLendingItem()) {
                    if (returnItem.getFine() <= 0) {
                        itemCounter--;
                        libraryLendingItems[i] = null;
                        int delta = libraryLendingItems.length - 1 - i;
                        for (int j = 0; j < delta; j++) {
                            ItemLending temp = libraryLendingItems[i];
                            libraryLendingItems[i] = libraryLendingItems[i + 1];
                            libraryLendingItems[i + 1] = temp;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public ItemLending[] getMyLendingList(MemberAccount member) {
        if (checkMember(member)) {
            return member.getMyLendingList();
        }
        return null;
    }

    private boolean checkMember(MemberAccount member) {
        for (int i = 0; i < members.length; i++) {
            if (members[i].getId().equals(member.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addItem(LibrarianAccount librarian, Item item) {
        if (!checkLibrarian(librarian)) {
            return false;
        } else {
            this.libraryItem[this.itemCounter++] = item;
            return true;
        }
    }

    @Override
    public boolean addMember(LibrarianAccount librarian, Account member) {
        if (!checkLibrarian(librarian)) {
            return false;
        } else {
            this.members[this.memberCounter++] = (MemberAccount) member;
            return true;
        }
    }

    private boolean checkLibrarian(LibrarianAccount librarian) {
        return librarian.equals(this.librarian);
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < itemCounter;
            }

            @Override
            public Item next() {
                if (index == itemCounter) {
                    System.out.println("No Item.");
                }
                return libraryItem[itemCounter++];
            }
        };
    }
}
