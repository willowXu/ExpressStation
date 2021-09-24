package pers.xuliushen.expressstation_list.view;

import pers.xuliushen.expressstation_list.dao.ExpressDao;
import pers.xuliushen.expressstation_list.exception.OutNumberBoundException;
import pers.xuliushen.expressstation_list.pojo.Express;

import java.util.List;
import java.util.Scanner;

/**
 * View 视图展示
 * 打印信息和接收用户输入
 */

public class Views {

    private Scanner input = new Scanner(System.in);
    private ExpressDao expressDao = new ExpressDao();

    /**
    打印欢迎信息
     */
    public void welcome(){
        System.out.println("----------------------欢迎来到徐柳深快递管理系统--------------------------");
    }

    /**
    打印退出信息
     */
    public void bye(){
        System.out.println("欢迎下次再来哦~");
    }

    /**
    打印主菜单
    @return  选择的身份 1快递员 2普通用户 0退出
     */
    public int startMenu(){
        int option = 0;
        do {
            System.out.println("根据提示，输入功能序号:");
            System.out.println("1.管理员");
            System.out.println("2.普通用户");
            System.out.println("0.退出");
            //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
            String strNum = input.nextLine();
            try {
                option = validateNumber(strNum, 0, 2);
                break;
            } catch (NumberFormatException | OutNumberBoundException e) {
                System.err.println(e.getMessage());
            }
        }while(true);
        if(option == 1){
            administorMenu();
        }else if (option == 2){
            userMenu();
        }
        return option;
    }

    /**
     * 验证用户输入是否为数字以及是否在给定范围内
     * @param strNum 字符串
     * @param begin 范围左边界
     * @param end 范围右边界
     * @return strNum的数字形式
     * @throws NumberFormatException 数字格式错误
     * @throws OutNumberBoundException 数字不在范围内异常
     */
    private int validateNumber(String strNum, int begin, int end) throws NumberFormatException, OutNumberBoundException {
        try{
            int number = Integer.parseInt(strNum);
            if(number<begin || number>end){
                throw new OutNumberBoundException("数字范围必须在"+begin+"和"+end+"间!");
            }
            return number;
        }catch (NumberFormatException e){
            throw new NumberFormatException("输入的必须是数字！");
        }
    }

    /**
    快递员的菜单
     @return 1快递录入 2快递删除 3快递修改 4查看所有快递 0退出
     */
    private void administorMenu(){
        System.out.println("管理员菜单：");
        System.out.println("1.快递录入");
        System.out.println("2.快递删除");
        System.out.println("3.快递修改");
        System.out.println("4.查看所有快递");
        System.out.println("0.返回上一级菜单");
        //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
        String strNum = input.nextLine();
        int option = 0;
        do {
            try {
                option = validateNumber(strNum, 0, 4);
                break;
            } catch (NumberFormatException | OutNumberBoundException e) {
                System.err.println(e.getMessage());
            }
        }while(true);
        if(option == 1){
            System.out.println("快递录入:");
            addExpress();
        }else if(option == 2){
            System.out.println("快递删除:");
            deleteExpress();
        }else if(option == 3){
            System.out.println("快递修改:");
            updateExpress();
        }else if(option == 4){
            System.out.println("查看所有快递:");
            printAllExpresses();
        }
    }

    /**
    快递员录入快递
     */
    public void addExpress(){
        System.out.println("请根据提示，输入快递信息");
        System.out.println("请输入快递的单号:");
        String number = input.nextLine();
        System.out.println("请输入快递的公司:");
        String company = input.nextLine();
        //要返回两个数据——封装(取件码是柜子生成的，不属于Views
        Express express = new Express();
        express.setNumber(number);
        express.setCompany(company);
        try {
           Express result = expressDao.add(express);
            System.out.println("添加成功，放在了快递柜中的第"+result.getX()+"排，第"+result.getY()+"列");
        } catch (Exception e) {
            System.out.println("添加失败！");
            System.err.println(e.getMessage());
        }
        //administorMenu();//返回管理员菜单
    }

    /**
    快递修改流程：Views findByName输入快递单号->调度逻辑Dao查找快递->Views printExpress打印快递信息
    ->View update输入修改后的快递信息->Dao存入快递柜
     */

    /**
     修改快递信息
     */
    public void updateExpress(){
        System.out.println("请根据提示，输入信息:");
        System.out.println("请输入要修改的快递的快递单号:");
        String oldNumber = input.nextLine();
        //修改对象信息
        Express newExpress = new Express();
        System.out.println("请输入新的快递单号:");
        newExpress.setNumber(input.nextLine());
        System.out.println("请输入新的快递公司");
        newExpress.setCompany(input.nextLine());
        try {
            expressDao.update(oldNumber, newExpress);
            System.out.println("修改快递成功！");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        //administorMenu();//返回管理员菜单
    }

    /**
     快递删除流程：Views findByName输入快递单号->调度逻辑Dao查找快递->Views printExpress打印快递信息
     ->View delete询问是否确认删除->Dao从快递柜删除快递
     */

    /**
     * 询问是否确认删除
     * @return 1确认 0取消操作
     */
    public void deleteExpress(){
        int option = 0;
        System.out.println("请输入快递单号：");
        String number = input.nextLine();
        do {
            System.out.println("是否确认删除？");
            System.out.println("1.确认");
            System.out.println("0.取消操作");
            //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
            String strNum = input.nextLine();
            try {
                option = validateNumber(strNum,0,1);
                break;
            } catch (NumberFormatException | OutNumberBoundException e) {//捕获非数字的输入
                System.err.println(e.getMessage());
            }
        }while(true);
        if(option == 1) {//确认删除
            try {
                expressDao.delete(number);
                System.out.println("操作成功！已删除单号为" + number + "的快递");
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
//        else{//取消操作返回管理员菜单
//            administorMenu();
//        }
    }

    /**
     * 显示所有快递信息
     */
    private void printAllExpresses(){
        List<Express> expressList = expressDao.getExpressList();
        for(Express express:expressList){
            System.out.println(express);
        }

    }

    /**
    用户的菜单
     @return 用户输入的取件码
    */
    private void userMenu(){
        withDrawExpress();
    }

    /**
     * 用户取件流程：Views输入取件码->Dao根据取件码查找快递->Views printExpress打印快递信息 ->Dao从快递柜取出快递
     */
    public void withDrawExpress(){
        System.out.println("普通用户菜单：");
        System.out.println("请输入您的取件码：");
        //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
        int  code = 0;
        do {
            System.out.println("请输入您的取件码：");
            //全局都使用nextLine获取用户输入， 不会产生冲突，可以输入一些特殊的字符如空格等
            String strCode = input.nextLine();
            try {
                code = validateNumber(strCode, 100000, 999999);
                break;
            } catch (OutNumberBoundException e) {//捕获非数字的输入
                System.err.println(e.getMessage());
            }
        }while(true);
        try {
            Express express = expressDao.withDraw(code);
            System.out.println("取件成功！");
            System.out.println(express);
        }catch (Exception e){
            System.out.println("取件失败！");
            System.err.println(e.getMessage());
        }
    }

}
