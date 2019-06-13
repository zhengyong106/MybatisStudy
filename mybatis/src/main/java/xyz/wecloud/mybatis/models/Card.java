package xyz.wecloud.mybatis.models;

public class Card {
    private Integer cardId;
    private String cardName;
    private String cardType;
    private Integer empId;

    public Card() {
    }

    public Card(Integer cardId, String cardName, String cardType, Integer empId) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardType = cardType;
        this.empId = empId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("cardId=").append(cardId);
        sb.append(", cardName='").append(cardName).append('\'');
        sb.append(", cardType='").append(cardType).append('\'');
        sb.append(", empId=").append(empId);
        sb.append('}');
        return sb.toString();
    }
}
