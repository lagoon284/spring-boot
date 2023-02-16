package com.keduit.bpro52.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister() {
		
		BoardDTO dto = BoardDTO.builder()
							   .title("test title.......")
							   .content("content test......")
							   .writerEmail("user33@asd.com")
							   .build();
		
		Long bno = boardService.register(dto);
		
		System.out.println(bno + ": " + dto);
		
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		
		PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
		
		for(BoardDTO boardDTO : result.getDtoList()) {
			System.out.println(boardDTO);
		}
	}
	
	@Test
	public void testGet() {
		Long bno = 97L;
		BoardDTO boardDTO = boardService.get(bno);
		System.out.println("testGet..... " + boardDTO);
	}
	
	@Test
	public void testRemove() {
		Long bno = 5L;
		
		boardService.removeWithReplies(bno);
	}
	
	@Test
	public void testModify() {
		BoardDTO boardDTO = BoardDTO.builder()
									.bno(2L)
									.title("title update....")
									.content("content update....")
									.build();
		
		boardService.modify(boardDTO);
	}
	
	
}
