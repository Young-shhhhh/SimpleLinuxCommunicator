public class Launcher {
    public void help(){
        System.out.print("——————————————————————————————————————————————————————————————————————————————\n"+
                         "使用手册：\n"+
                         "1.开始时输入端口作为服务器端，等待客户端连接或者输入（目标IP,端口）格式作为客户端连接服务器端 \n" +
                         "2.连接成功后，可以使用一下功能：\n" +
                         "  (1)直接输入”1 内容”回车发送，即可发送内容信息,例如：“1 你好” \n" +
                         "  (2)输入“FR 1 日期”,即可查找日期当日内所有信息,例如：“FR 1 2021-10-22”\n"+
                         "     输入“FR 2 内容”,即可查找有关内容的所有信息,例如：“FR 2 密码”\n"+
                         "  (3)输入“SF 发送端口 文件路径”,即可发送该文件给对方,\n" +
                         "        例如：“SF 8083 /Users/young/datas/tmp/abc.txt”\n" +
                         "  (4)输入“?”再次查看使用手册\n"
                        );
    }
}