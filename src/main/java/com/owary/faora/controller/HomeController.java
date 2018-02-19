package com.owary.faora.controller;

import com.owary.faora.entity.Todo;
import com.owary.faora.service.TodoService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TodoService service;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("main");
        List<Todo> list = service.sort();
        DateTime now = DateTime.now();
        for(int i=0;i<list.size();i++){
            list.get(i).setTimePassed(timeBetween(now,list.get(i).getDateCreated()));
        }
        model.addObject("list", list);
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam(value="title") String title, @RequestParam(value="desc") String desc){
        Todo todo = new Todo(title, desc);
        return Boolean.toString(service.addEntry(todo));
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam(value="id") Long id, @RequestParam(value="title") String title, @RequestParam(value="desc") String desc){
        Todo todo = service.getTodoById(id);
        todo.setTitle(title);
        todo.setDescription(desc);
        return Boolean.toString(service.updateEntry(todo));
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam(value="id") Long id){
        return Boolean.toString(service.deleteEntryById(id));
    }

    @RequestMapping(value = "/done", method = RequestMethod.POST)
    @ResponseBody
    public String done(@RequestParam(value="id") Long id){
        return Boolean.toString(service.markDone(id));
    }

    @RequestMapping(value = "/undone", method = RequestMethod.POST)
    @ResponseBody
    public String undone(@RequestParam(value="id") Long id){
        return Boolean.toString(service.markUndone(id));
    }

    @RequestMapping("/test")
    @ResponseBody
    public String method(){
        return "Worked down here";
    }

    public String timeBetween(DateTime now, Date date){
        DateTime comp = new DateTime(date);

        Period p = new Period(now, comp);
        long days = Math.abs(p.getDays());
        long hours = Math.abs(p.getHours());
        long minutes = Math.abs(p.getMinutes());
        String result = "";

        if (days==0){
            if (hours==0){
                if (minutes==0){
                    result = "added just now";
                }else{
                    if(minutes == 1){
                        result = "added "+minutes+" minute ago";
                    }else{
                        result = "added "+minutes+" minutes ago";
                    }
                }
            }else{
                if(hours == 1){
                    result = "added "+hours+" hour ago";
                }else{
                    result = "added "+hours+" hours ago";
                }
            }
        }else {
            if (days == 1) {
                result = "added " + days + " day ago";
            } else {
                result = "added " + days + " days ago";
            }
        }
        return result;
    }



}
