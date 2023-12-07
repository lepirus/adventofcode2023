package src.adventofcode.day7.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum HandType {
    DISTINCT,
    PAIR,
    TWO_PAIRS,
    THREE,
    FULL,
    FOUR,
    FIVE
}

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private final int bid;
    private final HandType handType;

    public Hand(String cards, int bid) {
        this.cards = new ArrayList<>(cards.length());
        for (int i = 0; i < cards.length(); i++) {
            this.cards.add(new Card(cards.charAt(i)));
        }
        this.bid = bid;
        this.handType = calculateHandType();
    }

    @Override
    public int compareTo(Hand h) {
        return this.handType != h.handType ? this.handType.ordinal() - h.handType.ordinal() : compareCards(h.cards);
    }

    private int compareCards(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (this.cards.get(i).compareTo(cards.get(i)) != 0)
                return this.cards.get(i).compareTo(cards.get(i));
        }
        return 0;
    }

    private HandType calculateHandType() {
        Map<Card, Integer> counter = new HashMap<>();
        int jokers = 0;
        Card maxCard = null;
        int maxValue = 0;

        for (Card card : this.cards) {
            if (card.label == 'J') {
                jokers++;
            } else {
                counter.put(card, counter.getOrDefault(card, 0) + 1);
            }
        }

        if (jokers > 0) {
            for (Map.Entry<Card, Integer> entry : counter.entrySet()) {
                if (entry.getValue() > maxValue) {
                    maxCard = entry.getKey();
                    maxValue = entry.getValue();
                }
            }
            counter.put(maxCard, counter.getOrDefault(maxCard, 0) + jokers);
        }

        return switch (counter.size()) {
            case 5 -> HandType.DISTINCT;
            case 4 -> HandType.PAIR;
            case 3 -> {
                for (Map.Entry<Card, Integer> entry : counter.entrySet()) {
                    if (entry.getValue() == 3) {
                        yield HandType.THREE;
                    }
                }
                yield HandType.TWO_PAIRS;
            }
            case 2 -> {
                for (Map.Entry<Card, Integer> entry : counter.entrySet()) {
                    if (entry.getValue() == 4) {
                        yield HandType.FOUR;
                    }
                }
                yield HandType.FULL;
            }
            case 1 -> HandType.FIVE;
            default -> throw new IllegalStateException("Unexpected value: " + counter.size());
        };
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", bid=" + bid +
                ", handType=" + handType +
                '}';
    }

    public int getBid() {
        return bid;
    }
}