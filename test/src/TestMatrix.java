public class TestMatrix {
    

    public static void main(String[] args) {
        
        //         测试数据
        //        double[][] a={{0.2368,0.2471,0.2568,1.2671},
        //                {1.1161,0.1254,0.1397,0.149},
        //                {0.1582,1.1675,0.1768,0.1871},
        //                {0.1968,0.2071,1.2168,0.2271}};
        
        
        double[][] a={{1,3,1},
                {2,1,1},
                {2,2,1}
                 };
        double[][] b={{1,3,1},
                {2,1,1},
                {2,2,1}
                 };
        
        double[][] c= new double[3][3];
        
        TestMatrix tm=new TestMatrix();
        tm.Mrinv(a, 3);
                
        //验证   A*A-1=E
        tm.Mrcheng(a,b,c,3,3,3);        
        tm.PrintMatrix(c, 3);
    }

    public static void PrintMatrix(double[][] a, int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
            {
                System.out.print(a[i][j]+"  ");
            }
            System.out.println();
        }
    }
    
    ////////////////////////////////////////////////////////////////////////
    //函数：Mrinv
    //功能：求矩阵的逆
    //参数：n---整数，矩阵的阶数
    //a---Double型n*n二维数组，开始时为原矩阵，返回时为逆矩阵
    ////////////////////////////////////////////////////////////////////////
    public static void Mrinv(double[][] a, int n) {
        int i, j, row, col, k;
        double max, temp;
        int[] p = new int[n];
        double[][] b = new double[n][n];
        for (i = 0; i < n; i++) {
            p[i] = i;
            b[i][i] = 1;
        }

        for (k = 0; k < n; k++) {
            // 找主元
            max = 0;
            row = col = i;
            for (i = k; i < n; i++)
                for (j = k; j < n; j++) {
                    temp = Math.abs(b[i][j]);
                    if (max < temp) {
                        max = temp;
                        row = i;
                        col = j;
                    }
                }
            // 交换行列，将主元调整到 k 行 k 列上
            if (row != k) {
                for (j = 0; j < n; j++) {
                    temp = a[row][j];
                    a[row][j] = a[k][j];
                    a[k][j] = temp;
                    temp = b[row][j];
                    b[row][j] = b[k][j];
                    b[k][j] = temp;
                }
                i = p[row];
                p[row] = p[k];
                p[k] = i;
            }
            if (col != k) {
                for (i = 0; i < n; i++) {
                    temp = a[i][col];
                    a[i][col] = a[i][k];
                    a[i][k] = temp;
                }
            }
            // 处理
            for (j = k + 1; j < n; j++)
                a[k][j] /= a[k][k];
            for (j = 0; j < n; j++)
                b[k][j] /= a[k][k];
            a[k][k] = 1;

            for (j = k + 1; j < n; j++) {
                for (i = 0; i < k; i++)
                    a[i][j] -= a[i][k] * a[k][j];
                for (i = k + 1; i < n; i++)
                    a[i][j] -= a[i][k] * a[k][j];
            }
            for (j = 0; j < n; j++) {
                for (i = 0; i < k; i++)
                    b[i][j] -= a[i][k] * b[k][j];
                for (i = k + 1; i < n; i++)
                    b[i][j] -= a[i][k] * b[k][j];
            }
            for (i = 0; i < k; i++)
                a[i][k] = 0;
            a[k][k] = 1;
        }
        // 恢复行列次序；
        for (j = 0; j < n; j++)
            for (i = 0; i < n; i++)
                a[p[i]][j] = b[i][j];
    }
    
    //矩阵乘法
    public void Mrcheng(double[][] a,double[][] b,double[][]c,int m,int n,int l)
    { 
        double[][] d=new double[m][l];
        //使用中间变量d,是防止c=a或c=b的情形下计算出错
        int i,j,k;
        for(i=0;i<m;i++)
        for(j=0;j<l;j++)
        { 
            d[i][j]=0;
            for(k=0;k<n;k++)
            d[i][j]+=a[i][k]*b[k][j];
        }
        
        for(i=0;i<m;i++)
        for(j=0;j<l;j++)
        c[i][j]=d[i][j];
    }
    
    
    
    
}