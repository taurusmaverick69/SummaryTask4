package ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao;

import ua.nure.lyubimtsev.SummaryTask4.db.entities.Category;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;

import java.util.List;

public interface CategoryDAO {

    List<Category> getAllCategories() throws DBException;

}
