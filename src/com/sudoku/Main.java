package com.sudoku;
import java.util.*;
import java.io.*;
public class Main {

    public static boolean helper(char[][] board, int row, int col)
    {
        if(row == board.length)
        {
            return true;
        }
        int new_row = 0;
        int new_col = 0;
        if(col != board.length - 1)
        {
            new_row = row;
            new_col = col + 1;
        }
        else
        {
            new_row = row + 1;
            new_col = 0;
        }
        if(board[row][col] != '*')
        {
            if(helper(board, new_row, new_col))
            {
                return true;
            }
        }
        else
        {
            for(int i = 1; i <= 9; i++)
            {
                if(isSafe(board, row, col, i))
                {
                    board[row][col] = (char)(i + '0');
                    if(helper(board, new_row, new_col))
                    {
                        return true;
                    }
                    else
                    {
                        board[row][col] = '*';
                    }
                }
            }
        }
        return false;
    }
    public static boolean isSafe(char[][] board, int row, int col, int n)
    {
        for(int i = 0; i < board.length; i++)
        {
            if (board[i][col] == (char) (n + '0')) {
                return false;
            }
            if (board[row][i] == (char) (n + '0')) {
                return false;
            }
        }
            int start_row = (row/3)*3;
            int start_col = (col/3)*3;

            for(int i = start_row; i < start_row + 3; i++)
            {
                for(int j = start_col; j < start_col + 3; j++)
                {
                    if(board[i][j] == (char)(n + '0'))
                    {
                        return false;
                    }
                }
            }
        return true;
    }
    public static void solveSudoku(char[][] board)
    {
        helper(board, 0,0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] sudoku = new char[9][9];
        System.out.println("Sudoku solver using Backtracking Algorithm");
        System.out.println("Enter a number between 1 to 9 for every cell to form a puzzle");
        System.out.println("For every blank cell enter *");
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                System.out.println("Enter data for cell " +"("+i +"," +j +"):");
                sudoku[i][j] = sc.next().charAt(0);
            }
        }
        solveSudoku(sudoku);
        System.out.println("Solved Puzzle:");
        System.out.println();
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                System.out.print(sudoku[i][j] +" ");
            }
            System.out.println();
        }
    }
}
