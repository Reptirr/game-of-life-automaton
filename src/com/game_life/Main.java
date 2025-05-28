package com.game_life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    // main
    public static void main(String[] args) {
        Drawer drawer = create_window_game();
        ButtonWindow.create_window();
        ArrayList<JButton> buttons = ButtonWindow.buttons;
        ArrayList<JCheckBox> checks = ButtonWindow.checkBoxes;
        ArrayList<JTextField> fields = ButtonWindow.fields;


        // Цикл окна игры
        javax.swing.Timer game = new javax.swing.Timer(100, e -> {
            boolean[][] map = game_life.next_gen();
            drawer.setMap(map);
            drawer.repaint();
        });



        buttons.get(0).addActionListener(e -> {
            if (game.isRunning()) {
                game.stop();
                buttons.get(0).setText("Запустить");
            } else {
                game.start();
                buttons.get(0).setText("Остановить");
            }
        });

        buttons.get(1).addActionListener(e -> {
            drawer.setMap(game_life.clear_map());
            drawer.repaint();
        });

        buttons.get(2).addActionListener(e -> {
            game_rules.size = Integer.parseInt(ButtonWindow.getInput());
            drawer.repaint();
        });

        buttons.get(3).addActionListener(e -> {

            char[] textB = String.valueOf(fields.get(1).getText()).toCharArray(); // B
            char[] textS = String.valueOf(fields.get(2).getText()).toCharArray(); // S

            // Проверки
            if (textB.length <= 10 || textS.length <= 10) {
                ArrayList<Byte> B_nums = new ArrayList<>();
                ArrayList<Byte> S_nums = new ArrayList<>();
                for (char c : textB) {
                    if (c == '-') {
                        B_nums.add((byte) -1);
                        continue;
                    }
                    B_nums.add(Byte.parseByte(String.valueOf(c)));
                }
                for (char c : textS) {
                    if (c == '-') {
                        S_nums.add((byte) -1);
                        continue;
                    }
                    S_nums.add(Byte.parseByte(String.valueOf(c)));
                }

                byte remainderB = (byte) (10 - B_nums.size());
                for (int i = 0; i < B_nums.size(); i++) {
                    game_rules.B[i] = B_nums.get(i);
                }
                for (int i = 10- remainderB; i < 10; i++) {
                    game_rules.B[i] = -1;
                }

                byte remainderS = (byte) (10 - S_nums.size());
                for (int i = 0; i < S_nums.size(); i++) {
                    game_rules.S[i] = S_nums.get(i);
                }
                for (int i = 10- remainderS; i < 10; i++) {
                    game_rules.S[i] = -1;
                }
            }
        });

        buttons.get(4).addActionListener(e -> {
            game.setDelay(Integer.parseInt(fields.get(3).getText()));
        });

        checks.get(0).addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                game_rules.is_foneim = true;
            } else {
                game_rules.is_foneim = false;
            }
        });

        checks.get(1).addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                game_rules.is_log_nei = true;
                drawer.repaint();
            } else {
                game_rules.is_log_nei = false;
                drawer.repaint();
            }
        });

        checks.get(2).addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                game_rules.is_grid = true;
                drawer.repaint();
            } else {
                game_rules.is_grid = false;
                drawer.repaint();
            }
        });



    }



    private static Drawer create_window_game() {
        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        int display = screen_size.height;

        JFrame frame = new JFrame("Окновввв"); // Создаем окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // По какой кнопке закрывается окно (выход)
        frame.setSize(display-60, display-40-60); // Устанавливаем размеры

        // Фиксирование размеров экрана
        frame.setResizable(false);
        frame.setMaximumSize(frame.getSize());
        frame.setMinimumSize(frame.getSize());



        Drawer drawer = new Drawer(game_life.get_map());
        frame.add(drawer);
        frame.setVisible(true); // Открываем окно

        return drawer;
    }
}
