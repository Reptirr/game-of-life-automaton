package com.game_life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// Унаследуем класс окна отображения
public class Drawer extends JPanel {
    private boolean[][] map;
    private double recty_size;
    private double rectx_size;

    private boolean is_log_nei = false;

    // Функция рисования в окне отображения
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int rects_num = MapSize.size * MapSize.size;
        double rects = Math.sqrt(rects_num);

        int mx = getWidth();
        int my = getHeight();

        recty_size = my/rects;
        rectx_size = mx/rects;

        setBackground(Color.black);

        for (int y_num = 0; y_num < rects; y_num++) {
            for (int x_num = 0; x_num < rects; x_num++) {
                // Пишем число соседей в середине клетки
                byte num_nei = 0;
                try {
                    num_nei = game_life.num_neighbours(new int[]{x_num, y_num});
                } catch (Exception _) {}
                if (is_log_nei) {
                    g2d.setColor(Color.BLUE);
                    FontMetrics fm = g2d.getFontMetrics();
                    String s = String.valueOf( num_nei);
                    int x = (int) (x_num * rectx_size + (rectx_size - fm.stringWidth(s)) / 2);
                    int y = (int) (y_num * recty_size + (recty_size + fm.getAscent()) / 2);
                    g2d.drawString(s, x, y);
                }

                // g2d.setColor(Color.DARK_GRAY);
                g2d.setColor(Color.white);
                if (map[x_num][y_num]) {
                    g2d.fillRect((int) (x_num * rectx_size), (int) (y_num * recty_size), (int) rectx_size, (int) recty_size);
                } else {
                    // g2d.drawRect((int) (x_num * rectx_size), (int) (y_num * recty_size), (int) rectx_size, (int) recty_size);
                }
            }
        }

    }

    int old_x = -1, old_y = -1;
    long old_time = -1;
    Drawer(boolean[][] m) {
        map = m;

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
                int x = (int) (e.getX() / rectx_size);
                int y = (int) (e.getY() / recty_size);

                if (!(old_x == x && old_y == y && (System.currentTimeMillis() - old_time < 1000))) {
                    old_x = x;
                    old_y = y;
                    old_time = System.currentTimeMillis();
                    paint_cell(e);
                }
            }
        });

    }

    private void paint_cell(MouseEvent e) {
        int x = (int) (e.getX() / rectx_size);
        int y = (int) (e.getY() / recty_size);

        map[x][y] = !map[x][y];
        repaint();
    }

    public void setMap(boolean[][] m) {
        this.map = m;
    }

    public void setLog(boolean is_l_n) {
        is_log_nei = is_l_n;
    }

}
