package com.keduit.bpro52.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;

@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void insertBoard() {
		
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder().email("user" + i + "@asd.com").build();
			
			Board board = Board.builder().title("title.... " + i)
										 .content("content.... " + i)
										 .writer(member)
										 .build();
			boardRepository.save(board);
		});
	}
	
	@Transactional
	@Test
	public void testRead() {
		Optional<Board> result = boardRepository.findById(100L);
		
		Board board = result.get();
		System.out.println(board);
		System.out.println(board.getWriter());
	}
	
	@Test
	public void testReadWithWriter() {
		
		Object result = boardRepository.getBoardWithWriter(100L);
		
		Object[] arr = (Object[])result;
		
		System.out.println("--------------------------");
		System.out.println(Arrays.toString(arr));
	}
	
	@Test
	public void testGetBoardWithReply() {
		
		List<Object[]> result = boardRepository.getBoardWithReply(100L);
		
		for(Object[] arr : result) {
			System.out.println(Arrays.toString(arr));
		}
	}
	
	@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row -> {
			Object[] arr = row;
			
			System.out.println(Arrays.toString(arr));
		});
	}
	
	@Test
	public void testReadOne() {
		Object result = boardRepository.getBoardByBno(100L);
		
		Object[] arr = (Object[])result;
		
		System.out.println(Arrays.toString(arr));
	}
	
	@Test
	public void testSearch1() {
		boardRepository.search1();
	}
	
	@Test
	public void testSearchPage() {
		Pageable pagrable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepository.searchPage("t", "1", pagrable);
	}
	
	@Test
	public void testSearchPageSort() {
		Pageable pagrable = PageRequest.of(0, 10, Sort.by("bno").descending().and(Sort.by("title").ascending()));
		Page<Object[]> result = boardRepository.searchPage("t", "1", pagrable);
	}
}
