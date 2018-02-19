package com.owary.faora.service;

import com.owary.faora.dao.TodoDAO;
import com.owary.faora.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDAO dao;

    @Override
    public List<Todo> getAllTodos() {
        try {
            return dao.getAllTodos();
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Todo getTodoById(long id) {
        try {
            return dao.getTodoById(id);
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addEntry(Todo todo) {
        try {
            dao.addEntry(todo);
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEntry(Todo todo) {
        try {
            dao.updateEntry(todo);
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEntryById(long id) {
        try {
            dao.deleteEntryById(id);
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean markDone(long id) {
        try {
            dao.markDone(id);
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean markUndone(long id) {
        try {
            dao.markUndone(id);
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Todo> sort(){
        List<Todo> list = getAllTodos();
        List<Todo> trues = new ArrayList<>();
        List<Todo> falses = new ArrayList<>();
        List<Todo> results = new ArrayList<>();

        for (Todo t : list){
            if(t.getDone()){
                trues.add(t);
            }else{
                falses.add(t);
            }
        }

        results.addAll(falses);
        results.addAll(trues);

        return results;
    }
}
