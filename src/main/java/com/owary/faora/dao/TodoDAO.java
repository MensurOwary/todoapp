package com.owary.faora.dao;

import com.owary.faora.entity.Todo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TodoDAO {

    List<Todo> getAllTodos() throws DataAccessException;
    Todo getTodoById(long id) throws DataAccessException;
    void addEntry(Todo todo) throws DataAccessException;
    void updateEntry(Todo todo) throws DataAccessException;
    void deleteEntryById(long id) throws DataAccessException;
    void markDone(long id) throws DataAccessException;
    void markUndone(long id) throws DataAccessException;

}
