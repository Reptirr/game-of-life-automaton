package com.game_life;

public class game_life {
    private static boolean[][] map = new boolean[53][53];

    public static boolean[][] next_gen() {
        boolean[][] new_map = new boolean[53][53];

        // Перебираем все поле, считаем соседей
        for (int y_m = 0; y_m < map.length; y_m++) {
            for(int x_m = 0; x_m < map.length; x_m++) {
                // Работа с клеткой
                byte num_nei = 0;
                try {
                    num_nei = num_neighbours(new int[]{x_m, y_m});
                } catch (Exception _) {
                }
                new_map[x_m][y_m] = rule_boss(map[x_m][y_m], num_nei);
            }
        }
        map = new_map;
        return map;
    }

    private static boolean rule_boss(boolean rect, byte num_nei) {
        if (rect) {
            if (num_nei < 2) return false;
            if (num_nei == 2 || num_nei == 3) return true;
            if (num_nei > 3) return false;
        } else {
            if (num_nei == 3) return true;
            if (num_nei != 3) return false;
        }

        return rect;
    }



    public static byte num_neighbours(int[] rect_i) {
        // rect_i = [x, y]

        // Считает соседей около клетки
        // 0 0 0
        // 0 1 0
        // 0 0 0

        int num_nei = 0;
        int x = rect_i[0];
        int y = rect_i[1];
        int rows = map.length;
        int cols = map[0].length;

        if (x > 0 && map[x - 1][y]) num_nei++;
        if (x < rows - 1 && map[x + 1][y]) num_nei++;
        if (y > 0 && map[x][y - 1]) num_nei++;
        if (y < cols - 1 && map[x][y + 1]) num_nei++;
        if (x > 0 && y > 0 && map[x - 1][y - 1]) num_nei++;
        if (x < rows - 1 && y > 0 && map[x + 1][y - 1]) num_nei++;
        if (x > 0 && y < cols - 1 && map[x - 1][y + 1]) num_nei++;
        if (x < rows - 1 && y < cols - 1 && map[x + 1][y + 1]) num_nei++;



        return (byte) num_nei;
    }

    public static void create_fig() {
        for (int x = 23; x < 23+3; x++) {
            map[x][23] = true;
        }
    }

    public static boolean[][] get_map() {
        return map;
    }
}
