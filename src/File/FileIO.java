/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Admin
 */
public class FileIO {
    File file;
    
    /*
    public void write(String namePath,String string)
    {
        file=new File(namePath);
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        BufferedReader bufferedReader;
        try {
            fileOutputStream=new FileOutputStream(file);
            objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(string);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (Exception e) {
            }
        }
    }
    public String read(String namePath)
    {
        file=new File(namePath);
        String result;
        FileInputStream fileInputStream=null;
        ObjectInputStream objectInputStream=null;
        try {
            fileInputStream=new FileInputStream(file);
            objectInputStream=new ObjectInputStream(fileInputStream);
        } catch (Exception e) {
        }
        return "fdaf";
    }
    */
    
    
    public void writeData(String namePath,String string)
    {
        file=new File(namePath);
        FileWriter fileWriter=null;
        PrintWriter printWriter=null;
        try {
            fileWriter=new FileWriter(file);
            printWriter=new PrintWriter(fileWriter);
            printWriter.write(string);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String readData(String namePath){
        file=new File(namePath);
        FileReader fileReader=null;
        String x = null;
        BufferedReader bufferedReader=null;
        try {
            fileReader=new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            x=bufferedReader.readLine();
            System.out.println("Value: "+x);
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
}
