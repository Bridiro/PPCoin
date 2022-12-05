package ppcoin;

import java.util.concurrent.TimeUnit;

public class Tester {
    public static void main(String[] args) throws InterruptedException {

        BlockChain ppc = new BlockChain();

        /*
        System.out.println(ppc.addBlock("This is a new Block", 0));
        System.out.println(ppc.getBlock(0).getHash());

        System.out.println(ppc.addBlock("Another Block", 1));
        System.out.println(ppc.getBlock(1).getHash());

        System.out.println(ppc.addBlock("VEDIAMO SE FUNZIA", 2));
        System.out.println(ppc.getBlock(2).getHash());

        System.out.println(ppc.addBlock("Another Block", 3));
        System.out.println(ppc.getBlock(3).getHash());
        System.out.println("\n");

        System.out.println(ppc.replaceBlock(0, "Modified"));
        System.out.println("\n");

         */

        System.out.println(ppc.addBlock(0));
        ppc.addTransaction("Bridi", "Gub", 15.0);
        System.out.println(ppc.getBlock(0).getHash());

        System.out.println(ppc.addBlock("Another Block", 1));
        System.out.println(ppc.getBlock(1).getHash());

        System.out.println(ppc.addBlock("VEDIAMO SE FUNZIA", 2));
        System.out.println(ppc.getBlock(2).getHash());

        System.out.println(ppc.addBlock("Another Block", 3));
        System.out.println(ppc.getBlock(3).getHash());
        System.out.println("\n");

        System.out.println(ppc.replaceBlock(1, "Modified"));
        System.out.println("\n");

        System.out.println(ppc.getBlock(0).getHash() + " : " + ppc.getBlock(0).transactionsToString());
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(ppc.getBlock(1).getHash() + " : " + ppc.getBlock(1).getData());
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(ppc.getBlock(2).getHash() + " : " + ppc.getBlock(2).getData());
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(ppc.getBlock(3).getHash() + " : " + ppc.getBlock(3).getData());

        System.out.println("\nBlockChain valida: "+ppc.isValid());

        ppc.stopBlockChain();
    }
}
