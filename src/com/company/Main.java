package com.company;

import java.util.Scanner;
import java.io.*;

class Je_soubor {

    public void setSoubor() throws IOException {
        Scanner nacti = new Scanner(System.in);
        String soubor = "chat.dat";
        InputStream fin = null;
        StringBuffer buffer = new StringBuffer();

        try {
            fin = new FileInputStream(soubor);
            InputStreamReader datain = new InputStreamReader(fin);
            Reader zpravy = new BufferedReader(datain);
            int ch;
            while ((ch = zpravy.read()) > -1) {
                buffer.append((char)ch);
            }
            zpravy.close();
            datain.close();
            fin.close();

            String[] vypis = buffer.toString().split(System.lineSeparator());
            System.out.println("V chatu máš " + vypis.length + " zprávy:");
            System.out.println("-----------------------");
            for (int i = 0; i < vypis.length; i++) {
                System.out.println(vypis[i]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("--Žádné předchozí zprávy--");
            FileOutputStream fout = new FileOutputStream(soubor);
            fout.close();
        }
    }
}

class Zapis {

    public void zapisSoubor(String[] poledat) throws IOException {
        String soubor = "chat.dat";
        try (BufferedWriter fout = new BufferedWriter(new FileWriter(soubor, true)))
        {
            for (int i = 0; i < poledat.length; i++) {
                if (poledat[i] != null) {
                    fout.write(poledat[i]);
                    fout.newLine();
                }
            }
            fout.close();
        }
        catch(IOException e) {
            System.err.println("Do souboru se nepovedlo zapsat.");
        }

    }

}
public class Main {

    public static void main(String[] args) throws Exception {
        String jmeno="";
        Scanner in = new Scanner(System.in);
        while (jmeno == "") {
            System.out.println("Zadej jméno:");
            jmeno = in.nextLine();
        }

        Je_soubor testSoubor = new Je_soubor();
        testSoubor.setSoubor();

        String nove_zpravy = "";
        String[] nzd = new String[1000];
        int i = 0;

        while (!(nove_zpravy.equalsIgnoreCase("!KONEC"))) {
            System.out.print("> ");
            nove_zpravy = in.nextLine();
            if (!(nove_zpravy.equalsIgnoreCase("!KONEC"))) {
                System.out.println(jmeno + ": " + nove_zpravy);
                nzd[i] = jmeno + ": " + nove_zpravy;
                i++;
            }
        }
        Zapis ulozData = new Zapis();
        ulozData.zapisSoubor(nzd);
    }
}
