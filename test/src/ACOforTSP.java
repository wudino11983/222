import java.io.File;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.random;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;


public class ACOforTSP {

   
    //城市的距离表
    private double[][] distance;   
    //距离的倒数表
    private double[][] heuristic;  
    //启发信息表
    private double[][] pheromone;  
    //权重
    private int alpha, beta;
    //迭代的次数
    private int iterationTimes;
    //蚂蚁的数量
    private int numbersOfAnt;
    //蒸发率
    private double rate;

    ACOforTSP (String file, int iterationTimes, int numbersOfAnt, int alpha, int beta, double rate) {
       
        //加载文件
        this.initializeData(file);
        //初始化参数
        this.iterationTimes = iterationTimes;
        //设置蚂蚁数量
        this.numbersOfAnt = numbersOfAnt;
        //设置权重
        this.alpha = alpha;
        this.beta = beta;
        //设置蒸发率
        this.rate = rate;
    }
   
    private void initializeData(String filename) {

        //定义内部类
        class City {

            int no;
            double x;
            double y;

            City(int no, double x, double y) {

                this.no = no;
                this.x = x;
                this.y = y;
            }

            private double getDistance(City city) {

                return sqrt(pow((x - city.x), 2) + pow((y - city.y), 2));
            }
        }

        try {
            //定义HashMap保存读取的坐标信息
            HashMap<Integer, City> map = new HashMap<Integer, City>();
            //读取文件
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            for (String str = reader.readLine(); str != null; str = reader.readLine()) {
                //将读到的信息保存入HashMap
                if (str.matches("([0-9]+)(\\s*)([0-9]+)(.?)([0-9]*)(\\s*)([0-9]+)(.?)([0-9]*)")) {

                    String[] data = str.split("(\\s+)");
                    City city = new City(Integer.parseInt(data[0]),
                            Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]));

                    map.put(city.no, city);
                }
            }
            //分配距离矩阵存储空间
            distance = new double[map.size() + 1][map.size() + 1];
            //分配距离倒数矩阵存储空间
            heuristic = new double[map.size() + 1][map.size() + 1];
            //分配信息素矩阵存储空间
            pheromone = new double[map.size() + 1][map.size() + 1];
            for (int i = 1; i < map.size() + 1; i++) {
                for (int j = 1; j < map.size() + 1; j++) {
                    //计算城市间的距离，并存入距离矩阵
                    distance[i][j] = map.get(i).getDistance(map.get(j));
                    //计算距离倒数，并存入距离倒数矩阵
                    heuristic[i][j] = 1 / distance[i][j];
                    //初始化信息素矩阵
                    pheromone[i][j] = 1;
                }
            }

        } catch (Exception exception) {

            System.out.println("初始化数据失败！");
        }
    }

    class Ant {
       
        //已访问城市列表
        private boolean[] visited;
        //访问顺序表
        private int[] tour;
        //已访问城市的个数
        private int n;
        //总的距离
        private double total;

        Ant() {
            //给访问顺序表分配空间
            tour = new int[distance.length+1];
            //已存入城市数量为n，刚开始为0
            n = 0;
            //将起始城市1,放入访问结点顺序表第一项
            tour[++n] = 1;
            //给已访问城市结点分配空间
            visited = new boolean[distance.length];
            //第一个城市为出发城市，设置为已访问
            visited[tour[n]] = true;
        }

        private int chooseCity() {

            //用来random的随机数
            double m = 0;
            //获得当前所在的城市号放入j,如果和j相邻的城市没有被访问，那么加入m
            for (int i = 1, j = tour[n]; i < pheromone.length; i++) {

                if (!visited[i]) {
                    m += pow(pheromone[j][i], alpha) * pow(heuristic[j][i], beta);
                }
            }

            //保存随机到的数
            double p = m * random();
            //寻找被随机到的城市
            double k = 0;
            //保存找到的城市
            int q = 0;
            for (int i = 1, j = tour[n]; k < p; i++) {

                if (!visited[i]) {
                   
                    k += pow(pheromone[j][i], alpha) * pow(heuristic[j][i], beta);
                    q = i;
                }
            }
           
            return q;
        }

        private void constructSolution () {
           
            while (n != (distance.length-1) ) {
               
                //选取下一个城市
                int p = chooseCity();
                //计算总的距离
                total += distance[tour[n]][p];
                //将选取到的城市放入已访问列表
                tour[++n] = p;
                //将选取到的城市标记为已访问
                visited[p] = true;
            }
           
            //回到起点
            total += distance[tour[1]][tour[n]];
            //将起点加入访问顺序表的最后
            tour[++n] = tour[1];
        }
       
        private void releasePheromone() {
           
            //释放信息素的大小
            double t = 1/total;
            //释放信息素
            for (int i=1;i<tour.length-1;i++) {
           
                pheromone[tour[i]][tour[i+1]] += t;
                pheromone[tour[i+1]][tour[i]] += t;
            }      
        }
       
    }

    public void go() {
       
        //保存最好的路径和路径长度
        double bestTotal = Double.MAX_VALUE;
        int[] bestTour = new int[distance.length+1];
       
        //新建蚂蚁数组，用来引用所创建的蚂蚁
        Ant[] ant = new Ant[numbersOfAnt];
       
        //进行iterationTimes次迭代
        while (iterationTimes != 0) {
            //初始化新的一批蚂蚁（这里用构造新的蚂蚁代替重置蚂蚁状态）
            for (int i=0; i<numbersOfAnt; i++) {
                ant[i] = new Ant();
            }
           
            //进行一次迭代（即让所有的蚂蚁构建一条路径）
            for (int i=0; i<numbersOfAnt; i++) {

                ant[i].constructSolution();
                //如果蚂蚁构建的路径长度比上次最好的还好，那么保存这个长度和它所走的路径
                if (ant[i].total<bestTotal) {
                   
                    bestTotal = ant[i].total;
                    System.arraycopy(ant[i].tour, 1, bestTour, 1, bestTour.length-1);
                }
            }
           
            //蒸发信息素
            evaporatePheromone();
           
            //释放信息素
            for (int i=0; i<numbersOfAnt; i++) {

                ant[i].releasePheromone();
            }
           
            //报告本次迭代的信息
            System.out.format("本次为倒数第%d次迭代,当前最优路径长度为%10.2f\n",iterationTimes,bestTotal);
           
            //迭代总数减去1，进行下次迭代
            iterationTimes--;
        }
       
        //输出最好的路径长度
        System.out.format("得到的最优的路径长度为:%10.2f\n",bestTotal);
        //输出最好的路径
        System.out.println("最优路径如下：");
        for (int i=1; i<bestTour.length; i++) {
           
            System.out.print("→"+bestTour[i]);
        }
    }

    private void evaporatePheromone() {
       
        for (int i = 1; i < pheromone.length; i++)
            for (int j = 1; j < pheromone.length; j++) {

                pheromone[i][j] *= 1-rate;
            }
       
    }
}