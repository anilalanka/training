import java.io.*;
public class p59 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("p059_cipher.txt"));
        String[] s = (br.readLine()).split(",");
        int[] cipher = new int[s.length];
        for(int i=0;i<s.length;i++)
            cipher[i] = Integer.parseInt(s[i]);
        int[] key = findKey(cipher);
        System.out.print("KEY: ");
        for(int i=0;i<3;i++){
            System.out.print((char)key[i]);
        }
        System.out.print("\nMESSAGE: ");
        for(int i=0;i<cipher.length;i++){
            int temp = cipher[i]^key[i%3];
            System.out.print((char)temp);
        }
        System.out.println();
    }
    static int[] findKey(int[] cipher){
        int[] key = new int[3];
        int[][] count = new int[128][3]; // counts frequency of each ascii value 
        for(int i=0;i<cipher.length;i++){
            int temp = cipher[i]^(int)(' '); // SPACE appears frequently
            count[temp][i%3]++;
        }
        for(int j=0;j<3;j++) {
            int maxCount = 0;
            int max =0;
            for (int i = 0; i < count.length; i++) {
                if (count[i][j] > maxCount) {
                    maxCount = count[i][j];
                    max = i;
                }
            }
            key[j] = max;
        }
        return key;
    }
}
