import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//约定8080为通信端口

public class Main {
    public static int revPort = 0;
    public static void main(String[] args) throws Exception {
        new Launcher().help();
        new Conf().confRead();
        System.out.println("请输入IP和端口号，或直接输入端口号：");
        System.out.println("例如：192.168.0.2,8080或者8080");
        //构建输入流接收键盘的输入,从输入的信息判断是服务器端还是客户端
        String[] ss=new BufferedReader(new InputStreamReader(System.in)).readLine().split(",");
        Socket skt=null;
        if (ss.length==1) {
            //服务器端
            skt=new ServerSocket(Integer.parseInt(ss[0])).accept();
            new KeyTcp(skt,"文件接收端口为："+revPort).start();
        }else {
            //客户端
            skt=new Socket(ss[0],Integer.parseInt(ss[1]));
            new KeyTcp(skt,"文件接收端口为："+revPort).start();
        }
        new ReadFile();
        new getKey(skt).start();
        new NetTcp(skt).start();
        new ReceiveFile();
    }

    public static class Conf{
        public void confRead(){
            try {
                String path = "Coummunicator_Data/conf.ini";
                File file = new File(path);
                if(!file.exists()){
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
                    StringBuffer stringBuffer=new StringBuffer();
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    stringBuffer.append("接收端口:8081");
                    bufferedWriter.write(stringBuffer.toString());
                    bufferedWriter.close();
                }
                FileInputStream fileInputStream = null;
                fileInputStream = new FileInputStream(path);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = null;
                while ((line = bufferedReader.readLine())!= null) {
                    String[] s = line.split(":");
                    if(s[0].equals("接收端口"))
                    {
                        revPort = Integer.parseInt(s[1]);
                    }
                }
                fileInputStream.close();

            }catch (
                    FileNotFoundException e) {
                e.printStackTrace();
            }catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

}