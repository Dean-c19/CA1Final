package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// supposed to to be used for transferring members data between postman and the server

@XmlRootElement(name = "member")
public class UserM {

    private String usersId;
    private String name;


    public String getUsersId() {
        return usersId;
    }

    public void setMembersId(String membersId) {
        this.usersId = membersId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
