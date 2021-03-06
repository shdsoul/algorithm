package First_Level_0_Basic_Algorithm.First_Basic.b_802_interval_and;

import java.io.*;
import java.util.*;

public class Main{
    static int N = 300010;
    static int[] a = new int[N];
    static int[] s = new int[N];
    static List<Integer> allS = new ArrayList<>();
    static List<PIIs> add = new ArrayList<>();
    static List<PIIs> query = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split(" ");
        int n = Integer.parseInt(str[0]); // n次操作
        int m = Integer.parseInt(str[1]); // m次询问
        for (int i = 0; i < n; i++) {
            String[] str1 = reader.readLine().split(" ");
            int x = Integer.parseInt(str1[0]);
            int c = Integer.parseInt(str1[1]);
            add.add(new PIIs(x, c));
            allS.add(x);
        }
        for (int i = 0; i < m; i++) {
            String[] str2 = reader.readLine().split(" ");
            int l = Integer.parseInt(str2[0]);
            int r = Integer.parseInt(str2[1]);
            query.add(new PIIs(l, r));
            allS.add(l);
            allS.add(r);
        }
        reader.close();
        Collections.sort(allS);
        int unique = unique(allS);
        allS = allS.subList(0, unique);
        for (PIIs item : add) {
            int x = find(item.getFirst(), allS);
            a[x] += item.getSecond();
        }
        for (int i = 1; i <= allS.size(); i++) {
            s[i] = s[i - 1] + a[i];
        }
        for (PIIs item : query) {
            int l = find(item.getFirst(), allS);
            int r = find(item.getSecond(), allS);
            System.out.println(s[r] - s[l - 1]);
        }
    }

    public static int unique(List<Integer> list) {
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0 || list.get(i) != list.get(i - 1)) {
                list.set(j++, list.get(i));
            }
        }
        return j;
    }

    public static int find(int x, List<Integer> allS) {
        int l = 0;
        int r = allS.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (allS.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r + 1;
    }
}

class PIIs implements Comparable<PIIs>{
    private int first;
    private int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public PIIs(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(PIIs o) {
        return Integer.compare(first, o.first);
    }
}
