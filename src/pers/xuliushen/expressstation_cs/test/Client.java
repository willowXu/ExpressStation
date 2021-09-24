package pers.xuliushen.expressstation_cs.test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost",8888);
            //输入流
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);
            //输出流
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            String text = null;
            do{
               text = bf.readLine();
            }while(reply(text,ps));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param ps 到服务器的输出流
     * @param text 服务器发来的消息
     * @return 是否断开连接
     */
    public static boolean reply(String text, PrintStream ps){
        if("exit".equals(text)){//客户退出服务器
            return false;
        }else if("#".equals(text)) {//服务器输出结束信息，希望得到用户回复
            Scanner input = new Scanner(System.in);
            String reply = input.nextLine();
            ps.println(reply);
        }else{//下一句还是服务器发送消息
            System.out.println(text);
        }
        return true;
    }

}
