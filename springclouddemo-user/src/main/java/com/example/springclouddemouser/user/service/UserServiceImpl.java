package com.example.springclouddemouser.user.service;

import com.example.springclouddemouser.common.constants.Constants;
import com.example.springclouddemouser.common.exception.MamaBuyException;
import com.example.springclouddemouser.user.dao.UserMapper;
import com.example.springclouddemouser.user.entity.User;
import com.example.springclouddemouser.user.entity.UserElement;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/24
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CuratorFramework zkClient;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 采用zookeeper分布式锁保证查重正确,注册路径保证
     * @param user
     * @throws Exception
     */
    @Override
    @Transactional
    public void registerUser(User user) throws Exception {
        InterProcessLock lock = null;
        try {
            lock = new InterProcessMutex(zkClient, Constants.USER_REGISTER_DISTRIBUTE_LOCK_PATH);
            boolean retry = true;
            do {
                if(lock.acquire(3000, TimeUnit.MILLISECONDS)) {
                    //查询重复用户
                    User repeatUser = userMapper.selectByEmail(user.getEmail());
                    if(repeatUser != null) {
                        throw new MamaBuyException("用户邮箱重复");
                    }
                    //检查两次密码是否一致
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                    user.setNickName("码码购用户"+user.getEmail());
                    userMapper.insertSelective(user);
                }
                retry = false;
            }while (retry);

        }catch (Exception e) {
            log.error("用户注册异常",e);
            throw e;
        } finally {
            if(null != lock){
                try {
                    lock.release();
                    log.info(user.getEmail()+Thread.currentThread().getName()+"释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public UserElement login(User user) {
        UserElement ue;
        User existUser = userMapper.selectByEmail(user.getEmail());
        if(null == existUser) {
            throw new MamaBuyException("用户不存在");
        } else {
            boolean result = bCryptPasswordEncoder.matches(user.getPassword(),existUser.getPassword());
            if(!result) {
                //密码错误
                throw new MamaBuyException("密码错误");
            }else {
                //验证通过，赋值ue，缓存
                ue = new UserElement();
                ue.setEmail(existUser.getEmail());
                ue.setUserId(existUser.getId());
                ue.setUuid(existUser.getUuid());
                ue.setNickname(existUser.getNickName());
            }
        }

        return ue;
    }
}
