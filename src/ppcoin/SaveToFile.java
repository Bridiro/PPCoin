package ppcoin;

import java.io.*;

public class SaveToFile {
    private static final String path = "/home/alebridi/Scrivania/programmazione/Java/PPCoin/src/blockchain.tmp";
    public static void writeToFile(Object obj) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            ObjectOutputStream obOut = new ObjectOutputStream(out);
            obOut.writeObject(obj);
            obOut.close();
            out.close();
        }
        catch (Exception ex) {
            ex.getMessage();
        }
    }

    public static Object readFromFile() {
        try {
            FileInputStream in = new FileInputStream(path);
            ObjectInputStream obIn = new ObjectInputStream(in);
            Object o = obIn.readObject();
            obIn.close();
            in.close();
            return o;
        }
        catch (Exception ex) {
            ex.getMessage();
            return null;
        }
    }
}
