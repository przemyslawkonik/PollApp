package com.pollapp.controller;

import com.pollapp.bean.Ip;
import com.pollapp.entity.UserData;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.repository.UserRoleRepository;
import com.pollapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class Test {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserDataRepository userDataRepository;


    @GetMapping("")
    @ResponseBody
    public String test() {
        /*UserData userData = new UserData();
        userData.setIp(Ip.remote());
        userDataRepository.save(userData);*/
        /*UserData userData = new UserData();
        userData.setGender(Gender.MALE);
        userData.setAge(5);
        userData.setFirstName("janek");
        userData.setLastName("komarek");

        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("mail");
        userAccount.setPassword("pass");
        userAccount.setUsername("user");
        userAccount.setUserData(userData);

        userAccountRepository.save(userAccount);*/

        /*UserAccount a = new UserAccount();
        a.setUserData(new UserData());
        a.setUserRole(userRoleRepository.findOne(1));
        userAccountRepository.save(a);*/

        /*UserData userData = new UserData(5, Gender.MALE);
        UserData data = userDataRepository.findOne(1L);
        data.setUser(usersRepository.findOne(1L));
        userDataRepository.save(data);*/

        /*userRoleRepository.save(new UserRole(Role.EMPTY));
        userRoleRepository.save(new UserRole(Role.ADMIN));
        userRoleRepository.save(new UserRole(Role.USER));
        */

        //UserData dd = userDataRepository.findOne(1L);
        //dd.setUser(usersRepository.findOne(3L));
        //userDataRepository.save(dd);
        //UserData userData = new UserData(15, Gender.MALE);
        //userData.setUser(usersRepository.findOne(3L));
        //userDataRepository.save(userData);
        //UserAccount user = new UserAccount("bartek", "barto", "deska");
        //UserAccount user1 = new UserAccount("marek", "marto", "deska");
        //usersRepository.save(user);
        //usersRepository.save(user1);

        /*UserData data = new UserData(5, Gender.MALE);
        data.setUser(user);
        userDataRepository.save(data);*/

        //usersRepository.save(user);
        //usersRepository.save(user);
        //userData.setUser(usersRepository.findOne(1L));
        //userDataRepository.save(userData);
        //userDataRepository.delete(1L);
        //userDataRepository.save(userData);
        //user.setUserData(userData);

        //user.setUserData(userData);
        //userDataRepository.save(userData);
        //usersRepository.save(user);

        //usersRepository.save(user);
        //usersRepository.delete(1L);
        //userDataRepository.save(new UserData(usersRepository.findOne(3L)));
        //usersRepository.delete(5L);
        List<UserData> list = new ArrayList<>(userDataRepository.findAll());
        return list.toString();
    }

    @GetMapping("/a")
    public String add() {
        UserData data = new UserData();
        data.setAge(11);
        userDataRepository.save(data);
        return "index";
    }
}
