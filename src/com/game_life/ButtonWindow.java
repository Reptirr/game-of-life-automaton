package com.game_life;

import javax.swing.*;
import java.awt.*;

public class ButtonWindow {

    public static JButton[] create_window() {
        // Создаем окно
        JFrame frame = new JFrame();
        frame.setSize(210, 80);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Создаем панель с кнопками
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));


        // Создаем кнопки
        JButton switch_btn = new JButton();
        JButton clear = new JButton();


        switch_btn.setText("Старт");
        clear.setText("Очистить");

        // Массив кнопок
        JButton[] buttons = new JButton[]{switch_btn, clear};


        // Делаем отступы
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        for (int i = 0; i < 2; i++) {
            panel.add(buttons[i]);
            panel.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        // Добавляем панель в окно
        frame.add(panel);

        // Открываем окно
        frame.setVisible(true);

        return buttons;
    }

    private static JButton setSize(Dimension dimension, JButton btn) {
        btn.setPreferredSize(dimension);
        btn.setMaximumSize(dimension);  // фиксируем размер
        btn.setMinimumSize(dimension);
        return btn;
    }

}
