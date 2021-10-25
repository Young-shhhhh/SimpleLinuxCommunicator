import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 本地读文件
 */
class ReadFile{
    public ReadFile()
    {
        try {
            //linux系统
            //String path = "/Users/shaonaiyi/datas/tmp/hello.txt";
            //win系统
            String path = "E:\\桌面\\聊天记录.txt";
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = null;
            int num=1;
            System.out.println("连接成功！前50条聊天记录为：");
            while (num<=50 && (line = bufferedReader.readLine())!= null) {
                System.out.println(line);
                num++;
            }
            fileInputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class SaveMessageToFile{
    public SaveMessageToFile(int mode,String message) throws Exception {
        //linux系统
        //String path="/Users/shaonaiyi/datas/tmp/hello.txt";
        //win系统
        String path="E:\\桌面\\聊天记录.txt";
        Date date= new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
        StringBuffer stringBuffer=new StringBuffer();
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        if(mode==0){//自己的信息
            stringBuffer.append(ft.format(date)+"\t"+"我方信息："+ message+ "\r\n");
            bufferedWriter.write(stringBuffer.toString());
            bufferedWriter.close();
        }
        if(mode==1){//对方的信息
            stringBuffer.append(ft.format(date) +"\t"+"对方信息："+message+ "\r\n");
            bufferedWriter.write(stringBuffer.toString());
            bufferedWriter.close();
        }
    }
}


class SearchRecordDate extends Thread{
    private String date;
    public SearchRecordDate(String date){
        this.date=date;
    }
    public void run(){
        try {
        //linux系统
        //String path = "/Users/shaonaiyi/datas/tmp/hello.txt";
        //win系统
        String path = "E:\\桌面\\聊天记录.txt";
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = null;
        while ((line = bufferedReader.readLine())!= null) {
            String[] s=line.split(" ");
            if(s[0].equals(date))
                System.out.println(line);
        }
        fileInputStream.close();
    }catch (FileNotFoundException e) {
        e.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
    }

    }

}


class SearchRecordCont extends Thread{
    private String content;
    public SearchRecordCont(String content){
        this.content=content;
    }
    public void run(){
        try {
            //linux系统
            //String path = "/Users/shaonaiyi/datas/tmp/hello.txt";
            //win系统
            String path = "E:\\桌面\\聊天记录.txt";
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = null;
            while ((line = bufferedReader.readLine())!= null) {
                String[] s=line.split(" ");
                if(s[1].indexOf(content)!=-1)
                    System.out.println(line);
            }
            fileInputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}