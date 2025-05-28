package com.game_life;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ButtonWindow {

    public static ArrayList<JButton> buttons = new ArrayList<>();
    public static ArrayList<JTextField> fields = new ArrayList<>();
    private static ArrayList<JLabel> labels = new ArrayList<>();
    public static ArrayList<JCheckBox> checkBoxes = new ArrayList<>();

    public static void create_window() {
        JFrame frame = new JFrame();
        frame.setSize(285, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Фиксирование размеров экрана
        frame.setResizable(false);
        frame.setMaximumSize(frame.getSize());
        frame.setMinimumSize(frame.getSize());

        // Лайаут
        JPanel main_panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Кнопочки
        buttons.add(new JButton("Запустить"));                            // 0
        buttons.add(new JButton("Очистить"));                             // 1
        buttons.add(new JButton("<html>Установить<br>размер</html>"));    // 2
        buttons.get(2).setPreferredSize(new Dimension(0, 34));
        buttons.add(new JButton("Установить настройки"));                 // 3
        buttons.add(new JButton("<html>Установить<br>скорость</html>"));  // 4
        buttons.get(4).setPreferredSize(new Dimension(0, 34));

        // Поля для ввода
        fields.add(new JTextField()); // Поле для размера 0
        fields.add(new JTextField()); // Поле для B       1
        fields.add(new JTextField()); // Поле для S       2
        fields.add(new JTextField()); // Поле для V       3
        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setColumns(10);
            fields.get(i).setPreferredSize(new Dimension(3*10, 0));
        }

        // Текста
        labels.add(new JLabel("Правила"));
        labels.get(0).setFont(new Font("Arial", Font.PLAIN, 20));
        labels.add(new JLabel("Оживает при"));
        labels.add(new JLabel("Живет при"));

        // Чек бокс
        JCheckBox check_foneim = new JCheckBox("Соседство Фонейма");
        JCheckBox check_log_nei = new JCheckBox("Показывать соседей");
        JCheckBox check_grid = new JCheckBox("Сетка");
        checkBoxes.add(check_foneim);
        checkBoxes.add(check_log_nei);
        checkBoxes.add(check_grid);



        // Добавляем обьекты в панель
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Первая строка
        gbc.gridy = 0;

        gbc.gridx = 0;
        main_panel.add(buttons.get(0), gbc); // Старт
        gbc.gridx = 1;
        main_panel.add(buttons.get(1), gbc); // Очистить

        // Вторая строка
        gbc.gridy = 1;

        gbc.gridx = 0;
        main_panel.add(fields.get(0), gbc); // Ввод размера
        gbc.gridx = 1;
        main_panel.add(buttons.get(2), gbc); // Установивть размер

        // Третья строка
        gbc.insets = new Insets(15, 5, 5, 5);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Растянуто на 2 колонки
        labels.get(0).setHorizontalAlignment(SwingConstants.CENTER); // Правила
        main_panel.add(labels.get(0), gbc); // Правила

        // Четвертая строка
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        check_foneim.setHorizontalAlignment(SwingConstants.CENTER); // Чек бокс
        main_panel.add(check_foneim, gbc);

        // Пятая строка
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        check_log_nei.setHorizontalAlignment(SwingConstants.CENTER); // Показывать соседей
        main_panel.add(check_log_nei, gbc);
//        check_grid.setHorizontalAlignment(SwingConstants.CENTER); // Сетка
        gbc.gridx = 1;
        main_panel.add(check_grid, gbc);   // Сетка

        // Шестая строка
        gbc.gridy = 5;
        gbc.gridx = 0;
        main_panel.add(labels.get(1), gbc); // Когда оживет
        gbc.gridx = 1;
        main_panel.add(fields.get(1), gbc); // Ввод когда оживает

        // Седьмая строка
        gbc.gridy = 6;
        gbc.gridx = 0;
        main_panel.add(labels.get(2), gbc); // Когда живет
        gbc.gridx = 1;
        main_panel.add(fields.get(2), gbc); // Ввод когда живет

        // Восьмая строка
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        main_panel.add(buttons.get(3), gbc); // Установить настройки

        // Девятая строка
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        main_panel.add(fields.get(3), gbc);
        gbc.gridx = 1;
        main_panel.add(buttons.get(4), gbc);



        // Добавляем панель в окно
        frame.add(main_panel);
        // Открываем окно
        frame.setVisible(true);

    }

    public static String getInput() {
        return fields.get(0).getText();
    }
}
