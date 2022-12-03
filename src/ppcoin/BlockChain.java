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

    public Boolean addBlock(String data, Integer n) {
        Block newBlock = new Block(data, (this.blockchain.size() == 0) ? "0" : this.blockchain.get(this.blockchain.size() - 1).getHash(), new Date().getTime(), n, this);
        newBlock.mineBlock(this.prefix);
        if (newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)) {
            this.blockchain.add(newBlock);
            return true;
        }
        return false;
    }

    public Boolean replaceBlock(Integer n, String data) {
        Block newBlock = new Block(data, (n == 0) ? "0" : this.blockchain.get(n - 1).getHash(), new Date().getTime(), n, this);
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

    public Block getBlock(Integer n) {
        return this.blockchain.get(n);
    }

    public List<Block> getBlockchain() {
        return this.blockchain;
    }

    public void stopBlockChain() {
        Block.stopUpdating();
    }
}
