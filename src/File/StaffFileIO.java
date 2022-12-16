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
import model.Staff;

/**
 *
 * @author Admin
 */
public class StaffFileIO {
    public String fileStaff="staff.txt";
     public ArrayList<Staff> readData(String fileName)
    {
        ArrayList<Staff> list=new ArrayList<>();
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
                String id=Text[0];
                String name=Text[1];
                String role=Text[2];
                String password=Text[3];
                String phoneNumber=Text[4];
                String DoB=Text[5];
                list.add(new Staff(id, name, role, password, phoneNumber, DoB));
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
    public void writeDataFromFile(String fileName, ArrayList<Staff> arrayList)
    {
        File file=new File(fileName);
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;

        
        try {
            fileWriter=new FileWriter(file);
            bufferedWriter=new BufferedWriter(fileWriter);
            for (Staff n : arrayList) {
                bufferedWriter.write(n.toString());
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
