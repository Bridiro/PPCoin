package ppcoin;

public class Tester {
    public static void main(String[] args) {

        BlockChain ppc = new BlockChain();

        System.out.println(ppc.addBlock("This is a new Block"));
        System.out.println(ppc.getBlock(0).getHash());

        System.out.println(ppc.addBlock("Another Block"));
        System.out.println(ppc.getBlock(1).getHash());

        System.out.println(ppc.addBlock("VEDIAMO SE FUNZIA"));
        System.out.println(ppc.getBlock(2).getHash());

        System.out.println(ppc.addBlock("Another Block"));
        System.out.println(ppc.getBlock(3).getHash());
        System.out.println("\n");

        System.out.println(ppc.replaceBlock(1, "Modified"));
        System.out.println("\n");

        System.out.println(ppc.getBlock(0).getHash() + " : " + ppc.getBlock(0).getData());
        System.out.println(ppc.getBlock(1).getHash() + " : " + ppc.getBlock(1).getData());
        System.out.println(ppc.getBlock(2).getHash() + " : " + ppc.getBlock(2).getData());
        System.out.println(ppc.getBlock(3).getHash() + " : " + ppc.getBlock(3).getData());
    }
}
