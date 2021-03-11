package com.example.ComprehensiveThree.Controller;


import com.example.ComprehensiveThree.Domain.BookInfo;
import com.example.ComprehensiveThree.POJO.QueryBook;
import com.example.ComprehensiveThree.Service.BookInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(path = "/search")
public class BookSearchController {
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String search(String query)throws Exception{
        final List<BookInfo> bookInfosByBookNameAndState = bookInfoService.findBookInfosByBookNameAndState(query, 1);
        if (bookInfosByBookNameAndState.size() == 0){
            List<BookInfo> bookInfosByAuthorAndState = bookInfoService.findBookInfosByAuthorAndState(query, 1);
            final List<QueryBook> handle = handle(bookInfosByAuthorAndState);
            return objectMapper.writeValueAsString(handle);
        } else {
            final BookInfo bookInfo = bookInfosByBookNameAndState.get(0);
            List<QueryBook> list = new ArrayList<>();
             QueryBook queryBook = new QueryBook();
            queryBook.setBookName(bookInfo.getBookName());
            queryBook.setAuthor(bookInfo.getAuthor());
            queryBook.setNumOnLibrary(bookInfosByBookNameAndState.size());
            queryBook.setPublishCompany(bookInfo.getPublishCompany());
            list.add(queryBook);
            return objectMapper.writeValueAsString(list);
        }
    }

    private List<QueryBook> handle(List<BookInfo> list){

        HashMap<String,List<BookInfo>> hashMap = new HashMap<>();
        for (BookInfo bookInfo:list){
            final List<BookInfo> bookInfos = hashMap.get(bookInfo.getBookName());
            if (bookInfos == null){
                ArrayList<BookInfo> arrayList = new ArrayList<>();
                arrayList.add(bookInfo);
                hashMap.put(bookInfo.getBookName(),arrayList);
            } else
                bookInfos.add(bookInfo);
        }
        List<QueryBook> queryBooks = new ArrayList<>();
        final Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            final String next = iterator.next();
            final List<BookInfo> bookInfos = hashMap.get(next);
            final BookInfo bookInfo = bookInfos.get(0);
            QueryBook queryBook = new QueryBook();
            queryBook.setAuthor(bookInfo.getAuthor());
            queryBook.setBookName(bookInfo.getBookName());
            queryBook.setNumOnLibrary(bookInfos.size());
            queryBook.setPublishCompany(bookInfo.getPublishCompany());
            queryBooks.add(queryBook);
        }
        return queryBooks;
    }
}
