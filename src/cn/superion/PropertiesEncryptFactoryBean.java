package cn.superion;


import java.util.Properties;  

import org.springframework.beans.factory.FactoryBean;  

import cn.superion.util.SupSafe;
  
public class PropertiesEncryptFactoryBean implements FactoryBean {  
  
    private Properties properties;  
      
    public Object getObject() throws Exception {  
        return getProperties();  
    }  
  
    public Class getObjectType() {  
        return java.util.Properties.class;  
    }  
  
    public boolean isSingleton() {  
        return true;  
    }  
  
    public Properties getProperties() {  
        return properties;  
    }  
  
    public void setProperties(Properties inProperties) {  
        this.properties = inProperties;  
        String originalUsername = properties.getProperty("user");  
        String originalPassword = properties.getProperty("password");  
        if (originalUsername != null){
//            String newUsername = SupSafe.getCrack(originalUsername);  
            properties.put("user", originalUsername);  
        }  
        if (originalPassword != null){
            String newPassword = SupSafe.getCrack(originalPassword);  
            properties.put("password", newPassword);
        }  
    } 
    public static void main(String[] args) {
//    	System.out.println(SupSafe.getEncrypt("suphisv3_zyy"));
    	System.out.println(SupSafe.getEncrypt("suphisv3_zyy"));
	}
}  