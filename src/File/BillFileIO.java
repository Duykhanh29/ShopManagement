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
import model.Bill;
import model.Staff;

/**
 *
 * @author Admin
 */
public class BillFileIO {
    public String fileBill="bill.txt";
      public ArrayList<Bill> readData(String fileName)
    {
        ArrayList<Bill> list=new ArrayList<>();
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
                String staffID=Text[1];
                String time=Text[2];
                int quantity=Integer.parseInt(Text[3]);
                int costs=Integer.parseInt(Text[4]);
                list.add(new Bill(billID, staffID, time, quantity, costs));
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
    public void writeDataFromFile(String fileName, ArrayList<Bill> arrayList)
    {
        File file=new File(fileName);
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;

        
        try {
            fileWriter=new FileWriter(file);
            bufferedWriter=new BufferedWriter(fileWriter);
            for (Bill h : arrayList) {
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
