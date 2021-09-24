package pers.xuliushen.expressstation.main;


import pers.xuliushen.expressstation.bean.Express;
import pers.xuliushen.expressstation.dao.ExpressDao;
import pers.xuliushen.expressstation.view.Views;

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
        m:while(true) {
            int menu = v.menu();
            switch (menu) {
                case 0:
                    break m;
                case 1:
                    cClient();
                    break;
                case 2:
                    uClient();
                    break;

            }
        }
        //3.再见
        v.bye();
    }

    /**
     * 快递员的菜单
     */
    private static void cClient() {
        while(true) {
            int menu = v.cMenu();
            switch (menu) {
                case 0:
                    return;
                case 1: {//快递录入(加大括号避免变量名重复）
                    //1.提示输入快递信息
                    Express e = v.insert();//接收到用户输入的快递对象
                    //2. 判断此快递是否已经存储过
                    Express e2 = dao.findByNumber(e.getNumber());
                    //3. 存储快递
                    if(e2 == null) {
                        dao.add(e);//存储快递
                        v.printExpress(e); //显示快递信息
                    }else{//单号在快递柜中已存在
                        //这里发现是视图层缺少一个提示方法，加进去
                        v.expressExist();
                    }
                    break;
                }
                case 2: {//快递删除
                    //1.输入快递单号
                    String number = v.findByNumber();
                    //2.查找快递对象
                    Express e = dao.findByNumber(number);
                    //3.删除快递
                    if(e == null){//快递单号不存在
                        v.printNull();
                    }else{
                        v.printExpress(e);//打印快递对象
                        int option = v.delete();//询问用户是否确认删除
                        if(option == 1){
                            dao.delete(e);//删除数据
                            //这里视图层缺少一个提示成功的方法
                            v.success();//提示操作成功
                        }

                    }
                    break;
                }
                case 3: {//快递修改
                    //1.获取用户输入的单号
                    String number = v.findByNumber();
                    //2. 查找数据
                    Express e = dao.findByNumber(number);
                    Express e2 = e;
                    //3.打印快递数据
                    if(e == null){//没有查找到快递信息
                        v.printNull();
                    }else {//查找到了快递信息
                        v.printExpress(e);//(这里视图层缺少一个打印空的快递的方法）
                        v.update(e2);////提示新的输入，这里面对引用进行了修改
                        dao.update(e,e2);//圆润逻辑
                        //5.显示快递信息
                        v.printExpress(e);
                    }
                    break;
                }
                case 4: {//查看所有数据
                    //1.查找到所有数据
                    Express [][]es = dao.findAll();
                    //2.显示所有快递
                    v.printAll(es);
                    break;
                }
            }
        }
    }

    /**
     * 用户的菜单
     */
    private static void uClient() {
        //1.获得取件码
        int code = v.uMenu();
        //2.根据取件码取出快递
        Express e = dao.findByCode(code);
        if(e == null){//没有找到该取件码
            v.printNull();
        }else{
            v.printExpress(e);//打印快递信息
            dao.delete(e);//从快递柜中删除快递
            v.success();//提示操作成功
        }

    }

}
