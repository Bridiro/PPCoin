package ppcoin;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {

    private static final Logger logger = Logger.getLogger(Block.class.getName());

    private String hash;
    private String previousHash;
    private String data;
    private List<Transaction> transactions = new ArrayList<>();
    private final Long timeStamp;
    private Integer nonce;
    private final Integer sequence;
    private final BlockChain ppc;
    private static Boolean update = true;

    public Block(Long timeStamp, Integer sequence, BlockChain ppc) {
        this.nonce = 0;
        this.sequence = sequence;
        this.ppc = ppc;
        this.previousHash = (this.sequence == 0) ? "0" : this.ppc.getBlockchain().get(this.sequence - 1).getHash();
        this.transactions = new ArrayList<>();
        this.timeStamp = timeStamp;
        this.hash = this.calculateBlockHash();

        Thread hashUpdater = new Thread(() -> {
            while (update) {
                String newPreviousHash = (this.sequence == 0) ? "0" : this.ppc.getBlockchain().get(this.sequence - 1).getHash();
                if (!newPreviousHash.equals(this.previousHash)) {
                    this.previousHash = newPreviousHash;
                    this.hash = this.calculateBlockHash();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        hashUpdater.start();
    }

    public Block(ArrayList<Transaction> transactions, Long timeStamp, Integer sequence, BlockChain ppc) {
        this.nonce = 0;
        this.sequence = sequence;
        this.ppc = ppc;
        this.previousHash = (this.sequence == 0) ? "0" : this.ppc.getBlockchain().get(this.sequence - 1).getHash();
        this.transactions = transactions;
        this.timeStamp = timeStamp;
        this.hash = this.calculateBlockHash();

        Thread hashUpdater = new Thread(() -> {
            while (update) {
                String newPreviousHash = (this.sequence == 0) ? "0" : this.ppc.getBlockchain().get(this.sequence - 1).getHash();
                if (!newPreviousHash.equals(this.previousHash)) {
                    this.previousHash = newPreviousHash;
                    this.hash = this.calculateBlockHash();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        hashUpdater.start();
    }
    public Block(String data, Long timeStamp, Integer sequence, BlockChain pcc) {
        this.nonce = 0;
        this.sequence = sequence;
        this.ppc = pcc;
        this.previousHash = (this.sequence == 0) ? "0" : this.ppc.getBlockchain().get(this.sequence - 1).getHash();
        this.data = data;
        this.timeStamp = timeStamp;
        this.hash = this.calculateBlockHashData();

        Thread hashUpdater = new Thread(() -> {
            while (update) {
                String newPreviousHash = (this.sequence == 0) ? "0" : this.ppc.getBlockchain().get(this.sequence - 1).getHash();
                if (!newPreviousHash.equals(this.previousHash)) {
                    this.previousHash = newPreviousHash;
                    this.hash = this.calculateBlockHashData();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        hashUpdater.start();
    }

    public String getHash() {
        return this.hash;
    }

    public String getData() {
        return this.data;
    }

    public static void stopUpdating() {
        Block.update = false;
    }

    public void mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!this.hash.substring(0, prefix).equals(prefixString)) {
            this.nonce++;
            this.hash = this.calculateBlockHash();
        }
    }

    public String calculateBlockHash() {
        StringBuilder dataToHash = new StringBuilder(this.previousHash + this.timeStamp + this.nonce);
        if(this.transactions != null) {
            for(Transaction t : this.transactions) {
                dataToHash.append(t.transactionToString());
            }
        }
        MessageDigest digest;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-512");
            bytes = digest.digest(dataToHash.toString().getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        StringBuilder buffer = new StringBuilder();
        assert bytes != null;
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String calculateBlockHashData() {
        String dataToHash = this.previousHash + this.timeStamp + this.nonce + this.data;
        MessageDigest digest;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-512");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        StringBuilder buffer = new StringBuilder();
        assert bytes != null;
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public void addTransaction(String senderId, String receiverId, Double money) {
        Transaction t = new Transaction(senderId, receiverId, money);
        if(t.valid()) {
            transactions.add(t);
        }
    }

    public String transactionsToString() {
        StringBuilder transactions = new StringBuilder(" ");
        for(Transaction t : this.transactions) {
            transactions.append(t.transactionToString()).append("\n");
        }
        return transactions.toString();
    }
}