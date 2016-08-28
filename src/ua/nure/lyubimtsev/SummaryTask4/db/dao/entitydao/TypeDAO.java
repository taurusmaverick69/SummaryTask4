package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Type;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.util.List;

public interface TypeDAO {

    List<Type> getAllTypes() throws DBException;

}
