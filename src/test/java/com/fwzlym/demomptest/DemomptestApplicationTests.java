package com.fwzlym.demomptest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fwzlym.demomptest.entity.User;
import com.fwzlym.demomptest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class DemomptestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testComplex() {
        //1.定义wrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //ge gt le lt   ge>= gt> le<= lt<
//        QueryWrapper<User> userQueryWrapper = queryWrapper.gt("age", 19);//年龄大于20
//        QueryWrapper<User> userQueryWrapper = queryWrapper.ne("name", "张三");//年龄大于20
//        QueryWrapper<User> userQueryWrapper = queryWrapper.between("age", 24,28);//年龄大于20
//        QueryWrapper<User> userQueryWrapper = queryWrapper.like("name", "泽");//模糊查询带泽的人
        QueryWrapper<User> userQueryWrapper = queryWrapper.orderByDesc("age");//模糊查询带泽的人
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);
    }

    @Test//批量id删除
    public void testDelete3() {
        Map<String, Object> map = new HashMap<>();
        //删除id 333 name zzz的那个人,也可以删除多个人，看条件了
        map.put("id", 333L);
        map.put("name", "zzz");
        int res = userMapper.deleteByMap(map);
        System.out.println(res);
    }

    @Test//批量id删除
    public void testDelete2() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        int res = userMapper.deleteBatchIds(list);
        System.out.println(res);//删除行数
    }

    @Test//根据id 删除
    public void testDelete() {
        int res = userMapper.deleteById(5L);
        System.out.println(res);
    }

    @Test
    public void testPage() {
        //current:1 当前页     size:3 每页显示
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        //返回对象得到分页总数据
        long pages = userPage.getPages();//总页数
        long current = userPage.getCurrent();//当前页
        List<User> records = userPage.getRecords();//查询数据集合
        long total = userPage.getTotal();//总记录数
        boolean hasNext = userPage.hasNext();//有无下一页
        boolean hasPrevious = userPage.hasPrevious();//当前页有无前一页
        System.out.println("当前总共页数："+pages);
        System.out.println("当前页："+current);
        System.out.println("查询数据集合："+records);
        System.out.println("总记录数："+total);
        System.out.println("有无下一页："+hasNext);
        System.out.println("有无上一页："+hasPrevious);
    }

    @Test//自己设置条件查询
    public void testSimple() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "泽丽");
        map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    @Test//多个id批量查询
    public void testBatchSelect() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<User> users = userMapper.selectBatchIds(list);
        System.out.println(users);
    }

    @Test//测试乐观锁
    public void testOptimisticLock() {
        //查询
        User user = userMapper.selectById(1L);
        //修改
        user.setName("泽丽");
        userMapper.updateById(user);
    }

    @Test//修改
    public void testUpdate() {
        User user = new User();
        user.setId(1494957810004766722L);
        user.setName("fwzaaa");
        int res = userMapper.updateById(user);
        System.out.println(res);
    }

    @Test//添加用户
    public void testAdd() {
        User user = new User();
        user.setName("fwz");
        user.setAge(23);
        user.setEmail("123@qq.com");
        int res = userMapper.insert(user);
        System.out.println(res);
    }

    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
