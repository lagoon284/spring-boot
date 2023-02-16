package com.keduit.bpro52.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keduit.bpro52.entity.Member;
import com.keduit.bpro52.repository.search.SearchBoardRepository;

public interface MemberRepository extends JpaRepository<Member, String>, SearchBoardRepository{

}
