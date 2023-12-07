package src.adventofcode.day7.model;

public class Card implements Comparable<Card> {
    char label;

    public Card(char label) {
        this.label = label;
    }

    @Override
    public int compareTo(Card c) {
        return getCardValue() - c.getCardValue();
    }

    @Override
    public String toString() {
        return "Card{label=" + label + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card myObject = (Card) obj;
        return label == myObject.label;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(label);
    }

    public char getLabel() {
        return label;
    }

    private int getCardValue() {
        return switch (this.label) {
            case 'A' -> 14;
            case 'K' -> 13;
            case 'Q' -> 12;
            case 'J' -> 11;
            case 'T' -> 10;
            default -> this.label - '0';
        };
    }
}
