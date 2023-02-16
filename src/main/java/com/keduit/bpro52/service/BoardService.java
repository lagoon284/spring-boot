package com.keduit.bpro52.service;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;

public interface BoardService {
	
	Long register(BoardDTO boardDTO);
	
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	BoardDTO get(Long bno);
	
	void removeWithReplies(Long bno);
	
	void modify(BoardDTO boardDTO);
	
	default Board dtoToEntity(BoardDTO dto) {
		
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		
		Board board = Board.builder().bno(dto.getBno())
									 .title(dto.getTitle())
									 .content(dto.getContent())
									 .writer(member)
									 .build();
		return board;
	}
	
	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
		
		BoardDTO boardDTO = BoardDTO.builder()
									.bno(board.getBno())
									.title(board.getTitle())
									.content(board.getContent())
									.regDate(board.getRegDate())
									.updateDate(board.getUpdateDate())
									.writerEmail(member.getEmail())
									.writerName(member.getName())
									.replyCount(replyCount.intValue())
									.build();
		
		return boardDTO;
		
	}

}
