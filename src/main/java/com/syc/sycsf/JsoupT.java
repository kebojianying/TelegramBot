package com.syc.sycsf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class JsoupT {
    public static final String BASE_URL = "http://taihegas.cn/Pay_Pawxsm_zp.html";

    public static void main(String args[]) {

        try{
            File file = new File("D:\\result\\en\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            FileOutputStream fos = null;
            if(!file.exists()){
                file.createNewFile();//如果文件不存在，就创建该文件
                fos = new FileOutputStream(file);//首次写入获取
            }else{
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file,true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }

            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write("我会写入文件啦\r\n"); // \r\n即为换行
            osw.flush(); // 把缓存区内容压入文件
            osw.close(); // 最后记得关闭文件
        }catch (Exception e){
            e.printStackTrace();
        }


     /*   Map<String, String> data = new HashMap<String, String>();
        data.put("user", "ylc888");
        data.put("amt", "1");
        data.put("type", "1");
//        data.put("ticket","");
//        data.put("ck","");
        Connection.Response login = null;
        try {
            login = Jsoup.connect(BASE_URL)
                    .ignoreContentType(true) // 忽略类型验证
                    .followRedirects(false) // 禁止重定向
                    .postDataCharset("utf-8")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("X-Requested-With", "XMLHttpRequest")
                    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
                    .data(data)
                    .method(Connection.Method.POST)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        login.charset("UTF-8");
        System.out.println(login.body());*/





        //img
//        Connection.Response img=null;
//        try{
//            img = Jsoup.connect(BASE_URL+"/Home/LogVerImage")
//                     .cookies(login.cookies())
//                    .ignoreContentType(true) // 忽略类型验证
//                    .followRedirects(false) // 禁止重定向
//                    .postDataCharset("utf-8")
//                    .header("Upgrade-Insecure-Requests","1")
//                    .header("Accept","application/json")
//                    .header("Content-Type","application/x-www-form-urlencoded")
//                    .header("X-Requested-With","XMLHttpRequest")
//                    .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
//                    //.data(data)
//                    .method(Connection.Method.POST)
//                    .execute();
//            byte[] yzm=img.bodyAsBytes();
//            Path path = Paths.get("D:\\sycsf\\src\\main\\resources\\static/"+System.currentTimeMillis()+".jpg");
//            Files.write(path, yzm);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//       System.out.println(login.cookies().get("ASP.NET_SessionId"));


    }
}