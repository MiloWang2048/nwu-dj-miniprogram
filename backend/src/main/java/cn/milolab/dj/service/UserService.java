package cn.milolab.dj.service;

import cn.milolab.dj.bean.entity.Employee;
import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.bean.request.VerifyRequest;
import cn.milolab.dj.dao.EmployeeDAO;
import cn.milolab.dj.dao.JobDAO;
import cn.milolab.dj.dao.UserDAO;
import cn.milolab.dj.error.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;

/**
 * @author milow
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JobDAO jobDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    public void verify(VerifyRequest request, User user){
        Employee employee = employeeDAO.findByStuSerial(request.getStuSerial());
        if(employee==null){
            throw new BadRequestException("学号不存在");
        }
        if(!employee.getJobSerial().equals(request.getJobSerial())){
            throw new BadRequestException("学号与工号不符");
        }
        employee.setUserId(user.getId());
        employeeDAO.updateWithEntity(employee);
    }
}
