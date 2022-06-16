package com.example.backprojet.service;
import com.example.backprojet.model.Role;
import com.example.backprojet.model.User;
import com.example.backprojet.service.repo.RoleDao;
import com.example.backprojet.service.repo.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

     @Autowired
     private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        Role ProjectManagerRole = new Role();
        ProjectManagerRole.setRoleName("ProjectManager");
        ProjectManagerRole.setRoleDescription("role for newly created ProjectManagerRole");
        roleDao.save(ProjectManagerRole);

        Role ProjectDirectorRole = new Role();
        ProjectDirectorRole.setRoleName("ProjectDirector");
        ProjectDirectorRole.setRoleDescription("role for ProjectDirectorRole");
        roleDao.save(ProjectDirectorRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword( getEncodedPassword("admin@pass")) ;
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User Manager = new User();
        Manager.setUserName("abdou");
        Manager.setUserPassword( getEncodedPassword("abdou@pass")) ;
        Manager.setUserFirstName("abdou");
        Manager.setUserLastName("khalladi");
        Set<Role> ManagerRoles = new HashSet<>();
        ManagerRoles.add(ProjectManagerRole);
        Manager.setRole(ManagerRoles);
        userDao.save(Manager);







//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user,String RoleName) {
        Role role = roleDao.findById(RoleName).get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
       // user.setUserPassword( user.getUserPassword() );

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
         return passwordEncoder.encode(password);
     }
}
