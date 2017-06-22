/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
public class ReadFile{
    //ham doc tung dong trong file su dung scanner,luu vao arraylist
        public static ArrayList readFile(){
            ArrayList geneSet = new ArrayList();
        try{
         FileInputStream file = new FileInputStream("C:/gene.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                line = line.trim();
                line = line.replaceAll("\\s+","");
                line = line.replaceAll("[A-Z]","");
                line = line.replaceAll("[0-9]","");
                line = line.replaceAll("[,+-_]","");
                geneSet.add(line);//them moi dong vao arraylist
            }
            scanner.close();
        }
        catch(Exception ex){
            System.out.printf(ex.toString());
    }
        return geneSet;
        }
        /*public static void main(String[] args){
            ArrayList file = readFile();
            System.out.println(file);
        }*/
}

  
       

        
                 
                 
