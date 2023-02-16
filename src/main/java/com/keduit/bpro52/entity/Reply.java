package com.keduit.bpro52.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude="board")
public class Reply extends BaseEntity {
	
	@Id
	@GeneratedValue
	private Long rno;
	private String text;
	private String replyer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;

}
