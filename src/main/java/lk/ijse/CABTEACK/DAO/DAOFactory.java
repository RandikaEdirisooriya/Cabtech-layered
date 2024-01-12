package lk.ijse.CABTEACK.DAO;

import lk.ijse.CABTEACK.DAO.Custom.Impl.*;
import lk.ijse.CABTEACK.DAO.Custom.Impl.VehiclePartOrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,CAR,THREEWEEL,BIKE,VEHICLEPARTS,EMPLOYEE,VEHICLEORDER,VEHICALPARTORDER,VEHICALORDERDETAILS,VEHICALPARTSORDERDETAIL,QUERY,PROFILE
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
               return new CustomerDAOImpl();
            case THREEWEEL:
                return new ThreeWeelDAOImpl();
            case CAR:
                return new CarDAOImpl();
            case PROFILE:
                return new ProfileDAOImpl();
            case BIKE:
                return new BikeDAOImpl();
            case VEHICLEPARTS:
              return new VehiclePartDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case VEHICLEORDER:
                return new VehicleOrderDAOImpl();
            case VEHICALPARTORDER:
                return new VehiclePartOrderDAOImpl();
             case VEHICALORDERDETAILS:
               // return new ItemDAOImpl();
            case VEHICALPARTSORDERDETAIL:
              //  return new OrderDAOImpl();

            case QUERY:
              //  return new QueryDAOImpl();
            default:
                return null;
        }
    }

}
