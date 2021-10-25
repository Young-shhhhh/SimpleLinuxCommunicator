import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.*;


class getKey extends Thread{
    private Socket skt;
    public getKey(Socket skt) {this.skt=skt;}
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for(;;) {
                String[] command = br.readLine().split(" ");
                if (command[0].equals("1")) {//发送信息
                    new KeyTcp(skt, command[1]).start();
                    new KeyTcp(skt, command[1]).start();
                    new SaveMessageToFile(0,command[1]);
                }
                else if (command[0].equals("FR")){
                    //TODO:查找记录
                    if(command[1].equals("1"))
                        new SearchRecordDate(command[2]).start();
                    else if(command[1].equals("2"))
                        new SearchRecordCont(command[2]).start();
                }
                else if(command[0].equals("SF")){//发送文件
                    String S=skt.getInetAddress().toString();
                    S=S.substring(1);
                    System.out.println(S);
                    new SendFile(S,command[1]);
                }
                else if(command[0].equals("?")||command[0].equals("？")) {
                    new Launcher().help();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * 信息发送
 */
class KeyTcp extends Thread{
    private String message;
    private Socket skt;
    public KeyTcp(Socket skt,String message) {
        this.skt=skt;
        this.message=message;
    }
    public void run() {
        try {
            //构建输出流，将从键盘接收的信息写入到网络
            PrintWriter pw=new PrintWriter(skt.getOutputStream());
                    pw.println(message);
                    pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 读入信息，并将其打印到控制台
 */
class NetTcp extends Thread{

    private Socket skt;
    public NetTcp(Socket skt) {
        this.skt=skt;
    }

    public void run() {
        try {
            //构建输入流，从网络中读入信息，并将其打印到控制台
            BufferedReader br=new BufferedReader(new InputStreamReader(skt.getInputStream()));
            for (;;) {
                System.out.println("对方信息：" + br.readLine());
                new SaveMessageToFile(1,br.readLine());

            }
        } catch (Exception e) {
            System.out.println("对方下线或者掉线……");
        }
    }
}
