package com.game_life;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {

    // main
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Drawer drawer = create_window_game();
        JButton[] buttons = ButtonWindow.create_window();

        // Цикл окна игры
        javax.swing.Timer game = new javax.swing.Timer((int) (0.1 * 1000), e -> {
            boolean[][] map = game_life.next_gen();
            drawer.setMap(map);
            drawer.repaint();
        });

        buttons[0].addActionListener(e -> { // Старт\стоп
            if (game.isRunning()) {
                game.stop();
                buttons[0].setText("Старт");
            } else {
                game.start();
                buttons[0].setText("Стоп");
            }
        });

        buttons[1].addActionListener(e -> {
            drawer.setMap(game_life.clear_map());
            drawer.repaint();
        });

    }



    private static Drawer create_window_game() {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        int display = screen_size.height;

        JFrame frame = new JFrame("Окновввв"); // Создаем окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // По какой кнопке закрывается окно (выход)
        frame.setSize(display-60, display-40-60); // Устанавливаем размеры


        Drawer drawer = new Drawer(game_life.get_map());
        frame.add(drawer);
        frame.setVisible(true); // Открываем окно

        return drawer;
    }
}
