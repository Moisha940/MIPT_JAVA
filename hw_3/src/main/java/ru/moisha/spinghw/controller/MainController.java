package ru.moisha.spinghw.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.moisha.spinghw.reader.Reader;


@Controller
@RequiredArgsConstructor
@RequestMapping("/save")
public class MainController {
    private final Reader reader;

    /*

    curl "http://localhost:8080/save/user?file_address=/Users/moisha/cmpt130/Java/IdeaProjects/SpringHw/students/test1"

    */
    @GetMapping("/user")
    public void get(@RequestParam("file_address") String file_address) {
        reader.getData(file_address);
    }

}
