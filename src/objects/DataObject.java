/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;
import java.util.ArrayList;
import objects.Feature;
import readfile.ReadFile;

/**
 *
 * @author LA
 */
public class DataObject {
    ArrayList decodeOfSet = decodeDataObject();
    //chuyen moi xau gen thanh arraylist;
    public static ArrayList changeGene(String gene){
        ArrayList newGene = new ArrayList();
        for(int i=0;i < gene.length();i++){
            newGene.add(gene.charAt(i));
        }
        return newGene;
    }
    //chuyen toa do tung dong dataobject;
    public static ArrayList decodeALine(ArrayList list){
        ArrayList feature = Feature.findFeature();
        ArrayList codeALine = new ArrayList();
        for(int i=0;i < list.size();i++){
            ArrayList code = new ArrayList();
            for(int k=0;k < feature.size();k++){
                if(list.get(i) == feature.get(k))code.add(1);
                else code.add(0);
            }
            codeALine.add(code);
        }
        return codeALine;
    }
   //chuyen toa do tat ca cac dong;
    public static ArrayList decodeDataObject(){
        String[] geneSet = Feature.changeSetToArray();
        ArrayList set = ReadFile.readFile();
        int a = set.size();
        ArrayList decodeOfSet = new ArrayList();
        for(int i=0;i < a;i++){
            ArrayList line = changeGene(geneSet[i]);
            ArrayList decodeOfLine = decodeALine(line);
            decodeOfSet.add(decodeOfLine);
        }
        return decodeOfSet;
        }
   /* public static void main(String args[]){
        ArrayList dataobject = decodeDataObject();
        System.out.println(dataobject);*/
    }
        
   


    
    
/*
    public static String[] decode(char s){
        ArrayList geneSet = Feature.getgeneSet();//tap gen
        String[] arrayOfString = Feature.changeSetToArray();//mang cac gen
        int a = arrayOfString[0].length();//do dai moi gen
        String[] featureString = changeFeatureList();
        int l = featureString.length;
        String code = new String();
        for(int k=0;k < l;k++){
            if(s == featureString[k].charAt(0))code.charAt(k)= '0';
        }
        
    }
    
    
    //public ArrayList decodeDataObject(){
        //int a = geneSet.size();
        //String[] arrayOfString = Feature.changeSetToArray();
        //for(int i=0;i < geneSet.size();i++){
    /*public String decode(char b){
        String[] arrayOfString = Feature.changeSetToArray();
        String decode = null;
        ArrayList feature = Feature.findFeature();
        for(int i = 0;i < arrayOfString[0].length();i++){
            for(int e=0;e < feature.size();e++){
                if(arrayOfString[0].charAt(i) == feature.get(e))decode.charAt(e) == '1';
                else
                decode.charAt(e) == '0';
            }
            
        }
        return decode;
    }
    }
        public static void main(String args[]){
            String[] dataObject = new String[200];
            for(int m = 0;m < arrayOfString[0].length();m++){
                dataObject[m] = decode(arrayOfString[0].charAt(m));
            }
            arrayOfString.put(dataObject);
        }
    }
    
}*/
