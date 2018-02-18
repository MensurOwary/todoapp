package com.owary.faora.dao;

import com.owary.faora.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class TodoDAOImpl implements TodoDAO {

    @Autowired
    private HibernateTemplate template;

    @SuppressWarnings("unchecked")
    @Override
    public List<Todo> getAllTodos()  throws DataAccessException{
        return (List<Todo>) template.find("FROM Todo t ORDER BY t.id");
    }

    @Override
    public Todo getTodoById(long id)  throws DataAccessException{
        return template.get(Todo.class, id);
    }

    @Override
    public void addEntry(Todo todo)  throws DataAccessException{
        template.save(todo);
    }

    @Override
    public void updateEntry(Todo todo)  throws DataAccessException{
        template.update(todo);
    }

    @Override
    public void deleteEntryById(long id)  throws DataAccessException{
          template.delete(getTodoById(id));
    }

    @Override
    public void markDone(long id) throws DataAccessException {
            Todo todo = getTodoById(id);
            todo.setDone(true);
            template.update(todo);
    }

    @Override
    public void markUndone(long id) throws DataAccessException {
        Todo todo = getTodoById(id);
        todo.setDone(false);
        template.update(todo);
    }
}
