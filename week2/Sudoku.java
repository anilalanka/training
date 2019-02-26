import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class Sudoku {
    public static void main(String[] args) throws IOException{
        File file  = new File("Sudoku.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int count=0;
        int sum=0;
        int value;
        while(br.readLine()!= null){
            count++;
            System.out.println("Grid "+count);
            int[][] arr = new int[9][9];
            for(int i=0;i<9;i++) {
                String str = br.readLine();
                String[] elements = str.split("");
                for (int j=0;j<9;j++) {
                    arr[i][j] = Integer.parseInt(elements[j]);
                }
            }
            solveSudoku(arr);
            printSolution(arr);
            value = arr[0][0]*100 + arr[0][1]*10 + arr[0][2];
            sum = sum + value;
        }
        System.out.println("sum of 3 digit numbers = "+sum);

    }
    static void printSolution(int[][] arr){
        for(int i=0;i<9;i++){
            for(int j =0;j<9;j++){
                System.out.print(arr[i][j]+"  ");
            }
            System.out.println();
        }
    }
    public static boolean solveSudoku(int[][] arr){
        int[] ind = firstUnassignedIndex(arr);
        int m = ind[0];
        int n = ind[1];
        if(m==-1 && n==-1){
            return true;
        }
        int num =1;
        while(num<=9){
            if(isValidNumber(arr,m,n,num)){
                arr[m][n] = num;
                if(solveSudoku(arr))
                    return true;
                arr[m][n] = 0;
            }
            num++;
        }
        return false;
    }
    static int[] firstUnassignedIndex(int[][] arr){
        for(int i =0; i<9;i++){
            for(int j=0;j<9;j++){
                int[] ind = {i,j};
                if(arr[i][j] == 0)
                    return ind;
            }
        }
        int[] ind = {-1,-1}; // if all elements are assigned
        return ind;
    }
    static boolean rowOK(int[][] arr,int m,int n,int num){
        for(int j=0;j<9;j++){
            if(arr[m][j] == num)
                return false;
        }
        return true;
    }
    static boolean colOK(int[][] arr,int m,int n,int num){
        for(int i=0;i<9;i++){
            if(arr[i][n] == num)
                return false;
        }
        return true;
    }
    static boolean blockOK(int[][] arr , int m, int n, int num){
        for(int i=(m/3)*3;i<(m/3)*3+3;i++){
            for(int j=(n/3)*3;j<(n/3)*3+3;j++){
                if(arr[i][j] == num){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean isValidNumber(int[][] arr , int m, int n, int num){
        if(rowOK(arr,m,n,num)){
            if(colOK(arr,m,n,num)){
                if(blockOK(arr,m,n,num)){
                    return true;
                }
            }
        }
        return false;
    }
}
