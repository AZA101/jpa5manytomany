package cn.itcast5.test;

import cn.itcast5.dao.RoleDao;
import cn.itcast5.dao.UserDao;
import cn.itcast5.domain.Role;
import cn.itcast5.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /*保存一个用户，保存一个角色
     * 多对多当中放弃维护权 被动的一方放弃维护权
     *  这里被动的一方是角色*/
    @Test
    @Transactional  //开启事务
    @Rollback(value = false)//设置不回滚
    public void testAdd() {
        User user = new User();
        user.setUserName("小王");
        Role role = new Role();
        role.setRoleName("高级程序员");
        /*配置用户到角色的关系*/
        user.getRoleSet().add(role);
        /*配置角色到用户的关系*/
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);

    }

    /*级联操作 测试级联添加（保存一个用户的同时保存用户关联的角色）*/
    @Test
    @Transactional  //开启事务
    @Rollback(value = false)//设置不回滚
    public void testCasCadeAdd() {
        User user = new User();
        user.setUserName("老李");
        Role role = new Role();
        role.setRoleName("系统架构师");
        /*配置用户到角色的关系*/
        user.getRoleSet().add(role);
        /*配置角色到用户的关系*/
        role.getUsers().add(user);
        userDao.save(user);
    }

    /*级联操作 测试级联删除（删除一个用户的同时删除用户关联的角色，中间表内容也要删除）*/
    @Test
    @Transactional  //开启事务
    @Rollback(value = false)//设置不回滚
    public void testCasCadeRemove() {
        //查询用户
        User user = userDao.findOne(1l);
        //删除用户
        userDao.delete(user);
    }

}
