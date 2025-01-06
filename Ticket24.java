/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ticket24;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author kurush
 */
public class Ticket24 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("New JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton btnMessage1 = new JButton("Диалоговое окно 1");
        btnMessage1.setBounds(10, 10, 250, 40);
        btnMessage1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(frame, "hello this is my first message dialog", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        JButton btnMessage2 = new JButton("Диалоговое окно 2");
        btnMessage2.setBounds(10, 60, 250, 40);
        btnMessage2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showConfirmDialog(frame, "Hello this is my first confirm dialog");
            }
        });
        
        JButton btnMessage3 = new JButton("Диалоговое окно 3");
        btnMessage3.setBounds(10, 110, 250, 40);
        btnMessage3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showInputDialog(frame, "Hello this is my first input dialog");
            }
        });
        
        JButton btnFileCreate = new JButton("Создать файл");
        btnFileCreate.setBounds(10, 160, 250, 40);
        btnFileCreate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String fileName = JOptionPane.showInputDialog(frame, "Название файла для создания", "Создание файла", JOptionPane.NO_OPTION);
                if (fileName != ""){
                    try{
                        File file = new File(fileName);
                        file.createNewFile();
                    } catch (IOException i){
                        i.printStackTrace();
                    }
                }
            }
        });
        
        JButton btnFileRead = new JButton("Открыть файл");
        btnFileRead.setBounds(10, 210, 250, 40);
        btnFileRead.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String fileName = JOptionPane.showInputDialog("Введите название файла для прочтения");
                if (fileName != ""){
                    try{
                        FileReader fileReader = new FileReader(fileName);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        
                        String line;
                        String text = "";
                        
                        while((line = bufferedReader.readLine()) != null){
                            text += line;
                        }
                        
                        int minSpace = text.lastIndexOf(' ');
                        
                        String finalText = (minSpace != -1) ? text.substring(minSpace + 1) : text;
                        
                        File file = new File(fileName);
                        file.delete();
                        
                        File newFile = new File(fileName);
                        newFile.createNewFile();
                        
                        FileWriter fileWriter = new FileWriter(fileName);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(finalText);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        bufferedReader.close();
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        });
        frame.add(btnMessage1);
        frame.add(btnMessage2);
        frame.add(btnMessage3);
        frame.add(btnFileCreate);
        frame.add(btnFileRead);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
