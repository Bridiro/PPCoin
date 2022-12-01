package ppcoin;

import java.util.*;

public class BlockChain {
    private List<Block> blockchain = new ArrayList<>();
    private Integer prefix = 4;
    private String prefixString = new String(new char[prefix]).replace('\0', '0');

    public BlockChain() {

    }

    public Boolean addBlock(String data) {
        Block newBlock = new Block(data, (this.blockchain.size() == 0) ? "0" : this.blockchain.get(this.blockchain.size() - 1).getHash(), new Date().getTime());
        newBlock.mineBlock(this.prefix);
        if(newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)) {
            this.blockchain.add(newBlock);
            return true;
        }
        return false;
    }

    public Block getBlock(Integer n) {
        return this.blockchain.get(n);
    }

    public Boolean replaceBlock(Integer n, String data) {
        for(int i=n; i<this.blockchain.size(); i++) {
            Block newBlock = new Block((i == n) ? data : this.blockchain.get(i).getData(), (i == 0) ? "0" : this.blockchain.get(i - 1).getHash(), new Date().getTime());
            newBlock.mineBlock(this.prefix);
            if(!(newBlock.getHash().substring(0, this.prefix).equals(this.prefixString)))
                return false;
            this.blockchain.set(i, newBlock);
        }
        return true;
    }
}
