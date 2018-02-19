package com.owary.faora.service;

import com.owary.faora.entity.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodos();
    Todo getTodoById(long id);
    boolean addEntry(Todo todo);
    boolean updateEntry(Todo todo);
    boolean deleteEntryById(long id);
    boolean markDone(long id);
    boolean markUndone(long id);
    List<Todo> sort();

}
