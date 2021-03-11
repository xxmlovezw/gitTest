package com.example.ComprehensiveThree.Controller;


import com.example.ComprehensiveThree.Domain.BookInfo;
import com.example.ComprehensiveThree.Domain.BorrowRecords;
import com.example.ComprehensiveThree.Domain.UserInfo;
import com.example.ComprehensiveThree.POJO.BorrowDetailInfo;
import com.example.ComprehensiveThree.Service.BookInfoService;
import com.example.ComprehensiveThree.Service.BorrowRecordsService;
import com.example.ComprehensiveThree.Service.UserInfoService;
import com.example.ComprehensiveThree.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@RequestMapping(path = "/borrowRecords")
@Controller
public class BorrowRecordsController {

    @Autowired
    private BorrowRecordsService borrowRecordsService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/borrowed",method = RequestMethod.GET)
    @ResponseBody
    public String getBorrowedRecords(String userId)throws Exception{
        final List<BorrowRecords> borrowRecordsOnBorrowed = borrowRecordsService.findBorrowRecordsOnBorrowed(userId);
        return objectMapper.writeValueAsString(borrowRecordsOnBorrowed);
    }
    @RequestMapping(path = "/borrowing",method = RequestMethod.GET)
    @ResponseBody
    public String getBorrowingRecords(String userId,int state)throws Exception{
        final List<BorrowRecords> borrowRecordsOnBorrowing = borrowRecordsService.findBorrowRecordsOnBorrowing(userId, state);
        return  objectMapper.writeValueAsString(borrowRecordsOnBorrowing);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addBorrowRecords(String bookIdString)throws Exception{

        final String[] split = bookIdString.split(",");
        List<String> list = Arrays.asList(split);
        final int size = list.size();
        String userId = list.get(0);
        int time = Integer.parseInt(list.get(1));
        for (int i = 2;i<size;i++ ){
            String bookId = list.get(i);
            System.out.println(bookId);

            BookInfo bookInfo = bookInfoService.findBookInfoByBookId(bookId);
            BorrowRecords borrowRecords = new BorrowRecords();
            borrowRecords.setUserId(userId);
            borrowRecords.setBookId(bookId);
            borrowRecords.setBookName(bookInfo.getBookName());
            Date borrowTime = new Date();
            borrowRecords.setBorrowTime(borrowTime);
            borrowRecords.setShouldTime(getShouldTime(borrowTime,time));
            borrowRecordsService.addBorrowRecords(borrowRecords);
            bookInfo.setState(0);
            bookInfoService.updateBook(bookInfo);
        }
        final UserInfo byId = userInfoService.findById(userId);
        byId.setLendNum(byId.getLendNum()+(size -2));
        userInfoService.updateUserInfo(byId);
        return "ok";
    }


    private Date getShouldTime(Date date,int day){
        final Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DATE,day);
        return instance.getTime();
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String updateBorrowRecords(String bookId){
        BorrowRecords borrowRecordsByBookId = borrowRecordsService.findBorrowRecordsByBookId(bookId);
        if (borrowRecordsByBookId == null)
            return "error";
        final BookInfo bookInfoByBookId = bookInfoService.findBookInfoByBookId(bookId);
        if (bookInfoByBookId == null)
            return "error";
        borrowRecordsByBookId.setReturnTime(new Date());
        borrowRecordsByBookId.setState(1);
        borrowRecordsService.updateBorrowRecords(borrowRecordsByBookId);
        bookInfoByBookId.setState(1);
        bookInfoService.updateBook(bookInfoByBookId);
        return "ok";
    }

    @RequestMapping(path = "/{borrowId}",method = RequestMethod.GET)
    @ResponseBody
    public String borrowVerification(@PathVariable String borrowId)throws Exception{
        List<BorrowRecords> borrowRecordsOnBorrowing = borrowRecordsService.findBorrowRecordsOnBorrowing(borrowId, 0);
        List<BorrowDetailInfo> list = new ArrayList<>();
        System.out.println(borrowRecordsOnBorrowing);
        if (borrowRecordsOnBorrowing.size() == 0) {
            borrowRecordsOnBorrowing = borrowRecordsService.findBorrowRecordsByBookName(borrowId);
        }
        for (BorrowRecords records:borrowRecordsOnBorrowing){
            final String bookId = records.getBookId();
            final BookInfo bookInfoByBookId = bookInfoService.findBookInfoByBookId(bookId);
            list.add(getBorrowDetailInfo(records,bookInfoByBookId));
        }
        return objectMapper.writeValueAsString(list);
    }

    private BorrowDetailInfo getBorrowDetailInfo(BorrowRecords borrowRecords, BookInfo bookInfo){
        BorrowDetailInfo borrowDetailInfo = new BorrowDetailInfo();
        borrowDetailInfo.setUserId(borrowRecords.getUserId());
        borrowDetailInfo.setBookId(borrowRecords.getBookId());
        borrowDetailInfo.setBookName(borrowRecords.getBookName());
        borrowDetailInfo.setAuthor(bookInfo.getAuthor());
        borrowDetailInfo.setTranslator(bookInfo.getTranslator());
        borrowDetailInfo.setPublishCompany(bookInfo.getPublishCompany());
        borrowDetailInfo.setPublishTime(bookInfo.getComeUpTime());
        borrowDetailInfo.setISBNCode(bookInfo.getISBNCode());
        borrowDetailInfo.setShouldTime(borrowRecords.getShouldTime());
        borrowDetailInfo.setReturnTime(borrowRecords.getReturnTime());
        borrowDetailInfo.setPrice(bookInfo.getPrice());
        borrowDetailInfo.setEnteringMen(bookInfo.getEnteringMen());
        return borrowDetailInfo;
    }

    @RequestMapping(path = "/validation",method = RequestMethod.GET)
    @ResponseBody
    public String validation(String borrowId, String bookId)throws Exception{
        //  1.  验证是否含有这个借阅号
        final UserInfo byId = userInfoService.findById(borrowId);
        if (byId == null)
            return "error:此借阅号不能借阅";
        //  2.  判断这本书是否已经被借阅
        final BookInfo bookInfoByBookId = bookInfoService.findBookInfoByBookId(bookId);
        if (bookInfoByBookId.getState() == 0){
            return "error:这本书已经被借阅";
        }
        return "ok:"+byId.getTime()+"bookName:"+bookInfoByBookId.getBookName();
    }
}
