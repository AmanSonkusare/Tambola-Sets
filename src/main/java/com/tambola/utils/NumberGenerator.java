package com.tambola.utils;

import com.tambola.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {

    public static List<Ticket> list = new ArrayList<>();

    public static void startGame() {
        Ticket ticket = new Ticket();

        list.add(ticket);

        int[][] game = new int[3][9];


        int occupancyLimit = 15;
        while (occupancyLimit > 0) {
            int i = getRandomNumber(3);
            int j = getRandomNumber(9);

            int data = validateAndReturnNume(i, j, game);

            if (data > 0) {

                game[i][j] = data;
                occupancyLimit--;
            }
        }

        ticket.setGame(game);

    }


    private static int validateAndReturnNume(int i, int j, int[][] game) {

        if (game[i][j] != 0) {
            return -1;
        }
        int rowCount = 0;
        for (int r = 0; r < 3; r++) {
            if (game[r][j] != 0) {
                rowCount++;
            }
        }

        if (rowCount >= 2) {
            return -1;
        }
        int columnCount = 0;
        for (int c = 0; c < 9; c++) {
            if (game[i][c] != 0) {
                int a = game[i][c];


                columnCount++;
            }
        }

        if (columnCount >= 5) {
            return -1;
        }

        int data = 0;
        boolean isValueSet = false;
        do {
            data = getRandomNumberForCol(j);
            isValueSet = isValueExitCol(game, i, j, data);
        } while (isValueSet);
        return data;
    }

    private static boolean isValueExitCol(int[][] game, int i, int j, int data) {
        boolean status = false;
        for (int k = 0; k < 3; k++) {
            if (game[k][j] == data) {
                status = true;
                break;
            }
        }
        return status;
    }


    private static int getRandomNumberForCol(int j) {
        int min = j * 10 + 1;
        int max = j * 10 + 9;
        Random r = new Random();
        int randomNum = r.nextInt(max - min + 1) + min;
        return r.nextInt(max - min + 1) + min;
    }

    private static int getRandomNumber(int max) {
        return (int) (Math.random() * max);
    }

}
