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

    @RequestMapping("/")
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
    public RedirectView add(@RequestParam(value="title") String title, @RequestParam(value="desc") String desc, HttpServletRequest request){
        Todo todo = new Todo(title, desc);
        service.addEntry(todo);
        String path = request.getContextPath();
        RedirectView view = new RedirectView(path);
        return view;
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public RedirectView update(@RequestParam(value="id") Long id, @RequestParam(value="title") String title, @RequestParam(value="desc") String desc, HttpServletRequest request){
        Todo todo = service.getTodoById(id);
        todo.setTitle(title);
        todo.setDescription(desc);
        service.updateEntry(todo);
        String path = request.getContextPath();
        RedirectView view  = new RedirectView(path);
        return view;
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public RedirectView delete(@RequestParam(value="id") Long id, HttpServletRequest request){
        service.deleteEntryById(id);
        String path = request.getContextPath();
        RedirectView view  = new RedirectView(path);
        return view;
    }

    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public RedirectView done(@RequestParam(value="id") Long id, HttpServletRequest request){
        service.markDone(id);
        String path = request.getContextPath();
        RedirectView view  = new RedirectView(path);
        return view;
    }

    @RequestMapping(value = "/undone", method = RequestMethod.POST)
    public RedirectView undone(@RequestParam(value="id") Long id, HttpServletRequest request){
        service.markUndone(id);
        String path = request.getContextPath();
        RedirectView view  = new RedirectView(path);
        return view;
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
