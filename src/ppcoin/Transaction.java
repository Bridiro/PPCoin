package ppcoin;

public class Transaction {
    private final String senderId;
    private final String receiverId;
    private final Double money;

    public Transaction(String senderId, String receiverId, Double money) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.money = money;
    }

    public String transactionToString() {
        return (this.senderId + " : " + this.money + " --> " + this.receiverId);
    }

    public Boolean valid() {
        return this.senderId != null && this.receiverId != null && this.money != null;
    }
}
