import java.io.*;
import java.util.*;
public class TarjanLca {
    public static void main(String[] args) throws Exception {
        new TarjanLca().run();}

    int[] h,ne,to,fa,pa,qy[];
    int ct = 0;
    int[] qh,qne,qto,index,result;
    int qct = 0;

    int root(int x){
        if(fa[x]==x){
            return x;
        }else{
            int pt = x;
            while(pt!=fa[pt]){
                pt = fa[pt];
            }
            while(x!=fa[x]){
                int cur = x;
                x = fa[x];
                fa[cur] = pt;
            }
            return pt;
        }
    }

    void addEdge(int u, int v){
        to[ct] = v;
        ne[ct] = h[u];
        h[u] = ct++;
    }

    void addQuery(int u, int v, int i) {
        qto[qct] = v;
        qne[qct] = qh[u];
        index[qct] = i;
        qh[u] = qct++;
    }

    void solve() {
        int n  = ni();
        int m  = ni();
        h = new int[n+1];
        Arrays.fill(h,-1);
        ne = new int[2*n];
        to = new int[2*n];
        fa = new int[n+1];
        pa = new int[n+1];

        qh = new int[n+1];
        Arrays.fill(qh,-1);
        
        qne = new int[2*n];
        qto = new int[2*n];
        index = new int[2*n];
        result = new int[m];

        int rt = ni();

        for(int i=0;i<n-1;++i){
            int x = ni();
            int y = ni();
            addEdge(x,y);
            addEdge(y,x);
        }

        for(int i=0;i<m;++i){
            int x = ni();
            int y = ni();
            addQuery(x,y,i);
            addQuery(y,x,i);
        }

        int stk[] = new int[n];
        int p = 0;
        boolean vis[] = new boolean[n+1];
        fa = new int[n+1];
        stk[p++] = rt;

        for(int i=1;i<=n;++i){
            fa[i] = i;
        }

        while(p>0) {
            rt = stk[--p];
            if(rt>=0) {
                stk[p++] = ~rt;
                for (int i = h[rt]; i != -1; i = ne[i]) {
                    int vv = to[i];
                    if (pa[rt] == vv) continue;
                    pa[vv] = rt;
                    stk[p++] = vv;
                }
            }else{
                rt = ~rt;
                vis[rt] = true;
                for (int i = qh[rt] ;i != -1; i = qne[i]) {
                    int v = qto[i];
                    if (vis[v]) {
                        result[index[i]] = root(v);
                    }
                }
                fa[rt] = pa[rt];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++)
            sb.append(result[i]).append("\n");

        println(sb);

    }

    long gcd(long a,long b){ return b==0?a: gcd(b,a%b);}
    int gcd(int a,int b){ return b==0?a: gcd(b,a%b);}
    InputStream is;PrintWriter out;
    void run() throws Exception {is = System.in;
        //   is  = new FileInputStream("C:\\Users\\Luqi\\Downloads\\P3119_6.in");
        out = new PrintWriter(System.out);solve();out.flush();}
    private byte[] inbuf = new byte[2];
    public int lenbuf = 0, ptrbuf = 0;
    private int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {lenbuf = is.read(inbuf);} catch (IOException e) {throw new InputMismatchException();}
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];}
    private boolean isSpaceChar(int c) {return !(c >= 33 && c <= 126);}
    private int skip() {int b;while((b = readByte()) != -1 && isSpaceChar(b));return b;}
    private double nd() {return Double.parseDouble(ns());}
    private char nc() {return (char) skip();}
    private char ncc() {int b;while((b = readByte()) != -1 && !(b >= 32 && b <= 126));return (char)b;}
    private String ns() {int b = skip();StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);b = readByte(); }
        return sb.toString();}
    private char[] ns(int n) {char[] buf = new char[n];int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) { buf[p++] = (char) b;b = readByte(); }
        return n == p ? buf : Arrays.copyOf(buf, p);}
    private String nline() {int b = skip();StringBuilder sb = new StringBuilder();
        while (!isSpaceChar(b) ) { sb.appendCodePoint(b);b = readByte(); }
        return sb.toString();}
    private char[][] nm(int n, int m) {char[][] a = new char[n][];for (int i = 0; i < n; i++) a[i] = ns(m);return a;}
    private int[] na(int n) {int[] a = new int[n];for (int i = 0; i < n; i++) a[i] = ni();return a;}
    private long[] nal(int n) { long[] a = new long[n];for (int i = 0; i < n; i++) a[i] = nl();return a;}
    private int ni() { int num = 0, b; boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')){};
        if (b == '-') { minus = true; b = readByte(); }
        while (true) {
            if (b >= '0' && b <= '9') num = (num << 3) + (num << 1) + (b - '0');
            else return minus ? -num : num;
            b = readByte();}}
    private long nl() { long num = 0; int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')){};
        if (b == '-') { minus = true; b = readByte(); }
        while (true) {
            if (b >= '0' && b <= '9')  num = num * 10 + (b - '0');
            else return minus ? -num : num;
            b = readByte();}}
    private void print(Object obj){out.print(obj);}
    private void println(Object obj){out.println(obj);}
    private void println(){out.println();}
}
