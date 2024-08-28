package com.example.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Book;

@Repository
// 데이터베이스와 연동하여 쿼리를 실행할 인터페이스
// DB 관련 기능을 수정하고자 할 때
public interface JpaBookRepository extends JpaRepository<Book, String> {
	
	// bookid 컬럼의 값이 일치하는 데이터를 조회
	public List<Book> findByBookid(String bookid);
	
	// value에 설정한 쿼리를 실행하고 조회결과를 리턴
	// 오라클 쿼리 실행 : nativeQuery = true
	@Query(value = "select * from book order by bookid desc", nativeQuery = true)
	public List<Book> selectAllBookSortBookid();

}
