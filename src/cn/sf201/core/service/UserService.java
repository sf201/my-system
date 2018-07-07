package cn.sf201.core.service;

import cn.sf201.core.annotations.Auth;
import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.entity.user.User;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.utils.MD5Util;
import cn.sf201.core.utils.MapUtil;
import cn.sf201.core.utils.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserService {
    @Resource(name = "userDomain")
    private BaseDomain userDomain;

    /**
     * 登录
     *
     * @param params
     * @return
     * @throws RequestProcessException
     */
    public User login(Map<String, Object> params) throws RequestProcessException {
        String userName = MapUtil.getStringValue("userName", params);
        String password = MapUtil.getStringValue("password", params);
        String encrypedPassword = MD5Util.getMD5(password);
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("userName", userName));
        criteria.add(Restrictions.eq("password", encrypedPassword));
        List<User> list = userDomain.queryByCriteria(criteria);
        if (list == null || list.size() == 0) {
            throw new RequestProcessException("用户名或密码错误!");
        }
        return list.get(0);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public User addUser(User user) throws RequestProcessException {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("userName", user.getUserName()));
        List<User> list = userDomain.queryByCriteria(criteria);
        if (list != null && list.size() > 0) {
            throw new RequestProcessException("该用户名已经被占用，请重新输入！");
        }

        user.setUserName(user.getUserName().toUpperCase());
        if (!StringUtil.isNullOrEmpty(user.getPassword())) {
            user.setPassword(MD5Util.getMD5(user.getPassword()));
        } else {
            user.setPassword(MD5Util.getMD5("0"));
        }
        try {
            userDomain.merge(user);
            return user;
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }
    }

    /**
     * 获取用户列表
     *
     * @param params
     * @return
     */
    public List<User> list(Map<String, Object> params) throws RequestProcessException {
        String userName = MapUtil.getStringValue("userName", params);
        String name = MapUtil.getStringValue("name", params);
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        if (!StringUtil.isNullOrEmpty(userName)) {
            criteria.add(Restrictions.eq("userName", userName));
        }
        if (!StringUtil.isNullOrEmpty(name)) {
            criteria.add(Restrictions.like("name", "%" + name + "%"));
        }
        try {
            List<User> list = userDomain.queryByCriteria(criteria);
            return list;
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }

    }

    public int changePassword(Map<String, Object> params) throws RequestProcessException {
        String userName = MapUtil.getStringValue("userName", params);
        String password = MapUtil.getStringValue("password", params);
        String newPassWord = MapUtil.getStringValue("newPassword", params);
        String rePassWord = MapUtil.getStringValue("rePassword", params);
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);

        if (StringUtil.isNullOrEmpty(userName)) {
            throw new RequestProcessException("用户名不能为空！");
        }
        if (StringUtil.isNullOrEmpty(password)) {
            throw new RequestProcessException("原始密码不能为空！");
        }
        if (StringUtil.isNullOrEmpty(newPassWord)) {
            throw new RequestProcessException("新密码不能为空！");
        }
        if (!newPassWord.equals(rePassWord)) {
            throw new RequestProcessException("两次输入的密码不一致！");
        }
        criteria.add(Restrictions.eq("userName", userName));
        criteria.add(Restrictions.eq("password", MD5Util.getMD5(password)));
        try {

            List<User> list = userDomain.queryByCriteria(criteria);
            if (list == null || list.size() <= 0) {
                throw new RequestProcessException("原始密码不正确！");
            }
            User user = list.get(0);
            user.setPassword(MD5Util.getMD5(newPassWord));
            userDomain.merge(user);
            return 1;
        } catch (Exception ex) {
            throw new RequestProcessException("密码修改失败！" + ex.getMessage());
        }

    }
}
