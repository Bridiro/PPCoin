package ppcoin;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class BlockChain {
    private final List<Block> blockchain = new ArrayList<>();
    private final Integer prefix = 4;
    private final String prefixString = new String(new char[prefix]).replace('\0', '0');

    public BlockChain() {

    }

    public Block getBlock(Integer n) {
        return this.blockchain.get(n);
    }

    public List<Block> getBlockchain() {
        return this.blockchain;
    }

    public void stopBlockChain() {
        Block.stopUpdating();
    }

    public Boolean addBlock(String data, Integer n) {
        Block newBlock = new Block(data, new Date().getTime(), n, this);
        newBlock.mineBlock(this.prefix);
        if (newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)) {
            this.blockchain.add(newBlock);
            return true;
        }
        return false;
    }

    public Boolean addBlock(ArrayList<Transaction> t, Integer n) {
        Block newBlock = new Block(t, new Date().getTime(), n, this);
        newBlock.mineBlock(this.prefix);
        if (newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)) {
            this.blockchain.add(newBlock);
            return true;
        }
        return false;
    }

    public Boolean addBlock(Integer n) {
        Block newBlock = new Block(new Date().getTime(), n, this);
        newBlock.mineBlock(this.prefix);
        if (newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)) {
            this.blockchain.add(newBlock);
            return true;
        }
        return false;
    }

    public Boolean replaceBlock(Integer n, String data) {
        Block newBlock = new Block(data, new Date().getTime(), n, this);
        newBlock.mineBlock(this.prefix);
        if(!(newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)))
            return false;
        this.blockchain.set(n, newBlock);
        return true;
    }

    public Boolean isValid() {
        boolean valid = true;
        for(Block b : this.blockchain) {
            if (!(b.getHash().substring(0, this.prefix).equals(this.prefixString))) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    public void addTransaction(String senderId, String receiverId, Double money) {
        Block b = this.blockchain.get(this.blockchain.size() - 1);
        b.addTransaction(senderId, receiverId, money);
        this.blockchain.set(this.blockchain.size() - 1, b);
    }
}
