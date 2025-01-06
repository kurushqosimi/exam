/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ticket10;

/**
 *
 * @author kurush
 */

/*
JDBC – это…

JDBC расшифровывается как Java DataBase Connectivity. Говоря дословно – соединения 
с «хранилищами электронных структурированных материалов» в Джаве. Это – 
платформенно-независимый стандарт. Используется для того, чтобы обеспечивать 
взаимодействие Java-контента со всевозможными СУБД.

Имеет реализацию в виде пакета java.sql. Он включен в состав Java SE. В первом 
ряду при работе с соответствующим элементом выступает SQL. Но и MySQL тоже весьма 
хорошо функционирует вместе с JDBC.

Принцип подключения – к БД и драйверам

JDBC в своей основе имеет концепцию драйверов. Driver позволяет получать соединение 
(getconnection) с БД. Для реализации поставленной задачи задействуют специальные URL-адреса.

Драйверы заключаются динамически (тогда, когда используемая утилита функционирует). 
Алгоритм «активации» будет следующим:

    Происходит загрузка софта.
    Драйвер инициализируется и загружается.
    Осуществляется самостоятельная регистрация drivers.
    Вызов производится «автоматом». Это происходит тогда, когда используемое 
    приложение требует URL с протоколом, за который отвечают драйверы.

JDBC использует экземпляры классов java.sql. После того, как это было сделано, 
происходит передача тех или иных команд для корректировки информации. JDBC посредством 
драйверов взаимодействует с СУБД и выводит тот или иной результат.
Принцип работы с БД

JDBC работает с «электронными хранилищами информации» через специальные запросы. 

Активная работа через JDBC

Взаимодействие с хранилищами электронных структурированных материалов может 
осуществляться в Java при помощи main interfaces. Возможны три варианта развития событий. 
Select one осуществляется с учетом того, что каждый подход реализуется всеми 
драйверами и имеет ряд нюансов.

Выбор предоставляется из следующих интерфейсов:

    Statement. Задействован для доступа к БД при решении общих вопросов. Активно 
    применяется на практике со static SQL. А именно – выражениями во время функционирования утилиты. Не принимает параметры.
    PreparedStatement. Интерфейс, напоминающий предыдущий. Способен принимать различные параметры.
    CallableStatement. Помогает заполучить доступ к разнообразным процедурам 
    «хранилищ структурированных данных». Как и предыдущий вариант, принимает 
    параметры в процессе функционирования контента.

Далее каждый вариант будет рассмотрен более подробно. Для каждого имеется свой example применения.

Интерфейс Statement

Для того, чтобы создать объект, используют команду типа:

Statement statement = connection.createstatement();

Экземпляр можно будет задействовать для обработки SQL-запросов. Интерфейс для 
реализации задачи имеет три метода, который реализуются конкретикой в JDBC:

    Boolean execute (String SQL) – выполняет statement, если заранее не ясно, 
    является ли строка запросом или же это своеобразное обновление. Возвращаемое 
    значение True будет, когда за счет команды был создан результирующий набор.
    Int (public void) executeUpdate (строка SQL). Отвечает за обновления. Возвращает 
    количество обновленных строк. Задействованы операторы Delete, Update и Insert.
    ResultSet executeQuery – выполняет запросы (select). Отвечает за возврат обработки результирующего набора.

Интерфейс ResultSet

Это – результирующий набор хранилища. Обеспечивает построчный доступ к 
результатам запросов. Поддерживает указатель при выполнении оных на текущую 
обрабатываемую строчку. Утилита будет последовательно перемещаться по результатам до окончания обработки или закрытия.

Интерфейс PreparedStatement

Основное отличие – это наличие параметров. Выражение с соответствующими элементами имеет знаки вопроса в контенте:
select name from persons where age=?

*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;

public class Ticket10 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Monotonic Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton execBtn = new JButton("Execute");
        execBtn.setBounds(10, 10, 120, 40);
        execBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("Enter file name: ");
    
                if (fileName == null || fileName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "File name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                List<Double> numbers = new ArrayList<>();
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] parts = line.trim().split("\\s+"); // Разделяем строку на числа
                        for (String part : parts) {
                            try {
                                numbers.add(Double.parseDouble(part)); // Преобразуем каждую часть в число
                            } catch (NumberFormatException ex) {
                                Logger.getLogger(Ticket10.class.getName())
                                .log(Level.WARNING, "Invalid number format in part: " + part, ex);
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Ticket10.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frame, "Error reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Integer> sequenceLength = new ArrayList<>();
                if (!numbers.isEmpty()) {
                    int currentLength = 1;
                    boolean increasing = true;
                    for (int i = 1; i < numbers.size(); i++) {
                        if (numbers.get(i) >= numbers.get(i - 1)) {
                            if (!increasing) {
                                sequenceLength.add(currentLength);
                                currentLength = 1;
                                increasing = true;
                            }
                            currentLength++;
                        } else if (numbers.get(i) <= numbers.get(i - 1)) {
                            if (increasing) {
                                sequenceLength.add(currentLength);
                                currentLength = 1;
                                increasing = false;
                            }
                            currentLength++;
                        }
                    }
                    sequenceLength.add(currentLength);
                }
    
                int lastDotIndex = fileName.lastIndexOf('.');
                String baseName = (lastDotIndex == -1) ? fileName : fileName.substring(0, lastDotIndex);
                String extension = (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex);
                String resultFileName = baseName + "_result" + extension;

                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFileName))) {
                    for (int length : sequenceLength) {
                        bufferedWriter.write(length + "\n");
                    }
                    JOptionPane.showMessageDialog(frame, "Results saved to " + resultFileName, "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(Ticket10.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frame, "Error writing the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        
        frame.add(execBtn);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
