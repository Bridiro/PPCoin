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

    public Boolean addBlock(String data) {
        Block newBlock = new Block(data, (this.blockchain.size() == 0) ? "0" : this.blockchain.get(this.blockchain.size() - 1).getHash(), new Date().getTime(), this);
        newBlock.mineBlock(this.prefix);
        if(newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)) {
            this.blockchain.add(newBlock);
            return true;
        }
        return false;
    }

    public Boolean replaceBlock(Integer n, String data) {
        for(int i=n; i<this.blockchain.size(); i++) {
            Block newBlock = new Block((i == n) ? data : this.blockchain.get(i).getData(), (i == 0) ? "0" : this.blockchain.get(i - 1).getHash(), new Date().getTime(), this);
            newBlock.mineBlock(this.prefix);
            if(!(newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)))
                return false;
            this.blockchain.set(i, newBlock);
        }
        return true;
    }

    public Boolean replaceBlockNew(Integer n, String data) {
        Block newBlock = new Block(data, (n == 0) ? "0" : this.blockchain.get(n - 1).getHash(), new Date().getTime(), this);
        newBlock.mineBlock(this.prefix);
        if(!(newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)))
            return false;
        this.blockchain.set(n, newBlock);
        return true;
    }

    public Block getBlock(Integer n) {
        return this.blockchain.get(n);
    }

    public List<Block> getBlockchain() {
        return this.blockchain;
    }
}
