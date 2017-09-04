/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

/**
 *
 * @author MatthewVillegas
 */
public class ConnectionData {
    String type;
    String url;
    String ipaddress;
    String port;
    String database;
    String login;
    String password;

    /**
     *
     * @return returns the String type member variable
     * 
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type is a string
     * sets member variable type to the input string
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return returns the member variable
     * 
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url is a string that then is what the member variable is set
     * equal to
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return returns member variable
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     *
     * @param ipaddress is a string that the member variable is set to
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     *
     * @return returns member variable
     */
    public String getPort() {
        return port;
    }

    /**
     *
     * @param port is a string that the member variable is set to
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     *
     * @return returns member variable
     */
    public String getDatabase() {
        return database;
    }

    /**
     *
     * @param database is a string that the member variable is set to
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     *
     * @return returns member variable
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login is a string that the member variable is set to
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return returns member variable
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password is a string that the member variable is set to
     */
    public void setPassword(String password) {
        this.password = password;
    }
   
    /**
     *
     * @return returns a string with a  postgreSQL connection formatted properly
     */
    @Override
    public String toString()
    {
        String connection = new String();
        
        connection =  url + "://" + ipaddress + ":" + port + "/" + database;
        return connection;
                
    }

}
