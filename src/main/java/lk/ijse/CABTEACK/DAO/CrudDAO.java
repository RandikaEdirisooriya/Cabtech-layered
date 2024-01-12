package lk.ijse.CABTEACK.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
     ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException ;
    boolean update(T dto) throws SQLException, ClassNotFoundException ;

    void delete(String id) throws SQLException, ClassNotFoundException ;

    public T search(String id) throws SQLException, ClassNotFoundException;
    List<String> getIds() throws SQLException, ClassNotFoundException;

    List<String> getAllIds() throws SQLException, ClassNotFoundException;
}