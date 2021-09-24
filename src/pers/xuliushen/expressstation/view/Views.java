package pers.xuliushen.expressstation.view;

import pers.xuliushen.expressstation.bean.Express;

import java.util.Scanner;

/**
 * View 视图展示
 * 打印信息和接收用户输入
 */

public class Views {

    Scanner input = new Scanner(System.in);

    /**
    打印欢迎信息
     */
    public void welcome(){
        System.out.println("欢迎使用哈哈哈的快递管理系统");
    }

    /**
    打印退出信息
     */
    public void bye(){
        System.out.println("欢迎下次再来哦~");
    }

    /**
    打印菜单
    @return  选择的身份 1快递员 2普通用户 0退出
     */
    public int menu(){
        System.out.println("根据提示，输入功能序号:");
        System.out.println("1.管理员");
        System.out.println("2.普通用户");
        System.out.println("0.退出");
        //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
        String text = input.nextLine();
        int option = -1;
        try {
            option = Integer.parseInt(text);
        }catch (NumberFormatException e){//捕获非数字的输入
            //do nothing
        }
        if(option<0 || option>2){//选择不在规定范围内，再次选择
            System.out.println("输入有误，请重新输入");
            return menu();
        }
        return option;
    }

    /**
    快递员的菜单
     @return 1快递录入 2快递删除 3快递修改 4查看所有快递 0退出
     */
    public int cMenu(){
        System.out.println("根据提示，输入功能序号:");
        System.out.println("1.快递录入");
        System.out.println("2.快递删除");
        System.out.println("3.快递修改");
        System.out.println("4.查看所有快递");
        System.out.println("0.退出");
        //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
        String text = input.nextLine();
        int option = -1;
        try {
            option = Integer.parseInt(text);
        }catch (NumberFormatException e){//捕获非数字的输入
            //do nothing
        }
        if(option<0 || option>4){//选择不在规定范围内，再次选择
            System.out.println("输入有误，请重新输入");
            return cMenu();
        }
        return option;
    }

    /**
    快递员录入快递
     @return 录入的快递（非完整）
     */
    public Express insert(){
        System.out.println("请根据提示，输入快递信息");
        System.out.println("请输入快递的单号:");
        String number = input.nextLine();
        System.out.println("请输入快递的公司:");
        String company = input.nextLine();
        //要返回两个数据——封装(取件码是柜子生成的，不属于Views
        Express express = new Express();
        express.setNumber(number);
        express.setCompany(company);
        return express;
    }

    /**
    快递修改流程：Views findByName输入快递单号->调度逻辑Dao查找快递->Views printExpress打印快递信息
    ->View update输入修改后的快递信息->Dao存入快递柜
     */

    /**
    输入快递单号
     @return 快递单号
     */
    public String findByNumber(){
        System.out.println("请根据提示，输入快递信息");
        System.out.println("请输入要操作的快递单号:");
        String number = input.nextLine();
        return number;
    }

    /**
    显示快递信息
     @param e 要打印信息的快递
     */
    public void printExpress(Express e){
        System.out.println("快递信息如下：");
        System.out.println("快递公司："+e.getCompany()+",快递单号："+e.getNumber()+",取件码："+e.getCode());
    }

    /**
     * 打印空的快递信息
     */
    public void printNull(){
        System.out.println("快递不存在，请检查您的输入");
    }

    /**
     @param e 要修改的快递
     修改快递信息，传来的是引用无需返回
     */
    public void  update(Express e){
        System.out.println("请根据提示，输入新的快递信息:");
        System.out.println("请输入新的快递单号:");
        String number = input.nextLine();
        System.out.println("请输入新的快递公司");
        String company = input.nextLine();
        //修改对象信息
        e.setNumber(number);
        e.setCompany(company);
    }

    /**
     快递删除流程：Views findByName输入快递单号->调度逻辑Dao查找快递->Views printExpress打印快递信息
     ->View delete询问是否确认删除->Dao从快递柜删除快递
     */

    /**
     * 询问是否确认删除
     * @return 1确认 2取消操作 0退出
     */
    public int delete(){
        System.out.println("是否确认删除？");
        System.out.println("1.确认");
        System.out.println("2.取消操作");
        System.out.println("0.退出");
        //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
        String text = input.nextLine();
        int option = -1;
        try {
            option = Integer.parseInt(text);
        }catch (NumberFormatException e){//捕获非数字的输入
            //do nothing
        }
        if(option<0 || option>4){//选择不在规定范围内，再次选择
            System.out.println("输入有误，请重新输入");
            return cMenu();
        }
        return option;
    }

    /**
     * 将给定数组中的快递信息遍历显示
     * @param es 所有快递
     */
    public void printAll(Express[][] es){
        int count = 0;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++) {
                if(es[i][j] != null) {
                    System.out.print("第"+(i+1)+"排 "+(j+1)+"列快递:");
                    printExpress(es[i][j]);
                    count++;
                }
            }
        }
        if(count == 0){
            System.out.println("暂无快递信息");
        }
    }

    /**
   用户的菜单
     @return 用户输入的取件码
    */
    public int uMenu(){
        System.out.println("根据提示，进行取件:");
        System.out.println("请输入您的取件码");
        //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
        String code = input.nextLine();
        int  num = -1;
        try {
            num = Integer.parseInt(code);
        }catch (NumberFormatException e){//捕获非数字的输入
            //do nothing
        }
        if(num<100000 || num>999999){//选择不在规定范围内，再次选择
            System.out.println("输入有误，请重新输入");
            return uMenu();
        }
        return num;
    }

    /**
     * 用户取件流程：Views输入取件码->Dao根据取件码查找快递->Views printExpress打印快递信息 ->Dao从快递柜取出快递
     */


    /**
     * 快递单号已存在
     */
    public void expressExist(){
        System.out.println("此单号在快递柜中已存在，请勿重复存储");
    }

//    /**
//     * 显示取件码
//     * @param e 要显示取件码的快递
//     */
//    public void printCode(Express e){
//        System.out.println("快递的取件码为"+e.getCode());
//    }

    /**
     * 提示操作成功
     */
    public void success(){
        System.out.println("操作成功");
    }

}
