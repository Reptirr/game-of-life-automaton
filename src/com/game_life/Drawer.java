package com.game_life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// Унаследуем класс окна отображения
public class Drawer extends JPanel {
    private static boolean[][] map;
    private static double recty_size;
    private static double rectx_size;

    private static double scale = 1;
    private static double offsetX = 0;
    private static double offsetY = 0;

    // Функция рисования в окне отображения
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int rects_num = game_rules.size * game_rules.size;
        double rects = Math.sqrt(rects_num);

        int mx = getWidth();
        int my = getHeight();

        recty_size = my/rects;
        rectx_size = mx/rects;

        g2d.translate(offsetX, offsetY);
        g2d.scale(scale, scale);

        setBackground(Color.black);

        for (double y_num = 0; y_num < rects; y_num++) {
            for (double x_num = 0; x_num < rects; x_num++) {
                // Функция отображения сетки, соседей и самой клетки
                draw_cellGrid(g2d, x_num, y_num);
            }
        }

    }

    private static void draw_cellGrid(Graphics2D g2d, double x_numd, double y_numd) {
        int x_num = (int) x_numd;
        int y_num = (int) y_numd;
        // Пишем число соседей в середине клетки
        byte num_nei = 0;
        try {
            num_nei = game_life.num_neighbours(new int[]{x_num, y_num});
        } catch (Exception _) {}

        if (game_rules.is_log_nei) {
            g2d.setColor(Color.BLUE);
            FontMetrics fm = g2d.getFontMetrics();
//            String s = String.valueOf(num_nei);    // Соседу
            String s = String.valueOf(x_num);// Место на карте
            int x = (int) (x_num * rectx_size + (rectx_size - fm.stringWidth(s)) / 2);
            int y = (int) (y_num * recty_size + (recty_size + fm.getAscent()) / 2);
            g2d.drawString(s, x, y);
        }

        // g2d.setColor(Color.DARK_GRAY);
        g2d.setColor(Color.white);
        try {
            if (map[x_num][y_num]) {
                g2d.setColor(Color.white);
                g2d.fillRect((int) (x_num * rectx_size), (int) (y_num * recty_size), (int) rectx_size, (int) recty_size);
            } else {
                if (game_rules.is_grid) {
                    g2d.setColor(Color.GRAY);
                    g2d.drawRect((int) (x_num * rectx_size), (int) (y_num * recty_size), (int) rectx_size, (int) recty_size);
                }
            }
        } catch (Exception _) {}
    }

    int old_x = -1, old_y = -1;
    long old_time = -1;
    Drawer() {
        // Слушатели мышки
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paint_cell(e);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = (int) ((e.getX() - offsetX) / (rectx_size*scale));
                int y = (int) ((e.getY() - offsetY) / (recty_size*scale));
//                System.out.println("Нажал на квадрат x=" + x + "; y=" + y + "; Длина карты: " + map.length);
                if (x>=map.length || x<0 || y>=map.length || y<0) return;

                if (!(old_x == x && old_y == y && (System.currentTimeMillis() - old_time < 1000))) {
                    old_x = x;
                    old_y = y;
                    old_time = System.currentTimeMillis();
                    paint_cell(e);
                }
            }
        });

        addMouseWheelListener(e -> {
            // 1 = Приближение
            // -1 = Отдаление

            double MXBefore = (e.getX() - offsetX) / scale;
            double MYBefore = (e.getY() - offsetY) / scale;


            if (e.getPreciseWheelRotation() < 0) {
                scale += 0.21 + 0.25*scale;
            } else {
                scale -= 0.21 + 0.25*scale;
            }

            double MXAfter = MXBefore * scale;
            double MYAfter = MYBefore * scale;

            offsetX += e.getX() - MXAfter - offsetX;
            offsetY += e.getY() - MYAfter - offsetY;

            limitCamera();
            System.out.println("Scale: " + scale + "; offsetY= " + offsetY + "; offsetX= " + offsetX);
            repaint();
        });

    }

    private void paint_cell(MouseEvent e) {
        int x = (int) ((e.getX() - offsetX) / (rectx_size * scale));
        int y = (int) ((e.getY() - offsetY) / (recty_size * scale));


        map[x][y] = !map[x][y];
        repaint();
    }

    public void setMap(boolean[][] m) {
        map = m;
    }

    private void limitCamera() {

        if (scale < 1) {
            scale = 1;
        }

        // 1. Размеры карты в пикселях (до масштабирования)
        double mapWidth = rectx_size * map.length;
        double mapHeight = recty_size * map[0].length;

        // 2. С учётом масштабирования
        double scaledMapWidth = mapWidth * scale;
        double scaledMapHeight = mapHeight * scale;

        // 3. Границы смещения
        double minOffsetX = getWidth() - scaledMapWidth;
        double maxOffsetX = 0;

        double minOffsetY = getHeight() - scaledMapHeight;
        double maxOffsetY = 0;

        // 4. Ограничим, не прижимая к 0
        if (offsetX < minOffsetX) offsetX = minOffsetX;
        if (offsetX > maxOffsetX) offsetX = maxOffsetX;

        if (offsetY < minOffsetY) offsetY = minOffsetY;
        if (offsetY > maxOffsetY) offsetY = maxOffsetY;



    }



}
