package lk.ijse.CABTEACK.DAO.Custom.Impl;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.CABTEACK.DAO.Custom.EmployeeDao;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.ENTITY.EmployeeEntity;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDao {

    @Override
    public ArrayList<EmployeeEntity> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeEntity> employ = new ArrayList<>();
        ResultSet res = SqlUtil.execute("SELECT * FROM employee");
        {

            while (res.next()) {
                Blob blob = res.getBlob(10);
                InputStream binaryStream = blob.getBinaryStream();
                Image image = new Image(binaryStream);
                ImageView imageView = new ImageView();
                imageView.setFitWidth(50);
                imageView.setFitHeight(50
                );
                imageView.setImage(image);
                EmployeeEntity entity = new EmployeeEntity(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getDouble(7),
                        imageView,
                        res.getString(11)
                );
                employ.add(entity);

            }
            return employ;
        }
    }

    @Override
    public boolean save(EmployeeEntity dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public EmployeeEntity search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SqlUtil.execute("SELECT EmpId FROM employee");
        List<String> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getString("EmpId"));
        }
        return ids;
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
