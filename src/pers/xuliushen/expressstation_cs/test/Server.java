package pers.xuliushen.expressstation_cs.test;

import pers.xuliushen.expressstation_cs.dao.ExpressDao;
import pers.xuliushen.expressstation_cs.view.Views;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Views v = new Views();
    private static ExpressDao dao = new ExpressDao();

    public static void main(String[] args){
        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("服务器开始运行");
            //等待用户的连接请求
            while(true) {
                Socket socket = server.accept();
                System.out.println("接收到来自用户的连接：" + socket.getRemoteSocketAddress());
                //为每一个用户连接分配一个子线程进行处理
                new Thread(()->{
                    try {
                        OutputStream os = socket.getOutputStream();
                        PrintStream ps = new PrintStream(os);
                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader bf =new BufferedReader(isr);
                        //1.欢迎
                        v.welcome(ps);
                        //2.弹出菜单
                        int menu = 0;
                        do{
                            menu = v.startMenu(bf,ps);
                        }while(menu!=0);
                        //3.再见
                        v.bye(ps,socket.getRemoteSocketAddress().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
