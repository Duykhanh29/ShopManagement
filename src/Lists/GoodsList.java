/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lists;

import model.Goods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class GoodsList {

    public ArrayList<Goods> getList() {
        return list;
    }

    public void setList(ArrayList<Goods> list) {
        this.list = list;
    }
    
    ArrayList<Goods> list=new ArrayList<>();
    public int getSize(){
        return list.size();
    }
    public Goods getGoodsAtIndex(int index){
        return list.get(index);
    }
    public Goods getGoodsWithID(String id){
        for (Goods hangHoa : list) {
            if(hangHoa.getGoodsID().equals(id)){
                return hangHoa;
            }
        }
        return null;
    }
    public ArrayList<String> getListGoodsID(){
        ArrayList<String> newList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i).getGoodsID());
        }
        return newList;
    }
    
    public void display(){
        for (Goods hangHoa : list) {
            System.out.println(hangHoa.toString());
        }
    }
    // temporary datas
   

    public ArrayList<String> getListID() {
        ArrayList<String> newList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList.add(list.get(i).getGoodsID());
        }
        return newList;
    }
    public String getNameGoodsFromID(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getGoodsID().equals(id)){
                return list.get(i).getGoodsName();
            }
        }
        return null;
    }
    public int getSellingCostFromID(String id){
         for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getGoodsID().equals(id)){
                return list.get(i).getSellingCost();
            }
        }
        return -1;
    }
    
    public boolean checkExist(String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getGoodsID().equals(id)){
                return true;
            }
        }
        return false;
    }
    public int getQuantityOfAGoods(String id){
        for (Goods goods : list) {
            if(goods.getGoodsID().equals(id)){
                return goods.getQuantity();
            }
        }
        return -1;
    }
    public boolean duplicateCheck(String name,String producer){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getGoodsName().equals(name)&&list.get(i).getProducer().equals(producer)){
                return true;
            }
        }
        return false;
    }
    
    
    // unnecessary methods
    public void sortAscendingbySellingPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getSellingCost()>list.get(j).getSellingCost()){
                    Goods temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingbySellingPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getSellingCost()<=list.get(j).getSellingCost()){
                    Goods temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
    public void sortAscendingbyImportPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getImportedCost()>list.get(j).getImportedCost()){
                    Goods temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingbyImportPrice()
    {
        for(int i=0;i<list.size();i++)
        {
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).getImportedCost()<=list.get(j).getImportedCost()){
                    Goods temp=list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
     public void sortDescendingByQuantity()
     {
         Collections.sort(list,  new Comparator<Goods>() {
             @Override
             public int compare(Goods o1, Goods o2) {
                 if(o1.getQuantity()<o2.getQuantity()){
                     return 1;
                 }
                 return -1;
             }
         });
     }
     public void sortAscendingByQuantity()
     {
         Collections.sort(list,  new Comparator<Goods>() {
             @Override
             public int compare(Goods o1, Goods o2) {
                 if(o1.getQuantity()>=o2.getQuantity()){
                     return 1;
                 }
                 return -1;
             }
         });
     }
}
