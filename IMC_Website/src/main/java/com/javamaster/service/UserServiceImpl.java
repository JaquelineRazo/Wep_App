
package com.javamaster.service;

import com.javamaster.dao.UsersDAO;
import com.javamaster.exception.AuthException;
import com.javamaster.model.UsersModel;
import com.javamaster.model.UsersRecord;


public class UserServiceImpl implements UsersService{
    
    private static UserServiceImpl instance;
    
    private UserServiceImpl(){
        
    }
    
     public static synchronized UserServiceImpl getInstance(){
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }
    
    @Override
    public void register(String name, String age, String sex, String height, String email, String password){
        UsersModel user = new UsersModel();
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        user.setHeight(height);
        user.setEmail(email);
        user.setPassword(password);
        UsersDAO.getInstance().save(user);
    }
    
     @Override
    public void record(String date,   String imc){
        UsersRecord user = new UsersRecord();
        user.setDate(date);
       
        user.setImc(imc);
     
        UsersDAO.getInstance().record(user);
    }

    @Override
    public UsersModel auth(String email, String password) throws AuthException {
        UsersModel user = UsersDAO.getInstance().findByEmailAndPassword(email, password);
        if(user == null ) {
            throw new AuthException("email o contraseña incorrectos");
        }
        return user;
    }

   
}
