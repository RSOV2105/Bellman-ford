/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bellman;

import java.io.*;

/**
 *
 * @author RSOV
 */
public class Archivo {
    
    public String[] leerTxT(String dir)
    {
        String[] texto = null;
        
        try{
            FileReader fl = new FileReader(dir);
            BufferedReader bf = new BufferedReader(fl);
            
            String temp ="";
            String bfRead;
            while((bfRead=bf.readLine())!=null)
            {
                temp+=bfRead;
                temp+="\n";
            }
            texto = temp.split("\n");
        }
        catch(Exception e)
        {
            System.out.println("No se encontró archivo");
            System.out.println("Vuelva a intentar");
        }
        
        return texto;
    }
}
