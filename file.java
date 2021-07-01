/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class file {
//Function to create file

    public static void CreateFile(String Name) {
        File file = new File(Name + ".txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File is created");

            } else {

                System.out.println("File Already Exists");
            }

        } catch (IOException ex) {
            System.out.println("Error is " + ex.getMessage());

        }

    }
//function to put data in file and append on it

    public static void append(String FileName, String Line) {
        //we use FileWriter here which is from Buffered family
        //Which let us append in text file
        //Create an object from FileWriter Class
        FileWriter fw;
        try {
            //Now passing the filename that users sends and true is for append

            fw = new FileWriter(FileName + ".txt", true);
            fw.write(Line + "\n");
            fw.close();

        } catch (IOException ex) {
            System.out.println("Error is " + ex.getMessage());
        }

    }

    public static void Delete(String line, String FileName) {
        //Our method is Output=input-Delete
        //After we get the output we will override all the data in the main file with the new data
        File file = new File("Delete.txt");
        File file1 = new File("output.txt");
        //This part for writing the line we want to delete in delete.txt
        try {

            PrintWriter pr = new PrintWriter(file);

            pr.println(line);
            pr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("The File Not Exist please create one");
        }

        try {
            //Declaring an object from PrintWriter to Write the the data after delete in output file
            PrintWriter pr2 = new PrintWriter("Output.txt");
            //read data from the main file
            BufferedReader b1 = new BufferedReader(new FileReader(FileName + ".txt"));
            //reading the first line in the main file and put it line1
            String line1 = b1.readLine();

            while (line1 != null) {
                boolean flag = false;
                //to read the data should be deleted from delete.txt
                BufferedReader b2 = new BufferedReader(new FileReader("delete.txt"));
                //put this data in onother variable and check for equalty
                String line2 = b2.readLine();
                while (line2 != null) {
                    if (line1.equals(line2)) {
                        flag = true;
                        break;

                    }
                    line2 = b2.readLine();

                }
                if (!flag) {
                    pr2.println(line1);
                }

                line1 = b1.readLine();
            }
            b1.close();

            pr2.close();
            System.out.println("Delete opertation performed succefully please check your data");
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found:" + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("Error about reading:" + ex.getMessage());

        }
//this part to write the data after delete in the main file
        try {
            //To Read The data From output file
            BufferedReader b3 = new BufferedReader(new FileReader(file1));
            //to Write The data after delete in main file
            PrintWriter p3 = new PrintWriter(FileName + ".txt");
            String original;
            while ((original = b3.readLine()) != null) {
                p3.println(original);
            }

            b3.close();
            p3.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found:" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error About Readin or Writing:" + ex.getMessage());
        }
    }

    public static void search(String FileName, String item) throws FileNotFoundException, IOException {
        //Empty Varible To store line by line form file
        String string = "";
        BufferedReader br = new BufferedReader(new FileReader(FileName + ".txt"));
        int i = 1;
        while ((string = br.readLine()) != null) {
            //This line Splits the text file line by line you can put the separator you want
            //But You need a slight change in next code
            String starr[]= string.split("\n");
            //For Each loop,loops on The array
            for (String String2 : starr) {
                //Check if the item that was sent to the function is exist or not
                if (String2.matches(item)) {
                    System.out.println("The Data is exist  in line number :"+i+"\n"+"The Data is :"+string);
               
                } else {
                    System.out.println("No such data in other lines");
                }
                i++;
            }

        }
br.close();
    }

}
