package com.game_life;

import java.util.Random;

public class game_life {
    private static boolean[][] map = new boolean[game_rules.size][game_rules.size];

    public static boolean[][] next_gen() {
        boolean[][] new_map = new boolean[game_rules.size][game_rules.size];

        // Перебираем все поле, считаем соседей
        for (int y_m = 0; y_m < map.length; y_m++) {
            for(int x_m = 0; x_m < map.length; x_m++) {
                // Работа с клеткой
                byte num_nei = 0;
                num_nei = num_neighbours(new int[]{x_m, y_m});
                try {
                    new_map[x_m][y_m] = rule_boss(map[x_m][y_m], num_nei);
                } catch (Exception _) {}
            }
        }
        map = new_map;
        return map;
    }

    private static boolean rule_boss(boolean rect, byte num_nei) {
        byte[] B = game_rules.B;
        byte[] S = game_rules.S;
        if (rect) {
            for (int si = 0; si < 10; si++) {
                if (num_nei == S[si]) return true;
            }
            return false;
        } else {
            for (int bi = 0; bi < 10; bi++) {
                if (num_nei == B[bi]) return true;
            }
            return false;
        }
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


        if (game_rules.is_foneim) {
            // Соседство Фоннейма
            // Смотри по вертикали
            try {
                if (map[x][y - 1]) num_nei++; // Смотрим сверху
                if (map[x][y + 1]) num_nei++; // Смотрим снизу
                // Смотрим по горизонтали
                if (map[x - 1][y]) num_nei++; // Смотрим слева
                if (map[x + 1][y]) num_nei++; // Смотрим справа
            } catch (Exception _) {}
        } else {
            // Соседство Мурра
            for (int dy = -1; dy < 2; dy++) {
                for (int dx = -1; dx < 2; dx++) {
                    int xt = x + dx;
                    int yt = y + dy;
                    if (dy == 0 && dx == 0) continue;
                    try {
                        if (map[xt][yt]) num_nei++;
                    } catch (Exception _) {}
                }
            }
        }


        return (byte) num_nei;
    }

    public static boolean[][] get_map() {
        return map;
    }

    public static boolean[][] clear_map() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                map[x][y] = false;
            }
        }
        return map;
    }

    public static void rand_map() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                map[x][y] = new Random().nextBoolean();
            }
        }
    }

    public static void update_map() {
        boolean[][] new_map = new boolean[game_rules.size][game_rules.size];

        // Перебираем все поле, считаем соседей
        for (int y_m = 0; y_m < map.length; y_m++) {
            for(int x_m = 0; x_m < map.length; x_m++) {
                // Работа с клеткой
                try {
                    new_map[x_m][y_m] = map[x_m][y_m];
                } catch (Exception _) {}

            }
        }
        map = new_map;
    }

}
