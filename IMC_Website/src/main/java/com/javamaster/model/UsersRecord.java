
package com.javamaster.model;

import java.util.Objects;



public class UsersRecord {
    private String date;
 
    private String imc;

    public UsersRecord(){
        
    }

    public UsersRecord(String date,String imc) {
        this.date = date;
      
        this.imc = imc;
       
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   
   

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.date);
        hash = 43 * hash + Objects.hashCode(this.imc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsersRecord other = (UsersRecord) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.imc, other.imc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UsersRecord{");
        sb.append("date=").append(date);
        sb.append(", imc=").append(imc);
        sb.append('}');
        return sb.toString();
    }

    
    
}
