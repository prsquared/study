// Java Program to implement 0-1 BFS
import java.util.*;

public class BFSShortestPath {

    private Queue<Integer> rq = new LinkedList<>();
    private Queue<Integer> cq = new LinkedList<>();
    private int nodesInNextLayer = 0;
    private int dr[]={-1,1,0,0};
    private int dc[]={0,0,-1,1};
    private int solve( List<List<Integer>> grid,int m,int n){
        boolean exitReached=false;
        boolean[][] visited = new boolean[m][n];
        rq.add(0);
        cq.add(0);
        visited[0][0]=true;
        int nodesLeftInLayer=1;
        int moveCount=0;
        while(rq.size()>0 && cq.size()>0){
           Integer r = rq.remove();
           Integer c = cq.remove();
           if(grid.get(r).get(c)==9){
               exitReached = true;
               break;
           }
           exploreNeighbours(grid,visited,m,n,r,c);
           nodesLeftInLayer--;
           if(nodesLeftInLayer==0){
               nodesLeftInLayer=nodesInNextLayer;
               nodesInNextLayer=0;
               moveCount++;
           }
        }

        if(exitReached){
            return moveCount;
        }
        return -1;

    }

    private void exploreNeighbours( List<List<Integer>> grid,  boolean[][] visited, int m, int n, Integer r, Integer c) {
        for(int i=0;i<4;i++){
            int rr = r + dr[i];
            int cc = c + dc[i];
            if(rr<0 || rr>=m || cc<0|| cc>=n || grid.get(rr).get(cc)==0 || visited[rr][cc]){
                continue;
            }
            rq.add(rr);
            cq.add(cc);
            visited[rr][cc]=true;
            nodesInNextLayer++;
        }
    }

    public static void main(String[] args) {
       List<List<Integer>> grid = new ArrayList<>();
       grid.add(Arrays.asList(1,1,1,1,0));
       grid.add(Arrays.asList(1,0,1,0,0));
       grid.add(Arrays.asList(1,0,9,1,0));
       grid.add(Arrays.asList(1,1,1,1,1));

       BFSShortestPath obj = new BFSShortestPath();
       System.out.println("Shortest path distance is:"+obj.solve(grid,grid.size(),grid.get(0).size()));
    }
}
