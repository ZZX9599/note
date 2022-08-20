package com.baizhi.service.impl;

import com.baizhi.entity.Admin;
import com.baizhi.dao.AdminDao;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Admin)表服务实现类
 *
 * @author chenyn
 * @since 2021-05-12 17:06:55
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin login(Admin admin) {
        Admin adminDB = adminDao.findByUserName(admin.getUsername());
        if(adminDB==null) throw new RuntimeException("用户名输入错误!!");
        if(!adminDB.getPassword().equals(admin.getPassword())) throw new  RuntimeException("密码输入错误!!!");
        return adminDB;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Integer id) {
        return this.adminDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAllByLimit(int offset, int limit) {
        return this.adminDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.adminDao.deleteById(id) > 0;
    }
}
