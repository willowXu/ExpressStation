package pers.xuliushen.expressstation_exception.dao;


import pers.xuliushen.expressstation_exception.pojo.Coordinate;
import pers.xuliushen.expressstation_exception.pojo.Express;

import java.util.Random;

/**
 * 数据操作层Dao
 */
public class ExpressDao {
    private Express[][] data = new Express[10][10];//快递位置是数组下标
    private int size;//当前存储的快递数
    {
        size = 0;
    }
    private Random random = new Random();

    /**
     * 存储快递
     * @param e 要被存入的快递
     * @return 存储位置
     * @throws Exception 快递柜已满异常
     */
    public Coordinate add(Express e) throws Exception {
        if(size == 100)//快递柜满了
            throw new Exception("快递柜已满!");
        //1.位置的问题,随机生成2个0-9的下标
        int x = -1;
        int y = -1;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
        } while (data[x][y] != null); //此位置有快递
        //2.取件码的问题，重复？
        int code = randomCode();
        e.setCode(code);
        //3.存入快递
        size++;
        data[x][y] = e;
        return new Coordinate(x,y);
    }

    /**
     * 模板方法设计模式
     * @return 随机生成的取件码
     */
    private int randomCode(){
        while (true) {//一直循环直到生成可用的取件码
            int code = random.nextInt(899999) + 100000;//100000~999999
            Coordinate position = findByCode(code);
            if (position == null) {//取件码可用
                return code;
            }
        }
    }

    /**
     *修改快递，将修改后的快递重新存入快递柜
     * @param number 快递单号
     * @param newExpress 新对象
     * @return 操作结果
     */
    public boolean update(String number, Express newExpress) throws Exception {
        Coordinate position = findByNumber(number);
        if(position == null){
            throw new Exception("快递不存在！");
        }
        Express oldExpress = data[position.getX()][position.getY()];
        oldExpress.setCompany(newExpress.getCompany());
        oldExpress.setNumber(newExpress.getNumber());
        return true;
    }

    /**
     *根据快递单号删除快递
     * @param number 要删除的快递单号
     */
    public boolean delete(String number) throws Exception {
        Coordinate position = findByNumber(number);
        if(position == null){
            throw new Exception("快递不存在!");
        }
        data[position.getX()][position.getY()] = null;
        size--;
        return true;
    }

    /**
     * 快递员根据快递单号查询
     * @param number 快递单号
     * @return 查询到的快递位置
     */
    public Coordinate findByNumber(String number){
        Express e= new Express();
        e.setNumber(number);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(data[i][j] != null && e.equals(data[i][j]))//data[i][j].equals(e) 不同因为data[i][j]可能为空，e不会为空
                    return new Coordinate(i,j);
            }
        }
        return null;
    }

    /**
     * 返回所有快递
     */
    public Express[][] findAll(){
        return data;
    }

    /**
     * 根据取件码取件
     * @param code 取件码
     * @return 操作结果
     * @throws Exception 快递不存在
     */
    public boolean withDraw(int code) throws Exception {
        Coordinate position = findByCode(code);
        if(position == null){
            throw new Exception("快递不存在！");
        }
        data[position.getX()][position.getY()] = null;
        size--;
        return true;
    }

    /**
     * 通过取件码查询快递
     * @param code 取件码
     * @return 查找到的快递
     */
    public Coordinate findByCode(int code){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(data[i][j] != null && data[i][j].getCode() == code)
                    return new Coordinate(i,j);
            }
        }
        return null;
    }

}
