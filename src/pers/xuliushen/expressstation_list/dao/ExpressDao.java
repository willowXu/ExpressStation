package pers.xuliushen.expressstation_list.dao;

import pers.xuliushen.expressstation_list.pojo.Express;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpressDao {
    //集合模拟快递柜子
    private List<Express> expressList = new ArrayList<>(100);

    public ExpressDao(){
        init();
    }

    private void init(){
        expressList.add(new Express("YT001","圆通",123456,0,0));
        expressList.add(new Express("YD001","韵达",123457,0,1));
    }

    public List<Express> getExpressList() {
        return expressList;
    }

    /**
     * 录入快递
     * @param e 要被存入的快递
     * @return 存储位置
     * @throws Exception 快递柜已满异常
     */
    public Express add(Express e) throws Exception {
        if(expressList.size() == 100)//快递柜满了
            throw new Exception("快递柜已满!");
        //1.位置的问题,随机生成2个0-9的下标
        int x = -1;
        int y = -1;
        Random random = new Random();
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
        } while (isExist(x,y)); //此位置有快递
        //2.取件码的问题，重复？
        int code = randomCode();
        e.setCode(code);
        e.setX(x);
        e.setY(y);
        expressList.add(e);
        return e;
    }

    /**
     * 查询指定坐标处是否有快递
     * @param x
     * @param y
     * @return
     */
    private boolean isExist(int x,int y){
        for(Express express:expressList){
            if(express.getX()==x && express.getY()==y){
                return true;
            }
        }
        return false;
    }

    /**
     * 模板方法设计模式
     * @return 随机生成的取件码
     */
    private int randomCode(){
        Random random = new Random();
        while (true) {//一直循环直到生成可用的取件码
            int code = random.nextInt(899999) + 100000;//100000~999999
            int position = findExpressByCode(code);
            if (position==-1) {//取件码可用
                return code;
            }
        }
    }

    /**
     * 通过取件码查询快递
     * @param code 取件码
     * @return 查找到的快递
     */
    private int findExpressByCode(int code){
      for(int i=0;i<expressList.size();i++){
          if(expressList.get(i).getCode()==code){
              return i;
          }
      }
      return -1;
    }

    /**
     *根据快递单号删除快递
     * @param number 要删除的快递单号
     * @throws
     */
    public boolean delete(String number) throws Exception {
        int position = findExpressByNumber(number);
        if(position==-1){
            throw new Exception("快递不存在!");
        }
        expressList.remove(position);
        return true;
    }

    /**
     * 快递员根据快递单号查询
     * @param number 快递单号
     * @return 查询到的快递索引
     */
    private int findExpressByNumber(String number){
       for(int i=0;i<expressList.size();i++){
           if(expressList.get(i).getNumber().equals(number)){
               return i;
           }
       }
       return -1;
    }

    /**
     *修改快递，将修改后的快递重新存入快递柜
     * @param number 快递单号
     * @param newExpress 新对象
     * @throws  Exception
     * @return 操作结果
     */
    public boolean update(String number, Express newExpress) throws Exception {
        int position = findExpressByNumber(number);
        if(position==-1){
            throw new Exception("快递不存在！");
        }
        Express express = expressList.get(position);
        express.setCompany(newExpress.getCompany());
        express.setNumber(newExpress.getNumber());
        return true;
    }

    /**
     * 根据取件码取件
     * @param code 取件码
     * @return 操作结果
     * @throws Exception 快递不存在
     */
    public Express withDraw(int code) throws Exception {
        int position = findExpressByCode(code);
        if(position==-1){
            throw new Exception("快递不存在！");
        }
        Express express = expressList.get(position);
        Express express1 = new Express();
        express1.setNumber(express.getNumber());
        express1.setCode(express.getCode());
        express1.setCompany(express.getCompany());
        express1.setX(express.getX());
        express1.setY(express.getY());
        expressList.remove(position);
        return express1;
    }



}
