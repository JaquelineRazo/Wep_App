package com.javamaster.dao;

import com.javamaster.config.DBConnectionConfig;
import com.javamaster.model.UsersModel;
import com.javamaster.model.UsersRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDAO {

    public static UsersDAO instance;

    private UsersDAO() {

    }

    public static synchronized UsersDAO getInstance() {
        if (instance == null) {
            instance = new UsersDAO();
        }
        return instance;
    }

    public void save(UsersModel user) {
        Connection connection = DBConnectionConfig.getConnection();
        try {
            PreparedStatement pr = connection.prepareStatement("insert into users_table"
                    + "(name, age, sex, height, email, password) values(?, ?, ?, ?, ?, ?)");
            pr.setString(1, user.getName());
            pr.setString(2, user.getAge());
            pr.setString(3, user.getSex());
            pr.setString(4, user.getHeight());
            pr.setString(5, user.getEmail());
            pr.setString(6, user.getPassword());
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void record(UsersRecord user) {
        Connection connection = DBConnectionConfig.getConnection();
        try {
            PreparedStatement pr = connection.prepareStatement("insert into imc_record"
                    + "(date, imc) values(?, ?)");
            pr.setString(1, user.getDate());

            pr.setString(2, user.getImc());

            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UsersModel findByEmailAndPassword(String email, String password) {
        Connection connection = DBConnectionConfig.getConnection();
        UsersModel user = null;
        try {
            PreparedStatement pr = connection.prepareStatement("select * from users_table where email=? and password=?;");

            pr.setString(1, email);
            pr.setString(2, password);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user = new UsersModel();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setName(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
