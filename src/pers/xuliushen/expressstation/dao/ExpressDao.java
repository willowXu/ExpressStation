package pers.xuliushen.expressstation.dao;


import pers.xuliushen.expressstation.bean.Express;

import java.util.*;

/**
 * 数据操作层Dao
 */
public class ExpressDao {
//    private Express[][] data = new Express[10][];//快递位置是数组下标
    private Map<String,Express> data = new HashMap<>();
    private boolean[][] position = new boolean[10][];//快递柜存储情况
    private int size;//当前存储的快递数
    {//构造快开辟二维空间
//        for(int i=0;i<10;i++){
//            data[i] = new Express[10];
//        }
        for(int i=0;i<10;i++){
            position[i] = new boolean[10];
        }
        //初始化柜台
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                position[i][j] = false;
        size = 0;
    }
    private Random random = new Random();

    /**
     * 存储快递
     * @param e 要被存入的快递
     * @return 存储结果 true成功 false失败
     */
    public boolean add(Express e){
        if(size == 100)//快递柜满了
            return false;
        //1.位置的问题,随机生成2个0-9的下标
        int x = -1;
        int y = -1;
        while(true){
            x = random.nextInt(10);
            y = random.nextInt(10);
            if(position[x][y] == false){
                //此位置无快递
                break;
            }
        }
        //2.取件码的问题，重复？
        int code = randomCode();
        e.setCode(code);
        //3.存入快递
        position[x][y] = true;
        e.setPosition(x,y);
        data.put(e.getNumber(),e);
        return true;
    }

    /**
     * 模板方法设计模式
     * @return 随机生成的取件码
     */
    private int randomCode(){
        while (true) {//一直循环直到生成可用的取件码
            int code = random.nextInt(899999) + 100000;//100000~999999
            Express e = findByCode(code);
            if (e == null) {//取件码可用
                return code;
            }
        }
    }

    /**
     * 通过取件码查询快递
     * @param code 取件码
     * @return 查找到的快递
     */
    public Express findByCode(int code){
//        for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                if(data[i][j] != null && data[i][j].getCode() == code)
//                    return data[i][j];
//            }
//        }
        for(Express e:data.values()){
            if(code == e.getCode()){
                return e;
            }
        }
        return null;
    }

    /**
     * 快递员根据快递单号查询
     * @param number 快递单号
     * @return 查询到的快递
     */
    public Express findByNumber(String number){
//        Express e= new Express();
//        e.setNumber(number);
//        for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                if(data[i][j] != null && e.equals(data[i][j]))//data[i][j].equals(e) 不同因为data[i][j]可能为空，e不会为空
//                    return data[i][j];
//            }
//        }
        if(number != null){
            Express e = data.get(number);
            if(e != null)
                return e;
        }
        return null;
    }


    /**多余的操作，为了MVC更圆润
     *修改快递，将修改后的快递重新存入快递柜
     * @param oldExpress 原对象
     * @param newExpress 新对象
     */
    public void update(Express oldExpress, Express newExpress){
        delete(oldExpress);
        add(newExpress);
    }

    /**
     *删除快递
     * @param e 要删除的快递
     */
    public void delete(Express e){
//        p:for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                if(e.equals(data[i][j])) {
//                    data[i][j] = null;//删除快递
//                    break p;//跳出循环
//                }
//            }
//        }
        if(e != null){
            data.remove(e.getNumber());
            position[e.getX()][e.getY()] = false;
        }
    }

    /**
     * 返回所有快递
     */
    public Express[][] findAll(){
//        return data;
        Express[][] es = new Express[10][];//快递位置是数组下标
        for(int i=0;i<10;i++){
            es[i] = new Express[10];
        }
        for(Express e:data.values()){
            es[e.getX()][e.getY()] = e;
        }
        return es;
    }

}
