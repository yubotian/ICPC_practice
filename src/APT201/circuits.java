package APT201;

public class circuits {
        public int howLong(String[] connects, String[] costs) {
                int N = connects.length;
                int cost[][] = new int[N][N];
                for (int i = 0; i < N; i++)
                        if (!connects[i].equals("")) {
                                String t1[] = connects[i].split(" ");
                                String t2[] = costs[i].split(" ");
                                
                                for (int j = 0; j < t1.length; j++) {
                                        // System.out.println( i + " " + j + " " + t1[j] + " " +
                                        // t2[j] );
                                        int t = Integer.parseInt(t1[j]);
                                        int c = Integer.parseInt(t2[j]);
                                        if (c > cost[i][t])
                                                cost[i][t] = c;
                                }
                        }
                for (int k = 0; k < N; k++)
                        for (int i = 0; i < N; i++)
                                for (int j = 0; j < N; j++) {
                                        if (cost[i][j] < cost[i][k] + cost[k][j] && cost[i][k] != 0
                                                        && cost[k][j] != 0) {
                                                cost[i][j] = cost[i][k] + cost[k][j];
                                        }
                                }

                int ans = 0;
                for (int i = 0; i < N; i++)
                        for (int j = 0; j < N; j++)
                                if (cost[i][j] > ans)
                                        ans = cost[i][j];

                return ans;

        }
        
        
        public static void main(String[] args){
          String[] dependencies = {"1 2", "2", ""};
          String[] weights = {"5 3", "7", ""};
          
          circuits c = new circuits();
          int result1 = c.howLong(dependencies, weights); 
          System.out.printf("Expected 12, got %d", result1);
  }
  
}