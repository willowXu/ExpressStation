package pers.xuliushen.expresssation_io.test;


import pers.xuliushen.expresssation_io.dao.ExpressDao;
import pers.xuliushen.expresssation_io.view.Views;

/**
 * 逻辑层Main
 * 这里其实可以将uClient和cClient封装成两个类，这样比较合理
 */
public class Main {
    private static Views v = new Views();
    private static ExpressDao dao = new ExpressDao();

    public static void main(String[] args ){
        //1.欢迎
        v.welcome();
        //2.弹出菜单
        int menu = 0;
        do{
            menu = v.startMenu();
        }while(menu!=0);
        //3.再见
        v.bye();
    }

}
