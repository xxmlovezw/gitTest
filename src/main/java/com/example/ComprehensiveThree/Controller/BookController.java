package com.example.ComprehensiveThree.Controller;

import com.example.ComprehensiveThree.Domain.BookInfo;
import com.example.ComprehensiveThree.POJO.QueryBook;
import com.example.ComprehensiveThree.Service.BookInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private ObjectMapper objectMapper;


    @ResponseBody
    @RequestMapping(path ="/{searchBookName}", method = RequestMethod.GET)
    public String getBooks(@PathVariable String searchBookName)throws Exception{
        final List<BookInfo> bookInfosByBookName = bookInfoService.findBookInfosByBookName(searchBookName);
        return objectMapper.writeValueAsString(bookInfosByBookName);
    }



    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addBook(BookInfo bookInfo){
        System.out.println(bookInfo.getPrice());
        bookInfo.setEnteringDate(new Date());
        bookInfo.setState(1);
        bookInfoService.addBook(bookInfo);
        return "ok";
    }
    @RequestMapping(path = "/books",method = RequestMethod.POST)
    @ResponseBody
    public String addBooks(HttpServletRequest httpServletRequest)throws Exception{
        List<BookInfo> list = new ArrayList<>();
        String line = null;
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) httpServletRequest;
        final MultipartFile file = multipartRequest.getFile("file");
        System.out.println(file.getOriginalFilename());
        final InputStream inputStream = file.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = bufferedReader.readLine()) != null){
            final BookInfo bookInfo = importExcel(line);
            list.add(bookInfo);
        }
        return objectMapper.writeValueAsString(list);
    }


    private BookInfo importExcel(String line)throws Exception{
        BookInfo bookInfo = new BookInfo();
        final String[] split = line.split(" ");
        bookInfo.setBookId(split[0]);
        bookInfo.setBookName(split[1]);
        bookInfo.setAuthor(split[2]);
        bookInfo.setTranslator(split[3]);
        bookInfo.setPrice(Integer.parseInt(split[4]));
        bookInfo.setISBNCode(split[5]);
        final String s = split[6];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm");
        Date time = simpleDateFormat.parse(s);
        bookInfo.setComeUpTime(time);
        bookInfo.setPublishCompany(split[7]);
        return bookInfo;
    }
   @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(path = "/addBooks",method = RequestMethod.POST)
    @ResponseBody
    public String addBooks(String books)throws Exception{
        System.out.println(books);
        final List<BookInfo> list = objectMapper.readValue(books, new TypeReference<List<BookInfo>>() {});
        for (BookInfo bookInfo : list){
            System.out.println(bookInfo.getISBNCode());
            bookInfo.setEnteringDate(new Date());
            bookInfo.setState(1);
            bookInfoService.addBook(bookInfo);
        }
        return "ok";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String updateBook(BookInfo bookInfo){
        final BookInfo bookInfoByBookId = bookInfoService.findBookInfoByBookId(bookInfo.getBookId());
        bookInfoService.updateBook(compareAndRepeat(bookInfoByBookId,bookInfo));
        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBook(String bookId){
        if (bookInfoService.deleteBookInfoByBookId(bookId))
            return "error";
        return "ok";
    }


    private BookInfo compareAndRepeat(BookInfo old,BookInfo now){
        if (now.getBookId() != null)
            old.setBookId(now.getBookId());
        if (now.getBookName() != null)
            old.setBookName(now.getBookName());
        if (now.getPrice() != 0)
            old.setPrice(now.getPrice());
        if (now.getAuthor() != null)
            old.setAuthor(now.getAuthor());
        if (now.getEnteringMen() != null)
            old.setEnteringMen(now.getEnteringMen());
        if (now.getPublishCompany() != null)
            old.setPublishCompany(now.getPublishCompany());
        if (now.getISBNCode() != null)
            old.setISBNCode(now.getISBNCode());
        if (now.getComeUpTime() != null)
            old.setComeUpTime(now.getComeUpTime());
        old.setEnteringDate(new Date());
        return old;
    }

    private List<QueryBook> QueryBookArrange(List<BookInfo> bookInfos){
        return null;
    }
}
