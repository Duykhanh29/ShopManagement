/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.BillDetails;
import model.Bill;

/**
 *
 * @author Admin
 */
public class BillDetailsFIleIO {
    public String fileDetail="billdetail.txt";
      public ArrayList<BillDetails> readData(String fileName)
    {
        ArrayList<BillDetails> list=new ArrayList<>();
        File file=new File(fileName);
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;


        try {
            fileReader=new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            String line="";
            while (true) {                
                line=bufferedReader.readLine();
                if(line==null)
                {
                    break;
                }
                String[] Text=line.split("\t");
                String billID=Text[0];
                String goodsID=Text[1];
                String name=Text[2];
                int quantity=Integer.parseInt(Text[3]);
                int sellingCost=Integer.parseInt(Text[4]);
                int totalCosts=Integer.parseInt(Text[5]);
                list.add(new BillDetails(billID, goodsID, name, quantity, sellingCost, totalCosts));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(bufferedReader!=null)
                {
                    bufferedReader.close();
                }
                if(fileReader!=null)
                {
                    fileReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public void writeDataFromFile(String fileName, ArrayList<BillDetails> arrayList)
    {
        File file=new File(fileName);
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;

        
        try {
            fileWriter=new FileWriter(file);
            bufferedWriter=new BufferedWriter(fileWriter);
            for (BillDetails h : arrayList) {
                bufferedWriter.write(h.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(bufferedWriter!=null)
                {
                    bufferedWriter.close();
                }
                if(fileWriter!=null)
                {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
