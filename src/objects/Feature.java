/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;
import java.util.ArrayList;
import readfile.ReadFile;

/**
 *
 * @author LA
 */
public class Feature {
    ArrayList feature = findFeature();
        
    //lay tâp gen tu doc file
        public static ArrayList getgeneSet(){    
          ArrayList geneSet = ReadFile.readFile();
          return geneSet;
    }
    //dua arraylist thanh mang các xau
        public static String[] changeSetToArray(){
            ArrayList geneSet = getgeneSet();
            int a = geneSet.size();
            String[] arrayOfString = new String[a];
            for(int k=0;k < geneSet.size();k++){
                arrayOfString[k] = geneSet.get(k).toString();
       }
            return arrayOfString;
   }
        //tim ra cac feature
        public static ArrayList findFeature(){
             ArrayList<Character> feature = new ArrayList<Character>();
            String[] arrayOfString = changeSetToArray();
            feature.add(arrayOfString[0].charAt(0));
            for(int m = 0;m < arrayOfString[0].length();m++){
                int count = 0;
                for(int n=0;n < feature.size();n++){
                    if(feature.get(n) != arrayOfString[0].charAt(m)){
                        count++;
                    }else break;
                }
                if(count == feature.size()){
                    feature.add(arrayOfString[0].charAt(m));
                }
                
            }
           return feature;
        }
}
            /*for(int m=0;m < arrayOfString[0].length();m++){
                int count = 0;
                for(int n=0;n < feature.length;n++){
                    if(feature[n] != arrayOfString[0].charAt(m)){
                        count++;
                    }else break;
                  }
                if(count == feature.length){
                    feature[feature.length-1] = arrayOfString[0].charAt(m);
                }
            }
            System.out.println(feature[2]);
        }
            
            /*for(int i=1;i < arrayOfString[0].length();i++){
                int count = 0;
                for(int m=0;m < feature.size();m++){
                    if(feature.get(m) != arrayOfString[0].charAt(i))count++;
                    else break;}
                if(count == feature.size())feature.get(arrayOfString[0].charAt(i));
                }
            return feature;*/
        
       


            //return arrayOfString;
        /*}
        public static void main(String args[]){
            char[][] array = changeSetToArray();
            System.out.println(array);
            }
        }

    
           
          
     
              /*System.out.println(object[k]);
              //System.out.println(object[k].length());

          
          //System.out.println(geneSet);
          

          /*for(int i=0;i<geneSet.size();i++){
             object[i] = geneSet.get(i).toString().toCharArray();
          }
          System.out.println(object);
          //return object;
          }
          //ham tim ra cac ki tu khac nhau trong arraylist(tim cac features)
          /*public static ArrayList findTypeOfValues(){
              ArrayList type = new ArrayList();//tao arraylist luu cac features
              char[][] object = toArray();
              type.add(object[0][0]);
              for(int k=0;k < type.size();k++){
                  
                  for(int m=0;m < object.length;m++){
                      for(int n=0;n < object[m].length;n++){
                          if(type.get(k).equals(object[m][n]))continue;else
                              type.get(object[m][n]);
  
                      }
                  }
              }
              return type;
         }
              //object[i].toCharArray();*/
         /*public static void main(String[] args){
            char[][] feature = toArray();
            System.out.println(feature);
         }     
          //System.out.println(geneSet.size());*/
          
          

