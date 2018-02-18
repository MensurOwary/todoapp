package com.owary.faora.service;

import com.owary.faora.entity.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodos();
    Todo getTodoById(long id);
    void addEntry(Todo todo);
    void updateEntry(Todo todo);
    void deleteEntryById(long id);
    void markDone(long id);
    void markUndone(long id);
    List<Todo> sort();

}
