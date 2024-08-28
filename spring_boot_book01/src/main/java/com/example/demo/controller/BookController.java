package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.jpa.JpaBookRepository;
import com.example.demo.vo.Book;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookController {
	
	@Autowired
	// JpaBookRepository 인터페이스를 상속받아
	// SQL 쿼리를 실행하는 클래스 객체를 대입받을 변수
	JpaBookRepository jpaBook;
	
	// 전체 도서 조회
	// http://localhost:8080/ 입력 시 전체 도서 조회가 실행되도록 함
	@RequestMapping(value="/")
	public ModelAndView allBook1() {
		List<Book> allList = jpaBook.selectAllBookSortBookid();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("allBook", allList);
		mav.setViewName("selectBookAll");
		
		return mav;
	}
	
	
	// 도서 1권 조회 - bookid를 이용해 도서정보 조회
	@RequestMapping(value="selectBook")
	public ModelAndView viewBook1(@RequestParam (name="bookid") String bid) {
		// bookid가 일치하는 책 정보를 조회한 뒤 변수 b에 저장
		Book b = jpaBook.getById(bid);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", b);
		mav.setViewName("selectBookOne");
		
		return mav;
	}
	
	// 도서 추가 - 도서를 추가할 뷰로 이동
	@RequestMapping(value="/insertBookForm")
	public ModelAndView insertBook1() {
		
		ModelAndView mav = new ModelAndView();
		// insertBook.html 실행
		mav.setViewName("insertBook");
		
		return mav;
	}
	
	// 도서 추가 - 도서 추가 처리
	@RequestMapping(value="/insertBookControl")
	public ModelAndView insertBook2(Book book) {
		/* 다음과 같을 때 Controller의 매개변수에 입력값이 자동 저장
			1. 매개변수가 HttpServletRequest 타입이 아닐 때
			2. 매개변수가 VO 타입일 때
			3. 매개변수가 변수 타입일 때 */
		
		// bookid가 일치하는 book 객체의 속성 값을 DB에 추가
		jpaBook.save(book);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", "도서 정보가 추가되었습니다.");
		mav.setViewName("insertBookResult");
		
		return mav;
	}
	
	// 도서 정보 수정 - 수정할 도서정보 조회
	@RequestMapping(value="/updateBookForm")
	public ModelAndView updateBook1(@RequestParam (name="bookid") String bid) {
		Book b = jpaBook.getById(bid);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", b);
		mav.setViewName("updateBook");
		
		return mav;
	}
	
	// 도서 정보 수정 - 수정 처리
	@RequestMapping(value="/updateBookControl")
	public ModelAndView updateBook2(Book book) {
		// bookid가 일치하는 book 객체의 속성 값을 DB에서 수정
		jpaBook.save(book);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", "도서 정보가 수정되었습니다.");
		mav.setViewName("updateBookResult");
		
		return mav;
	}
	

}
