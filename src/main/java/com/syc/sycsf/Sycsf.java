//package com.syc.sycsf;
//
//import cn.hutool.http.Header;
//import cn.hutool.http.HttpRequest;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//
//@EnableAsync
//@SpringBootApplication
//@EnableCaching
//@EnableScheduling
//@RestController
//public class SycsfApplication {
//    //测试对象账号：zhongtianys  nimaasyc1234
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    public static void main(String[] args) {
//        SpringApplication.run(SycsfApplication.class, args);
//    }
//
//    @GetMapping("/Auto/suying/login.php")
//    public String getSflogin() {
//        return  getYzm();
//    }
//    @PostMapping("/login")
//    public String postSflogin(@RequestParam String yzm
//    ) {
//        return submit(yzm);
//    }
//    @GetMapping("/Pay_Pawxsm_zp.html")
//    public void autosf(@RequestParam(value = "user") String user,
//                       @RequestParam(value = "amt") String amt,
//                       @RequestParam(value = "type") String type,
//                       @RequestParam(value = "cookie") String cookie) {
//        logger.info("大佬:\t"+user+";\t来啦\t"+amt);
//        logger.info(sfSubmit(user,amt,type,cookie));
//    }
//    public ChromeDriver driver;
//    /*
//    获取验证码
//     */
//    public String getYzm(){
//        System.setProperty("webdriver.chrome.driver","D:/shangfen/webdriver/chromedriver.exe");
//        ChromeOptions options=new ChromeOptions();
////		options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
////		options.addArguments("--headless");
//        //options.setExperimentalOption("useAutomationExtension", false);
//        options.addArguments("start-maximized"); // open Browser in maximized mode
//        options.addArguments("disable-infobars"); // disabling infobars
//        options.addArguments("--disable-gpu"); // applicable to windows os only
//        options.addArguments("--disable-dev-shm-usage");
//        driver = new ChromeDriver(options);
//        driver.get("http://manage.cs66cai.com");
//        driver.findElement(By.xpath("//input[@class='loginuser']")).sendKeys("zdrk888");
//        driver.findElement(By.xpath("//input[@class='loginpwd']")).sendKeys("aaa123");
//        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        long imageName =System.currentTimeMillis();
//
//        try {
//            String path = ResourceUtils.getURL("classpath:static").getPath();
//            ImgCuter.cutPNG(new FileInputStream(srcFile),
//                    new FileOutputStream(path+"/"+imageName+".png"), 1000,500,100,40);
//			/*ImgCuter.cutPNG(new FileInputStream(srcFile),
//					new FileOutputStream("D:/shangfen/sf/static/"+imageName+".png"), 450,400,100,40);*/
//            // FileUtils.copyFile(srcFile,new File("D:/shangfen/sf/static/"+imageName+".png"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "<html>\n" +
//                "<head>\n" +
//                "</head>\n" +
//                "\n" +
//                "<body>\n" +
//                "\n" +
//                "        <form action=\"/login\" method=\"post\">\n" +
//
//                "                输入下图验证码：<input type=\"text\" name=\"yzm\" value=\"\"><br>\n" +
//                "                <input type=\"submit\" value=\"提交\">\n" +
//                "        </form>\n" +
//                "                <img src=\"/static/"+imageName+".png\" /><br>\n" +
//                "\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>";
//
//    }
//    public String sfSubmit(String user,String amt,String type,String cookie){
//
//        return HttpRequest.post("http://manage.cs66cai.com/Home/A_ManualDeal")
//                .header(Header.ACCEPT,"application/json, text/javascript, */*; q=0.01")
//                .header(Header.ACCEPT_ENCODING,"gzip, deflate")
//                .header(Header.ACCEPT_LANGUAGE,"zh-CN,zh;q=0.8")
//                .header(Header.CONNECTION,"keep-alive")
//                .header(Header.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8")
//                //.header(Header.CONTENT_LENGTH,"")
//                .header(Header.ORIGIN,"http://manage.cs66cai.com")
//                .header(Header.REFERER,"http://manage.cs66cai.com/Home/A_ManualDeal")
//                .header(Header.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36")
//                .header(Header.COOKIE,"ASP.NET_SessionId=tfechaudnhadldykdhdic52k2")
//                .body("{\"user_name\":\""+user+"\",\"money\":\""+amt+"\",\"commt\":\"转盘抽奖\",\"manual_type\":3,\"consume_money\":0}")
//                .execute().body();
//    }
//    public String submit(String yzm){
//        driver.findElement(By.xpath("//input[@class='loginyzm']")).sendKeys(yzm);
//        driver.findElement(By.xpath("//button[@class='loginbtn']")).click();
//        sleep(1000);
//        logger.info("cookie---->"+driver.manage().getCookieNamed("ASP.NET_SessionId").getName()+"="+driver.manage().getCookieNamed("ASP.NET_SessionId").getValue());
//        return "验证成功！";
//    }
//    @Scheduled(cron="0 0/5 12-23 * * ? ")
//    public void logTime(){
//        if(driver!=null) {
//            String nowUrl = driver.getCurrentUrl();
//            System.out.println("当前链接为：" + nowUrl);
//            logger.info("cookie---->" + driver.manage().getCookieNamed("ASP.NET_SessionId").getName() + "=" + driver.manage().getCookieNamed("ASP.NET_SessionId").getValue());
//            if (nowUrl.equals("http://manage.cs66cai.com/")) {
//
//            } else {
//                driver.navigate().refresh();
//
//            }
//        }
//        logger.info("reflash："+System.currentTimeMillis());
//    }
//    public void sleep(long second){
//        try{
//            Thread.sleep(second);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
