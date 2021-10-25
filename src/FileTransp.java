import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

class SendFile {
    final String fileUrl;
    final String Url;
    public SendFile(String Url,String fileUrl){
        this.fileUrl = fileUrl ;
        this.Url = Url ;
        int length=0;
        byte[] sendByte = null;
        Socket socket = null;
        DataOutputStream dataOut =null;
        FileInputStream fileIn =null;
        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress(Url,8082),10*1000);
            dataOut =new DataOutputStream(socket.getOutputStream());
            File file = new File(fileUrl);
            fileIn = new FileInputStream(file);
            sendByte = new byte[1024];
            dataOut.writeUTF(file.getName());
            while((length = fileIn.read(sendByte,0,sendByte.length))>0){
                dataOut.write(sendByte,0,length);
                dataOut.flush();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(dataOut != null) {
                try {
                    dataOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileIn!=null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket !=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }

}
}

class ReceiveFile implements Runnable{

    @Override
    public void run() {

    }

    public ReceiveFile(){
        try{
            final ServerSocket server = new ServerSocket(8081);
            Thread th =new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            System.out.println("开始监听。。。");
                            Socket socket = server.accept();
                            System.out.println("有链接");
                            receiveFile(socket);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            th.run();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void receiveFile(Socket socket) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream din = null;
        FileOutputStream fout = null;
        try {
            din = new DataInputStream(socket.getInputStream());

            fout = new FileOutputStream(new File("E:\\"+din.readUTF()));
            inputByte = new byte[1024];
            System.out.println("开始接收数据...");
            while (true) {
                if (din != null) {
                    length = din.read(inputByte, 0, inputByte.length);
                }
                if (length == -1) {
                    break;
                }
                fout.write(inputByte, 0, length);
                fout.flush();
            }
            System.out.println("完成接收");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null)
                fout.close();
            if (din != null)
                din.close();
            if (socket != null)
                socket.close();
        }
    }

}