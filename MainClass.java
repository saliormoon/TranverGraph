import java.io.*;
import java.util.*;

/**
 * Created by w81021089 on 2016/9/19.
 */
public class MainClass {
    static int total=0;
    static  Map<Long,Long> map1=new HashMap<Long,Long>();//存放映射关系图
    static ArrayList<Long>list1=new ArrayList<Long>();//存放原始图
    static ArrayList<Long>list2=new ArrayList<Long>();//存放转换图
    static long value1=0;
    static long value2=0;
    public static void main(String[] args)throws Exception{
        long startTime=System.currentTimeMillis();//获取开始时间
        System.out.println("开始时间是："+startTime+"ms");
        String pathname="//home//wangyufeng//cit-Patents.txt";//文件路径
        File filename=new File(pathname);//生成文件
        InputStreamReader reader=new InputStreamReader(new FileInputStream(filename));//读取指定文件路径下的文件
        BufferedReader br=new BufferedReader(reader);//为其他reader提供缓冲功能
        //除去有注释的行
        String line1=br.readLine();
        String line2=br.readLine();
        String line3=br.readLine();
        String line4=br.readLine();
        String line=br.readLine();//读取文件中的一行
        while(line!=null){
            total++;
            String[]arr1=line.split("[\t ]");
            if(arr1.length == 2) {
                long  src = Long.parseLong(arr1[0]);
                long  dst = Long.parseLong(arr1[1]);
                //list1.add(src);
                //list1.add(dst);
                if (map1.containsKey(src)) {
                    value2 = map1.get(src);
                    list2.add(value2);
                } else {
                    value1++;
                    map1.put(src, value1);
                    list2.add(value1);
                }
                src = value1;
                if (map1.containsKey(dst)) {
                    value2= map1.get(dst);
                    list2.add(value2);

                } else {
                    value1++;
                    map1.put(dst, value1);
                    list2.add(value1);
                }
                dst = value1;
            }
            //stroe src and dst as new edge
            line=br.readLine();//一次读入一行数据
        }

        System.out.println("输入数据有"+total+"行");
    /*    for(int k=0;k<list1.size();k++) {
            System.out.println(k+1+" "+list1.get(k));
        }
       */

        for (int l=0;l<list2.size();l++){
            System.out.println(l+1+" "+list2.get(l));
        }

        Iterator iterator=map1.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry)iterator.next();
            Long key3=(Long)entry.getKey();
            Long value3=(Long)entry.getValue();
            System.out.println(key3+" "+value3);
        }
        //求Map中的最大value
        Collection<Long>c=map1.values();
        Object[] obj=c.toArray();
        Arrays.sort(obj);
        System.out.println("最小node号是："+obj[0]);
        System.out.println("最大node号是："+obj[map1.size()-1]);

        File writename1=new File("//home//wangyufeng//cit-Patentsout4.txt");
        File writename2=new File("//home//wangyufeng//cit-Patentsmap4.txt");
        writename1.createNewFile();//创建新文件
        writename2.createNewFile();//创建新文件
        FileWriter out1=new FileWriter(writename1);
        FileWriter out2=new FileWriter(writename2);
       // out1.write("# Undericted Graph com-friendster.ungraphout1.txt"+"\r\n");
       // out1.write("# transform from com-friendster.ungraph.txt"+"\r\n");
        out1.write("# Nodes:"+obj[map1.size()-1]+" "+"Edges:"+total+"\r\n");
      //  out1.write("# FromNodeId"+" "+"ToNodeId"+"\r\n");
        for(int m=0;m<list2.size()-1;m+=2){
            out1.write(list2.get(m)+" "+list2.get(m+1)+"\r\n");
        }
        Iterator iterator2=map1.entrySet().iterator();
        while(iterator2.hasNext()){
            Map.Entry entry=(Map.Entry)iterator2.next();
            Long key=(Long) entry.getKey();
            Long value=(Long)entry.getValue();
            out2.write(key+"-"+value+"\r\n");
        }
        System.out.println("总共有边数：(list2.size())/2"+list2.size()/2+"\r\n");
        System.out.println("总共有边数：（total）"+total+"\r\n");
        out1.flush();
        out1.close();
        out2.flush();
        out2.close();
        long endTime=System.currentTimeMillis();//获取结束时间
        System.out.println("程序运行结束时间是："+endTime+"ms");
        long time=endTime-startTime;//程序运行时间
        System.out.println("程序运行时间为"+time+"ms");
    }


}

