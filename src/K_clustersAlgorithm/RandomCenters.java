/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K_clustersAlgorithm;
import java.util.ArrayList;
import java.util.Random;
import readfile.ReadFile;
import objects.DataObject;
/**
 *
 * @author LA
 */
public class RandomCenters {
    //lay random 2 so trong khoang (0,a) a là so doi tương trong file; 2 so do se la chi so de lay cac tam;
    public static int[] takeIndexRandom(){
        int k = 2;
        ArrayList geneSet = ReadFile.readFile();
        int a = geneSet.size();
        int[] indexRandom=new int[k];
        for(int i=0;i < k;i++){
       Random rd = new Random();
       int index = rd.nextInt(a);
       indexRandom[i] = index;
    }
        return indexRandom;
    }
    //phuong thuc lay tam dua vao chi so vua random;
   public static ArrayList randomCenters(){
       ArrayList decode = DataObject.decodeDataObject();
       int[] index = takeIndexRandom();
       ArrayList centers = new ArrayList();
       for(int i=0;i < index.length;i++){
           centers.add(decode.get(index[i]));
       }
       return centers;
   }
}
  

