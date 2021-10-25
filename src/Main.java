import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

//约定8080为通信端口，8081为文件传输端口

public class Main {
    public static void main(String[] args) throws Exception {
        new Launcher().help();
        System.out.println("请输入IP和端口号，或直接输入端口号：");
        System.out.println("例如：192.168.0.2,8080或者8080");
        //构建输入流接收键盘的输入,从输入的信息判断是服务器端还是客户端
        String[] ss=new BufferedReader(new InputStreamReader(System.in)).readLine().split(",");
        Socket skt=null;
        if (ss.length==1) {
            //服务器端
            skt=new ServerSocket(Integer.parseInt(ss[0])).accept();
        }else {
            //客户端
            skt=new Socket(ss[0],Integer.parseInt(ss[1]));
        }
        new ReadFile();
        new getKey(skt).start();
        new NetTcp(skt).start();
        new ReceiveFile();
    }
}