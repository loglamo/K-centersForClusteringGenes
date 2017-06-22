/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package K_clustersAlgorithm;
import java.util.ArrayList;
import java.lang.Math;
import objects.DataObject;
import objects.Feature;
import readfile.ReadFile;
import K_clustersAlgorithm.RandomCenters;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class KclusterAlgorithm{
    int k = 2;//so cum duoc tao ta
    double b = 1.5d;//tham so b cho trong so 
    int p;//chi so cho moi vong lap
    //tim trong so W khoi đầu
    public static ArrayList findWFirst(){
        ArrayList objects= DataObject.decodeDataObject();
        ArrayList<Double> object0 = (ArrayList<Double>) objects.get(0);
        int h = object0.size();
        ArrayList firstW = new ArrayList();
        for(int i=0;i < h;i++ ){
        double W =(double) 1/h;
        firstW.add(W);
    }
        return firstW;
    }
    //phương thức tính bình phương khoảng cách từ mỗi data object đến tâm cụm
    public static double disstance(ArrayList<Integer> x,ArrayList<Integer> center){
        double total = 0;
        for(int i=0;i < x.size();i++){
            total += pow(x.get(i) - center.get(i),2);
    }
    
      return total;
    }
    /*public static void main(String args[]){
        ArrayList objects = DataObject.decodeDataObject();
        ArrayList object1 = (ArrayList) objects.get(0);
        System.out.println(object1);
        ArrayList x =  (ArrayList) object1.get(0);
        ArrayList v =  (ArrayList) object1.get(4);
        double d = disstance(x,v);
        System.out.println(d);
    }
   */
   //phương thức tính bandwidth ở mỗi vòng lặp;
    public static double bandWidthA(ArrayList<String> cluster){
        //số D của cụm;
        double d = cluster.get(0).length();
        ArrayList<Character> feature = Feature.findFeature();
        //số object trong cụm;
        double height = cluster.size();
        //Od 
        double O = feature.size();
        //số data objects trong cụm;
        double n = d*height;
        //tinh bandwidth theo công thức;
                double total1 =0;//khơi tạo giá trị cho tử số
                double total2 =0;//khởi tạo giá trị cho mẫu số
                for(int i=0;i< d;i++){//duyệt lần lượt từng chiều/attribute;
                    double totalFeatureNumber =0;//khởi tạo giá trị của biểu thức con xuất hiện trong công thức
                    for(int h=0;h < feature.size();h++){//duyệt lần lượt từng feature
                        double featureNumber =0;//khởi tạo số phần tử mỗi feature xuất hiện trong cụm;
                        for(int k=0;k < cluster.size();k++){//duyệt qua từng vị object trong cụm ở vị trí i
                          if(cluster.get(k).charAt(i) == feature.get(h))featureNumber++;
                          else continue;
                        }
                        featureNumber = featureNumber/n;
                        featureNumber = pow(featureNumber,2);
                        totalFeatureNumber += featureNumber;//giá trị biểu thức con tăng sau mỗi lần duyệt từng feature
                    }
                    total1 += (1 - totalFeatureNumber);//total1 tăng sau mỗi lần duyệt qua từng  attribute
                    total2 += (totalFeatureNumber - 1/O);//total2 tăng sau mỗi lần duyệt qua từng attribute
                }
                double a = total1/((n-1)*total2);//bandwidth theo công thức
        return a;
    }
    
    
    //phương thức tính Wjd ở công thức 9;
    public static ArrayList calculateWjd(ArrayList<String> cluster){
        ArrayList<Character> feature = Feature.findFeature();
        double O = feature.size();
        double d = cluster.get(0).length();
        double height = cluster.size();
        double n = d*height;
        double a = bandWidthA(cluster);
        ArrayList<Double> subW = new ArrayList<Double>();
        for(int i =0;i < d;i++){
            double featureNumber =0;
            for(int k=0;k < O;k++){
                double count =0;
                for(int h = 0;h < height;h++){
                    if(cluster.get(h).charAt(i) == feature.get(k))count++;
                    else continue;
                }
                count = count/n;
                count = pow(count,2);
                featureNumber += count;
                }
            double M = 1 - (pow(a,2)/O) + ((pow(a,2) - 1)*featureNumber);
            double B = -1.5d;
            M = M/B;
            subW.add(pow(10,M));
            }
        return subW;
     }
    
    
    //tính W;
    public static ArrayList calW(ArrayList<Double> subW){
        ArrayList<Double> arrayListW = new ArrayList<Double>();
        double total = 0;
        for(int i=0;i < subW.size();i++){
            total += subW.get(i);
        }
        for(int k=0;k < subW.size();k++){
            arrayListW.add((subW.get(k)/total));
        }
        return arrayListW;
    }
    
    
    //phương thức tính tâm xác suất ở mỗi cụm
    public static ArrayList probabilisticCenter(ArrayList<String> cluster,double a){//a là bandwidth
        ArrayList<Character> feature = Feature.findFeature();
        int O = feature.size();
        int height = cluster.size();
        int D = cluster.get(O).length();
        int n=D*height;
        ArrayList Vcenter = new ArrayList();
        for(int i=0;i < D;i++){
            ArrayList<Double> decodeX = new ArrayList();
                for(int j = 0;j < O;j++){
                    double count = 0;
                    for(int e = 0;e < height;e++){
                    if(cluster.get(e).charAt(i) == feature.get(j))count++;
                    else continue;
                    }
                    count = count/n;
                    count = (a*(1/O) + (1 - a)*count);
                    decodeX.add(count);
            }
                Vcenter.add(decodeX);
        }
        return Vcenter;
    }
    
    
    //lấy random tâm mỗi cụm ở bước đầu tiên
    public static ArrayList randomCenters(){
       int[] index = RandomCenters.takeIndexRandom();
       ArrayList centers = new ArrayList();
       ArrayList dataObject = DataObject.decodeDataObject();
       for(int i=0;i < index.length;i++){
           centers.add(dataObject.get(index[i]));
       }
       return centers;
    }

    //phương thức phân cụm
    public static ArrayList makeCluster(ArrayList centers,ArrayList W){
        ArrayList objects = DataObject.decodeDataObject();
        ArrayList object1 = (ArrayList) objects.get(0);
        int d = object1.size();
        ArrayList<ArrayList> center1 = new ArrayList();
        ArrayList<ArrayList> center2 = new ArrayList();
        center1 =  (ArrayList<ArrayList>) centers.get(0);
        center2 =  (ArrayList<ArrayList>) centers.get(1);
        ArrayList cluster1 = new ArrayList();
        ArrayList cluster2 = new ArrayList();
        ArrayList dissToCenter = new ArrayList();
        for(int k=0;k < 2;k++){
        ArrayList<Double> dissFromObjectToCenter = new ArrayList();
        ArrayList centerK = (ArrayList) centers.get(k);
        for(int i=0;i < objects.size();i++){
            
        ArrayList objecti = (ArrayList) objects.get(i);
        ArrayList dCenter = new ArrayList();
            double diss =0;
            for(int e=0;e < d;e++){
             double wE= (double) W.get(e);
             ArrayList centerE =  (ArrayList) centerK.get(e);
             ArrayList objectE =  (ArrayList) objecti.get(e);
                double dissd = disstance(objectE,centerE);
                dissd = dissd*wE;
                diss += dissd;
            }
            dissFromObjectToCenter.add(diss);
        }
        dissToCenter.add(dissFromObjectToCenter);
        }
        ArrayList diss1 = (ArrayList) dissToCenter.get(0);
        ArrayList diss2 = (ArrayList) dissToCenter.get(1);
        for(int n=0;n < objects.size();n++){
            double d1 = (double) diss1.get(n);
            double d2 = (double) diss2.get(n);
            ArrayList objectN = (ArrayList) objects.get(n);
            if(d1 < d2)cluster1.add(objectN);
            else cluster2.add(objectN);
        }
        ArrayList<ArrayList> C = new ArrayList();
        C.add(cluster1);
        C.add(cluster2);
        return C;
        
    }
//phương thức đưa ra các cụm là tập hợp các xâu
    public static ArrayList stringCluster(ArrayList C){
         ArrayList cluster = new ArrayList();
         ArrayList objects = DataObject.decodeDataObject();
         ArrayList setGene = Feature.getgeneSet();
         int index;
         for(int i=0;i < C.size();i++){
             index = objects.indexOf(C.get(i));
             if(index != -1){
                 cluster.add(setGene.get(index));
             }else continue;
         }
         return cluster;
    }
    /*public static void main(String args[]){
        ArrayList A = randomCenters();
        ArrayList C = stringCluster(A);
        System.out.println(C);
    }*/
    
    //phương thức dồn các W của các cụm thành 1 ArrayList W
    public static ArrayList addW(ArrayList a,ArrayList b){
        ArrayList W = new ArrayList();
        for(int i=0;i < a.size();i++){
            W.add(a.get(i));
        }
        for(int e=0;e < b.size();e++){
            W.add(b.get(e));
        }
        return W;
    }
    
    //test
    /*public static void main(String args[]){
       ArrayList centers = randomCenters();
       ArrayList W = findWFirst();
       ArrayList  C = makeCluster(centers,W);
       ArrayList c1 = (ArrayList) C.get(0);
       ArrayList stringobject = stringCluster(c1);
       System.out.println(stringobject);
    }*/

//K-centers algorithm;
public static ArrayList KclusterAlgorithm(){
        int p = 0;//chỉ số vòng lặp p
        ArrayList firstCenters = randomCenters();
        ArrayList W = findWFirst();
        ArrayList afterC = new ArrayList();
        ArrayList frontC = makeCluster(firstCenters,W);
           //gán cho fronC là C ban đầu
           
        while(afterC != frontC){
            ArrayList afterW = W;
            afterC = frontC;
            ArrayList cluster1 = (ArrayList) afterC.get(0);
            ArrayList cluster2 = (ArrayList) afterC.get(1);
            ArrayList<String> cluster1String = stringCluster(cluster1) ;
            ArrayList<String> cluster2String = stringCluster(cluster2);
            double bandwidth1 = bandWidthA(cluster1String);  
            double bandwidth2 = bandWidthA(cluster2String);
            ArrayList V1 = probabilisticCenter(cluster1String,bandwidth1);
            ArrayList V2 = probabilisticCenter(cluster2String,bandwidth2);
            ArrayList subw1 = calculateWjd(cluster1String);
            ArrayList W1 = calW(subw1);
            ArrayList subw2 = calculateWjd(cluster2String);
            ArrayList W2 = calW(subw2);
            ArrayList Wj = addW(W1,W2);
            W = Wj;
            p++;}
        return afterC;
        }


public static void main(String args[]){
    ArrayList afterC = KclusterAlgorithm();
    ArrayList geneSet = DataObject.decodeDataObject();
    ArrayList geneSetString = ReadFile.readFile();
    ArrayList gene1 = (ArrayList) afterC.get(0);
    ArrayList gene2 = (ArrayList) afterC.get(1);
    ArrayList<String> geneString1 = stringCluster(gene1);
    ArrayList<String> geneString2 = stringCluster(gene2);
    ArrayList W1 = calculateWjd(geneString1);
    ArrayList W2 = calculateWjd(geneString2);
    System.out.println("ma trận trọng số cụm 1:");
    for(int i=0;i < W1.size();i++){
        System.out.print(W1.get(i) + "  ");
    }
    System.out.println();
    System.out.println("ma trận trọng số cụm 2:");
    for(int e = 0;e < W2.size();e++){
        System.out.print(W2.get(e)+ "  ");
}
    System.out.println(geneString1);
    System.out.println(geneString1.size());
    System.out.println(geneString2);
    System.out.println(geneString2.size());
}
}


        
        
        
        
        
        
        
        
        
        
       
    
    

   
    
    

