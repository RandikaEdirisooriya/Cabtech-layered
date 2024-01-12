package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.DTO.Customer;
import lk.ijse.CABTEACK.DTO.admin;

import java.sql.SQLException;
import java.util.List;

public interface ProfileBo {
    boolean AddProfile(admin a) throws SQLException, ClassNotFoundException;
    List<String> getUserIds() throws SQLException, ClassNotFoundException;
}
