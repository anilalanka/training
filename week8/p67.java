import java.io.*;
public class p67 {
    public static void main(String[] args) throws Exception{
        BufferedReader br1 = new BufferedReader(new FileReader("p067_triangle.txt"));
        int n = 0;
        while(br1.readLine()!= null)n++;
        br1.close();
        BufferedReader br2 = new BufferedReader(new FileReader("p067_triangle.txt"));
        int[][] tri = new int[n][n];
        for(int i=0;i<n;i++){
            String[] s = (br2.readLine()).split(" ");
            for(int j=0;j<=i;j++){
                tri[i][j] = Integer.parseInt(s[j]);
            }
        }
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<=i;j++){
                tri[i][j] += Math.max(tri[i+1][j], tri[i+1][j+1]);
            }
        }
        System.out.println(tri[0][0]);
    }
}
