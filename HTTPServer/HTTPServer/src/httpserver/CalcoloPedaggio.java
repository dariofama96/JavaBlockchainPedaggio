package httpserver;

import javax.swing.JOptionPane;

public class CalcoloPedaggio {
    
        public static String name_of_file;
    /*  
        La procedura getChilometri() calcola il numero di chilometri in relazione alla stazione di partenza
        ed alla stazione d'arrivo inserita.
    */
        public static int getChilometri(String stazione) {
            int chilometri = 0;
            if(stazione.contains("Barcellona P.G.") && stazione.contains("Milazzo"))
                chilometri = 9;
            else if(stazione.contains("Barcellona P.G.") && stazione.contains("Rometta"))
                chilometri = 24;
            else if(stazione.contains("Barcellona P.G.") && stazione.contains("Messina"))
                chilometri = 43;
            else if(stazione.contains("Barcellona P.G.") && stazione.contains("Falcone"))
                chilometri = 16;
            else if(stazione.contains("Barcellona P.G.") && stazione.contains("Patti"))
                chilometri = 26;
            else if(stazione.contains("Barcellona P.G.") && stazione.contains("Brolo"))
                chilometri = 41;
            else if(stazione.contains("Barcellona P.G.") && stazione.contains("Rocca di Capri Leone"))
                chilometri = 55;
            else if(stazione.contains("Barcellona P.G.") && stazione.contains("S.Agata di Militello"))
                chilometri = 65;
            else if(stazione.contains("Milazzo") && stazione.contains("Rometta"))
                chilometri = 15;
           else if(stazione.contains("Milazzo") && stazione.contains("Messina"))
                chilometri = 34;
            else if(stazione.contains("Milazzo") && stazione.contains("Falcone"))
                chilometri = 21;
            else if(stazione.contains("Milazzo") && stazione.contains("Patti"))
                chilometri = 33;
            else if(stazione.contains("Milazzo") && stazione.contains("Brolo"))
                chilometri = 48;
            else if(stazione.contains("Milazzo") && stazione.contains("Rocca di Capri Leone"))
                chilometri = 62;
            else if(stazione.contains("Milazzo") && stazione.contains("S.Agata di Militello"))
                chilometri = 72;
            else if(stazione.contains("Rometta") && stazione.contains("Messina"))
                chilometri = 20;
            else if(stazione.contains("Rometta") && stazione.contains("Falcone"))
                chilometri = 36;
            else if(stazione.contains("Rometta") && stazione.contains("Patti"))
                chilometri = 47;
            else if(stazione.contains("Rometta") && stazione.contains("Brolo"))
                chilometri = 62;
            else if(stazione.contains("Rometta") && stazione.contains("Rocca di Capri Leone"))
                chilometri = 76;
            else if(stazione.contains("Rometta") && stazione.contains("S.Agata di Militello"))
                chilometri = 86;
            else if(stazione.contains("Messina") && stazione.contains("Falcone"))
                chilometri = 54;
            else if(stazione.contains("Messina") && stazione.contains("Patti"))
                chilometri = 66;
            else if(stazione.contains("Messina") && stazione.contains("Brolo"))
                chilometri = 81;
            else if(stazione.contains("Messina") && stazione.contains("Rocca di Capri Leone"))
                chilometri = 95;
            else if(stazione.contains("Messina") && stazione.contains("S.Agata di Militello"))
                chilometri = 104;
            else if(stazione.contains("Falcone") && stazione.contains("Patti"))
                chilometri = 13;
            else if(stazione.contains("Falcone") && stazione.contains("Brolo"))
                chilometri = 28;
            else if(stazione.contains("Falcone") && stazione.contains("Rocca di Capri Leone"))
                chilometri = 42;
            else if(stazione.contains("Falcone") && stazione.contains("S.Agata di Militello"))
                chilometri = 52;
            else if(stazione.contains("Patti") && stazione.contains("Brolo"))
                chilometri = 23;
            else if(stazione.contains("Patti") && stazione.contains("Rocca di Capri Leone"))
                chilometri = 36;
            else if(stazione.contains("Patti") && stazione.contains("S.Agata di Militello"))
                chilometri = 46;
            else if(stazione.contains("Brolo") && stazione.contains("Rocca di Capri Leone"))
                chilometri = 17;
            else if(stazione.contains("Brolo") && stazione.contains("S.Agata di Militello"))
                chilometri = 26;
            else if(stazione.contains("Rocca di Capri Leone") && stazione.contains("S.Agata di Militello"))
                chilometri = 13;
            else 
                JOptionPane.showMessageDialog(null, "Stazione di partenza non valida");
            return chilometri;
    }
    /*
    La procedura calcoloPedaggio() permette di calcolare il pedaggio
    in relazione ai dati inviati da parte del client. Il pedaggio viene calcolato
    calcolando prima i chilometri relativi alle stazioni di partenza e di arrivo, 
    dopo di chè si moltiplica la tariffa unitaria per il numero di chilometri, se il veicolo
    appartiene alle forze dell'ordine il pedaggio è 0 altrimenti se appartiene al primo soccorso
    il pedaggio viene scontato del 50% con l'esenzione dell'IVA.
    */
    public static float calcoloPedaggio(String content){
        float pedaggio = 0;
        float iva;
        float dopo_virgola;
        float tariffa_unitaria = (float) 0.05;
        int chilom_res = getChilometri(content);
        float ris = tariffa_unitaria*chilom_res;
        pedaggio += ris;
        if(content.contains("CRIA")){
            float sconto;
            System.out.println("Veicolo di soccorso rilevato");
            sconto = (pedaggio * 50)/ 100;
            pedaggio -= sconto;
            dopo_virgola = pedaggio -(int)pedaggio;
            if (dopo_virgola > (float) 0.50)
            {
                dopo_virgola = 1;
                pedaggio = (int)pedaggio + dopo_virgola;
                return pedaggio;
            }
            else {
                dopo_virgola = 0;
                pedaggio = (int)pedaggio + dopo_virgola;
                return pedaggio;
            }
        }else if(content.contains("POLIZIA") || content.contains("Carabinieri")){
            System.out.println("Veicolo forze dell'ordine. Pedaggio FREE");
            pedaggio = 0;
            return pedaggio;
        }else{
            iva = (pedaggio * 22) / 100;
            pedaggio += iva;
            dopo_virgola = pedaggio -(int)pedaggio;
            if (dopo_virgola > (float) 0.50)
            {
                dopo_virgola = 1;
                pedaggio = (int)pedaggio + dopo_virgola;
                return pedaggio;
            }
            else {
                dopo_virgola = 0;
                pedaggio = (int)pedaggio + dopo_virgola;
                return pedaggio;
            }
        }
    }
}
