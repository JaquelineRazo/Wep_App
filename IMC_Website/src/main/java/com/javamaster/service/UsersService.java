
package com.javamaster.service;

import com.javamaster.exception.AuthException;
import com.javamaster.model.UsersModel;


public interface UsersService {
 void register(String name, String age, String sex, String height, String email, String password);
 UsersModel auth(String email, String password) throws AuthException;
 void record(String date,  String imc);  
}
