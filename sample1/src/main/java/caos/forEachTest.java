package caos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ccr at 2018/5/22.
 */
public class forEachTest {
    public static void forEach1(List<String> list) {
        list.forEach(System.out::println);
    }

    public static void forEach2(List<String> list) {
        for (String item:list) {
            System.out.println(item);
        }
    }
    public static void forEach3(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        List<String> suits = new ArrayList<>(Arrays.asList("1","2","3"));
        List<String> ranks = new ArrayList<>(Arrays.asList("1","2"));
        List sortedDeck = new ArrayList();

        class Card{
            private String suit;
            private String ranks;

            Card(Object suit, Object ranks) {
                this.suit = (String) suit;
                this.ranks = (String) ranks;
            }

            @Override
            public String toString() {
                return "Card{" +
                        "suit='" + suit + '\'' +
                        ", ranks='" + ranks + '\'' +
                        '}';
            }
        }

    // BROKEN - throws NoSuchElementException!
        for (Iterator i = suits.iterator(); i.hasNext(); ) {
//            String suit = (String) i.next();
            for (Iterator j = ranks.iterator(); j.hasNext(); )
                sortedDeck.add(new Card(i.next(), j.next()));
        }



        System.out.println(sortedDeck);
    }
}
